package com.example.search;

import com.example.hospital.hospital.model.Hospital;
import com.example.core.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/hospitals")
    public BaseResponse<List<Hospital>> searchHospitals(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String location) {

        // 병원 검색 서비스 호출
        return searchService.searchHospitals(keyword, type, specialty, location);
    }
}
