package com.example.search;

import com.example.core.common.BaseResponse;
import com.example.core.common.ErrorCode;
import com.example.hospital.hospital.model.Hospital;
import com.example.hospital.hospital.model.HospitalDto;
import com.example.hospital.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final HospitalRepository hospitalRepository;
    private static final int LIMIT = 5; // 검색 결과 제한 개수

    // ✅ 전체 병원 조회
    public BaseResponse<List<HospitalDto.HospitalResponse>> findAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
    //예외처리
        if (hospitals.isEmpty()) {
            return BaseResponse.error(ErrorCode.NO_DATA);
        }

        List<HospitalDto.HospitalResponse> response = hospitals.stream()
                .map(HospitalDto.HospitalResponse::from)
                .collect(Collectors.toList());

        return BaseResponse.success(response);
    }

    // ✅ 진료 과목(type)으로 조회 (최대 5개)
    public BaseResponse<List<HospitalDto.HospitalResponse>> findByTypeContaining(String type) {
        List<Hospital> hospitals = hospitalRepository.findByTypeContaining(type,PageRequest.of(0, 5));

        if (hospitals.isEmpty()) {
            return BaseResponse.error(ErrorCode.NO_DATA);
        }

        List<HospitalDto.HospitalResponse> response = hospitals.stream()
                .map(HospitalDto.HospitalResponse::from)
                .collect(Collectors.toList());

        return BaseResponse.success(response);
    }

    // ✅ 병원 종류(department)로 조회 (여러 개 가능)
    public BaseResponse<List<HospitalDto.HospitalResponse>> findDepartmentContaining(String department) {
        List<Hospital> hospitals = hospitalRepository.findByDepartmentContaining(department,PageRequest.of(0, 5));

        if (hospitals.isEmpty()) {
            return BaseResponse.error(ErrorCode.NO_DATA);
        }

        List<HospitalDto.HospitalResponse> response = hospitals.stream()
                .map(HospitalDto.HospitalResponse::from)
                .collect(Collectors.toList());

        return BaseResponse.success(response);
    }

    // ✅ 지역(address)으로 조회 (여러 개 가능)
    public BaseResponse<List<HospitalDto.HospitalResponse>> findAddressContaining(String address) {
        List<Hospital> hospitals = hospitalRepository.findByAddressContaining(address,PageRequest.of(0, 5));

        if (hospitals.isEmpty()) {
            return BaseResponse.error(ErrorCode.NO_DATA);
        }

        List<HospitalDto.HospitalResponse> response = hospitals.stream()
                .map(HospitalDto.HospitalResponse::from)
                .collect(Collectors.toList());

        return BaseResponse.success(response);
    }

    // ✅ 병원명 검색 (여러 개 가능)
    public BaseResponse<List<HospitalDto.HospitalResponse>> findHospitalByName(String name) {
        List<Hospital> hospitals = hospitalRepository.findAllByName(name);

        if (hospitals.isEmpty()) {
            return BaseResponse.error(ErrorCode.NO_DATA);
        }

        List<HospitalDto.HospitalResponse> response = hospitals.stream()
                .map(HospitalDto.HospitalResponse::from)
                .collect(Collectors.toList());

        return BaseResponse.success(response);
    }
}