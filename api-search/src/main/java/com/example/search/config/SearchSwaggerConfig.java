package com.example.search.config;

import com.example.core.config.SwaggerConfigInterface;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchSwaggerConfig implements SwaggerConfigInterface {

    @Bean
    public GroupedOpenApi searchGroupedOpenApi() {
        return createGroupedOpenApi("search", "/search/**", "Search API", "검색 관련한 요청을 처리하는 API입니다.");
    }
}
