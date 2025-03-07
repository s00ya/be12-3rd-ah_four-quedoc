package com.example.payment.config;

import com.example.core.config.SwaggerConfigInterface;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class PaymentSwaggerConfig implements SwaggerConfigInterface {

    @Bean
    public GroupedOpenApi paymentGroupedOpenApi() {
        return createGroupedOpenApi("payment", "/payment/**", "Payment API", "결제 요청을 담당하는 API입니다.");
    }

}
