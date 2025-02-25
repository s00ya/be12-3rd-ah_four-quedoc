package com.example.search;

import com.example.hospital.model.Hospital;
import com.example.hospital.model.HospitalDto;
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

    // 모든 병원 조회
    @GetMapping
    public List<Hospital> getAllSearch() {
        return searchService.getAllSearch();
    }

    // 이름으로 병원 검색
    @GetMapping("/search")
    public List<Hospital> searchByName(@RequestParam String name) {
        return searchService.searchByName(name);
    }

    // 주소로 병원 검색
    @GetMapping("/search/address")
    public List<Hospital> searchByAddress(@RequestParam String address) {
        return searchService.searchByAddress(address);
    }
}