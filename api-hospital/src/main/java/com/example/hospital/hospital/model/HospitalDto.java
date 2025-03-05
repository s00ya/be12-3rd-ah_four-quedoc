package com.example.hospital.hospital.model;

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
    private Long idx;

    @Getter
    public static class HospitalRequest {
        private String name;
        private String type;
        private String address;
        private String detailAddress;
        private String phoneNumber;
        private String department;
        private String openTime;
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
    public static class HospitalResponse {
        private Long idx;
        private String name;
        private String address;
        private String department;
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

