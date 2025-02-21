package com.example.hospital.model;

import com.example.hospital.HospitalApplication;
import com.example.hospital.review.model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalDto {
    private Long idx;

    @Getter
    public static class HospitalRequest {
        private String name;
        private String address;
        private String department;
        private String phone;

        public Hospital toEntity() {
            return Hospital.builder()
                    .name(name)
                    .address(address)
                    .department(department)
                    .phone(phone)
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
        private String phone;

        public static HospitalResponse from(Hospital hospital) {
            return HospitalResponse.builder()
                    .idx(hospital.getIdx())
                    .name(hospital.getName())
                    .address(hospital.getAddress())
                    .department(hospital.getDepartment())
                    .phone(hospital.getPhone())
                    .build();
        }
    }

}

