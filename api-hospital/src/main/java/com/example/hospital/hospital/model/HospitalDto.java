package com.example.hospital.hospital.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalDto {
    @Schema(description = "Hospital 테이블의 primary key, auto_increment", example = "1")
    private Long idx;

    @Getter
    @Schema(description = "Hospital 등록을 위한 dto")
    public static class HospitalRequest {

        @Schema(description = "병원 이름", example = "튼튼병원")
        private String name;
        @Schema(description = "병원 종류", example = "종합병원")
        private String type;
        @Schema(description = "주소", example = "서울시 동작구")
        private String address;
        @Schema(description = "상세 주소", example = "보라매로 87")
        private String detailAddress;
        @Schema(description = "전화번호", example = "010-0000-0000")
        private String phoneNumber;
        @Schema(description = "진료과목", example = "소아과")
        private String department;
        @Schema(description = "병원 시작 시간", example = "09:00")
        private String openTime;
        @Schema(description = "병원 종료 시간", example = "18:00")
        private String closeTime;
        private List<Integer> selectedTags;

        public Hospital toEntity() {
            return Hospital.builder()
                    .name(name)
                    .type(type)
                    .address(address)
                    .detailAddress(detailAddress)
                    .phoneNumber(phoneNumber)
                    .department(department)
                    .openTime(openTime)
                    .closeTime(closeTime)
                    .build();
        }


    }

    @Getter
    @Builder
    @Schema(description = "Hospital 조회를 위한 dto")
    public static class HospitalResponse {
        @Schema(description = "Hospital 테이블의 primary key, auto_increment", example = "1")
        private Long idx;
        @Schema(description = "병원 이름", example = "튼튼병원")
        private String name;
        @Schema(description = "주소", example = "서울시 동작구")
        private String address;
        @Schema(description = "진료과목", example = "소아과")
        private String department;
        @Schema(description = "전화번호", example = "010-0000-0000")
        private String phoneNumber;

        public static HospitalResponse from(Hospital hospital) {
            return HospitalResponse.builder()
                    .idx(hospital.getIdx())
                    .name(hospital.getName())
                    .address(hospital.getAddress())
                    .department(hospital.getDepartment())
                    .phoneNumber(hospital.getPhoneNumber())
                    .build();
        }
    }

}

