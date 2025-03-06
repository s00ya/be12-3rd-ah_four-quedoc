package com.example.apireservation.reservation.model;


import com.example.admin.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import com.example.hospital.hospital.model.Hospital;
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
    private String phoneNumber;

    private String content;

    @Column(nullable = false)
    private Boolean isApproved;

}
