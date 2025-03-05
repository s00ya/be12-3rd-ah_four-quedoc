package com.example.search;

import com.example.core.common.ErrorCode;
import com.example.hospital.hospital.repository.HospitalRepository;
import com.example.hospital.hospital.model.Hospital;
import com.example.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final HospitalRepository hospitalRepository;

    public BaseResponse<List<Hospital>> searchHospitals(String keyword, String type, String specialty, String location) {
        keyword = (keyword == null) ? "" : keyword;
        type = (type == null) ? "" : type;
        specialty = (specialty == null) ? "" : specialty;
        location = (location == null) ? "" : location;

        // 데이터베이스에서 필터링된 병원 검색
        List<Hospital> filteredHospitals = hospitalRepository
                .findByTypeContainingAndNameContainingAndAddressContaining(type, specialty, location);

        if (filteredHospitals.isEmpty()) {
            return BaseResponse.error(ErrorCode.DATABASE_ERROR);
        }

        return BaseResponse.success(filteredHospitals);
    }
}
