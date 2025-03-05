package com.example.hospital.review;

import com.example.hospital.hospital.model.Hospital;
import com.example.hospital.hospital.repository.HospitalRepository;
import com.example.hospital.review.model.Review;
import com.example.hospital.review.model.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewDto.ReviewResponse create(ReviewDto.ReviewRequest dto) {
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new RuntimeException("병원을 찾을 수 없습니다."));

        Review review = reviewRepository.save(dto.toEntity(hospital));
        return ReviewDto.ReviewResponse.of(review);
    }

    public ReviewDto.ReviewResponse get(Long reviewIdx) {
        Review review = reviewRepository.findById(reviewIdx).orElseThrow();

        return ReviewDto.ReviewResponse.of(review);
    }

    public List<ReviewDto.ReviewResponse> getList() {
        List<Review> reviewList = reviewRepository.findAll();
        return reviewList.stream().map(ReviewDto.ReviewResponse::of).toList();


    }
}
