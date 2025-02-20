package com.example.gateway.filter;

import com.example.core.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    @Autowired
    private JWTUtil jwtUtil;

    public JwtAuthenticationFilter() {
        super(Config.class);

        System.out.println("JwtAuthenticationFilter()");
    }

    public static class Config {
        // 설정이 필요하면 여기에 추가 가능 (예: 특정 경로 제외 등)
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            System.out.println("JwtAuthenticationFilter 실행");

            // Authorization 헤더가 없으면 401 반환
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                System.out.println("Authorization 헤더 없음 - 401 반환");
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Authorization 헤더 값 가져오기
            String authorizationHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                System.out.println("Bearer 토큰 없음 - 401 반환");
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // "Bearer " 접두사 제거 후 토큰 추출
            String token = authorizationHeader.substring(7);
            System.out.println("token = " + token);

            String temp = jwtUtil.generateToken("test");
            System.out.println("temp = " + temp);
            // JWT 검증
            if (!jwtUtil.validateToken(token, "test")) {
                System.out.println("JWT 토큰 유효하지 않음 - 401 반환");
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // 모든 검증 통과 시 다음 필터로 요청 전달
            return chain.filter(exchange);
        };
    }

}
