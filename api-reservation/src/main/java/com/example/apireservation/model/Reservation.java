package com.example.apireservation.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Schema(description = "예약 정보를 저장하는 Reservation Entity")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Reservation 테이블의 primary key, auto_increment", example = "1")
    private long idx;

    @Column(nullable = false)
    @Schema(description = "예약된 병원의 primary key", example = "1")
    private Long hospitalIdx;

    @Column(nullable = false)
    @Schema(description = "예약한 사람의 primary key", example = "1")
    private Long userIdx;

    @Column(nullable = false)
    @Schema(description = "병원 방문자의 이름", example = "홍길동")
    private String name;

    @Column(nullable = false)
    @Schema(description = "병원 방문자의 전화번호", example = "01049282912")
    private String phoneNumber;

    @Schema(description = "요청사항", example = "잘 부탁드립니다.")
    private String content;

    @Column(nullable = false)
    @Schema(description = "예약 승인 여부", example = "false")
    private Boolean isApproved;

}
