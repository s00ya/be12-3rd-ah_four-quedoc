package com.example.search;

import com.example.hospital.hospital.model.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final SearchRepository searchRepository;

    // 모든 병원 조회
    public List<Hospital> getAllSearch() {
        return searchRepository.findAll();
    }

    // 이름으로 검색
    public List<Hospital> searchByName(String name) {
        return searchRepository.findByNameContaining(name);
    }

    // 주소로 검색
    public List<Hospital> searchByAddress(String address) {
        return searchRepository.findByAddressContaining(address);
    }

}