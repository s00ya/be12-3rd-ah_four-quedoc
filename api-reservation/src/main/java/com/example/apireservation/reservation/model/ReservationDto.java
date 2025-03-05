package com.example.apireservation.reservation.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDto {

    @Getter
    @Setter
    @Builder
    @Schema(description = "Reservation 예약을 위한 dto")
    public static class requestDto {
        private Long user_id;
        private Long hospital_idx;
        private String name;
        private LocalDateTime date;

    }
}
