package com.example.apireservation;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.admin.user.model","com.example.hospital","com.example.apireservation.reservation.model"})
@ComponentScan(basePackages = {"com.example.admin.user.service","com.example.hospital.hospital.service","com.example.hospital.notice.service","com.example.hospital.review.service","com.example.apireservation"})
@EnableJpaRepositories(basePackages = {"com.example.admin.user.repository","com.example.hospital.hospital.repository","com.example.hospital.review.repository","com.example.hospital.notice.repository","com.example.apireservation"})
public class ApiReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiReservationApplication.class, args);
    }

}
