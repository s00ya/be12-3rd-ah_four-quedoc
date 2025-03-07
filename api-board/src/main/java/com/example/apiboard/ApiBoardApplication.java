package com.example.apiboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.apiboard","com.example.core.common"})
public class ApiBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBoardApplication.class, args);
    }

}
