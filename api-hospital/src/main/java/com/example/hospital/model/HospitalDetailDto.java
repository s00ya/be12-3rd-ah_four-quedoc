package com.example.hospital.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class HospitalDetailDto {
    @Getter
    @Builder
    public static class HospitalDetailResponse {
        private HospitalDto.HospitalResponse hospital; // 병원 기본 정보
        private String type;
        private String detailAddress;
        private String openTime;
        private String closeTime;
        private List<ReviewDto.ReviewResponse> reviews;
        private List<NoticeDto.Response> notices;

        public static HospitalDetailResponse from(Hospital hospital, List<ReviewDto.ReviewResponse> reviews, List<NoticeDto.Response> notices) {
            return HospitalDetailResponse.builder()
                    .hospital(HospitalDto.HospitalResponse.from(hospital)) // 기존 `HospitalResponse` 활용
                    .type(hospital.getType())
                    .detailAddress(hospital.getDetailAddress())
                    .openTime(hospital.getOpenTime())
                    .closeTime(hospital.getCloseTime())
                    .reviews(reviews)
                    .notices(notices)
                    .build();
        }
    }
}