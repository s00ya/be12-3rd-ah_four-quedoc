package com.example.apireservation.reservation.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ReservationDto {

    @Getter
    @Setter
    @Builder
    public static class requestDto {
        private Long user_id;
        private Long hospital_idx;
        private String name;

    }
}
