package com.example.payment.config;

import com.example.core.config.SwaggerConfigInterface;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SwaggerConfig implements SwaggerConfigInterface {

    @Bean
    public GroupedOpenApi boardGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("payment")
                .pathsToMatch("/api/payment/**")  // 실제 API 경로에 맞게 수정
                .addOpenApiMethodFilter(method -> true) // (선택 사항) 메소드 필터링
                .build();
    }

}
