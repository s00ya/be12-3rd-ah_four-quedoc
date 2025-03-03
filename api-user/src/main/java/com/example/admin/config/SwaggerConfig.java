package com.example.admin.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.core.config.SwaggerConfigInterface;

@Configuration
public class SwaggerConfig implements SwaggerConfigInterface {

    @Bean
    public GroupedOpenApi boardGroupedOpenApi() {
        return createGroupedOpenApi("user", "/user/**", "User API", "유저 업무 처리를 위한 API");
    }
}