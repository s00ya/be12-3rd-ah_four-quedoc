package com.example.search.controller;

import com.example.core.common.BaseResponse;
import com.example.hospital.model.HospitalDto;
import com.example.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // ✅ 전체 병원 조회.
    @GetMapping("/list")
    public BaseResponse<List<HospitalDto.HospitalResponse>> getAllHospitals() {
        return searchService.findAllHospitals();
    }

    // ✅ 병원명 검색
    @GetMapping("/search/name")
    public BaseResponse<List<HospitalDto.HospitalResponse>> findHospitalByName(@RequestParam String name) {
        return searchService.findHospitalByName(name);
    }

    // ✅ 진료 과목(type)으로 검색 (최대 5개)
    @GetMapping("/search/type")
    public BaseResponse<List<HospitalDto.HospitalResponse>> findByType(@RequestParam String type) {
        return searchService.findByTypeContaining(type);
    }

    // ✅ 병원 종류(department)로 검색 (최대 5개)
    @GetMapping("/search/department")
    public BaseResponse<List<HospitalDto.HospitalResponse>> findByDepartment(@RequestParam String department) {
        return searchService.findDepartmentContaining(department);
    }

    // ✅ 지역(address)으로 검색 (최대 5개)
    @GetMapping("/search/address")
    public BaseResponse<List<HospitalDto.HospitalResponse>> findByAddress(@RequestParam String address) {
        return searchService.findAddressContaining(address);
    }
}
