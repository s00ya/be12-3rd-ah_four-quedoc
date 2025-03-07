package com.example.hospital.hospital.model;

import com.example.hospital.notice.model.Notice;
import com.example.hospital.review.model.Review;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "병원 등록 정보를 저장하는 Hospital Entity")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Hospital 테이블의 primary key, auto_increment", example = "1")
    private Long idx;

    @Column(nullable = false, length = 100)
    @Schema(description = "병원 이름", example = "튼튼병원")
    private String name;

    @Column(nullable = false, length = 255)
    @Schema(description = "주소", example = "서울시 동작구 보라매로 87")
    private String address;

    @Column(nullable = true)
    @Schema(description = "진료과목", example = "소아과")
    private String department;

    @Column(nullable = false)
    @Schema(description = "전화번호", example = "010-0000-0000")
    private String phoneNumber;

    @Column(nullable = false)
    @Schema(description = "병원 종류", example = "종합병원")
    private String type;

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    @Schema(description = "병원 시작 시간", example = "09:00")
    private String openTime;

    @Column(nullable = false)
    @Schema(description = "병원 종료 시간", example = "18:00")
    private String closeTime;


}
