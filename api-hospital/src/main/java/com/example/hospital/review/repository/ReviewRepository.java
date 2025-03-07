package com.example.hospital.review.repository;

import com.example.hospital.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHospitalIdx(Long hospitalIdx);
    Optional<Review> findByHospitalIdxAndUserIdx(Long hospitalIdx, Long userIdx);
}
