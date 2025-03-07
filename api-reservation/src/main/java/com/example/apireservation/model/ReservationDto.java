package com.example.apireservation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class ReservationDto {

    @Getter
    @Setter
    @Schema(description = "Reservation 예약을 위한 dto")
    public static class requestDto {

        @Schema(description = "예약한 사람의 id", required = true, example = "2")
        private Long user_idx;
        @Schema(description = "예약된 병원의 id", required = true, example = "2")
        private Long hospital_idx;

        @Schema(description = "방문하는 사람의 이름", required = true, example = "김철수")
        private String name;
        @Schema(description = "방문하는 사람의 전화번호", required = true, example = "01038023921")
        private String phoneNumber;
        @Schema(description = "요청 사항", example = "잘 부탁드립니다.")
        private String content;
    }

    @Getter
    @Setter
    @Builder
    @Schema(description = "Reservation 조회을 위한 dto")
    public static class responseDto {
        private Long idx;
        @Schema(description = "예약한 사람의 id", required = true, example = "2")
        private Long user_idx;
        @Schema(description = "예약된 병원의 id", required = true, example = "2")
        private Long hospital_idx;
        @Schema(description = "방문하는 사람의 이름", required = true, example = "김철수")
        private String name;
        @Schema(description = "방문하는 사람의 전화번호", required = true, example = "01038023921")
        private String phoneNumber;
        @Schema(description = "요청 사항", example = "잘 부탁드립니다.")
        private String content;
        @Schema(description = "예약의 승인 여부", example = "false")
        private Boolean isApproved;

    }

    @Getter
    @Setter
    @Schema(description = "Reservation 변경을 위한 dto")
    public static class updateDto {

        @Schema(description = "예약한 사람의 id", required = true, example = "2")
        private Long idx;
        @Schema(description = "방문하는 사람의 이름", required = true, example = "김철수")
        private String name;
        @Schema(description = "방문하는 사람의 전화번호", required = true, example = "01038023921")
        private String phoneNumber;
        @Schema(description = "요청 사항", example = "잘 부탁드립니다.")
        private String content;
    }
}
