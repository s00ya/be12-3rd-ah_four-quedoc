package com.example.search.config;

import com.example.core.config.SwaggerConfigInterface;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig implements SwaggerConfigInterface {

    @Bean
    public GroupedOpenApi searchGroupedOpenApi() {
        return createGroupedOpenApi("search", "/search/**", "Search API", "병원 검색 API");
    }

    @Bean
    public GroupedOpenApi searchByNameGroupedOpenApi() {
        return createGroupedOpenApi("search-name", "/search/name/**", "Search by Name API", "병원명 검색 API");
    }

    @Bean
    public GroupedOpenApi searchByTypeGroupedOpenApi() {
        return createGroupedOpenApi("search-type", "/search/type/**", "Search by Type API", "병원 종류 검색 API");
    }

    @Bean
    public GroupedOpenApi searchByLocationGroupedOpenApi() {
        return createGroupedOpenApi("search-location", "/search/location/**", "Search by Location API", "위치 기반 검색 API");
    }

    @Bean
    public GroupedOpenApi searchBySpecialtyGroupedOpenApi() {
        return createGroupedOpenApi("search-specialty", "/search/specialty/**", "Search by Specialty API", "진료과목 검색 API");
    }
}
