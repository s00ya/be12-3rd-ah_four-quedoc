package com.example.hospital.config;

import com.example.core.config.SwaggerConfigInterface;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig implements SwaggerConfigInterface {

    @Bean
    public GroupedOpenApi hospitalGroupedOpenApi() {
        return createGroupedOpenApi("hospital",
                "/hospital/**",
                "Hospital API",
                "병원 등록, 조회, 삭제를 위한 API");
    }
    @Bean
    public GroupedOpenApi reviewGroupedOpenApi() {
        return createGroupedOpenApi(
                "review",
                "/review/**",
                "Review API",
                "병원 리뷰 관련 API"
        );
    }

    @Bean
    public GroupedOpenApi noticeGroupedOpenApi() {
        return createGroupedOpenApi(
                "notice",
                "/notice/**",
                "Notice API",
                "병원 공지사항 관련 API"
        );
    }
}
