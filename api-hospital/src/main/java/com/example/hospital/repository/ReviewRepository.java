package com.example.hospital.repository;

import com.example.hospital.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHospitalIdx(Long hospitalIdx);
    Optional<Review> findByHospitalIdxAndUserIdx(Long hospitalIdx, Long userIdx);
}
