package com.example.search;

import com.example.hospital.model.Hospital;
import com.example.hospital.model.HospitalDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Hospital, Long> {
    // 이름 검색 (부분 검색)
    List<Hospital> findByNameContaining(String name);

    // 특정 주소 내 병원 찾기
    List<Hospital> findByAddressContaining(String address);
}
