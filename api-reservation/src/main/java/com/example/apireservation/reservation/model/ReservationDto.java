package com.example.apireservation.reservation.model;


import com.example.admin.user.model.User;
import com.example.hospital.hospital.model.Hospital;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDto {

    @Getter
    @Setter
    @Schema(description = "Reservation 예약을 위한 dto")
    public static class requestDto {
        private Long user_id;
        private Long hospital_idx;
        private String name;
        private String date;
    }

    @Getter
    @Setter
    @Builder
    @Schema(description = "Reservation 조회을 위한 dto")
    public static class responseDto {
        private User user;
        private Hospital hospital;
        private String name;
        private String date;

    }

    @Getter
    @Setter
    @Schema(description = "Reservation 변경을 위한 dto")
    public static class updateDto {
        private Long idx;
        private String name;
        private LocalDateTime date;
    }
}
