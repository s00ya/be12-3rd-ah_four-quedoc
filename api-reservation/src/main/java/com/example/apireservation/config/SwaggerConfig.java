package com.example.apireservation.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.core.config.SwaggerConfigInterface;

@Configuration
public class SwaggerConfig implements SwaggerConfigInterface {

    @Bean
    public GroupedOpenApi boardGroupedOpenApi() {
        return createGroupedOpenApi("reservation", "/reservation/**", "Reservation API", "예약 처리를 위한 API");
    }
}