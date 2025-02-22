package com.example.hospital.review;

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

    public ReviewDto.ReviewResponse create(ReviewDto.ReviewRequest dto) {
        Review review = reviewRepository.save(dto.toEntity());
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
