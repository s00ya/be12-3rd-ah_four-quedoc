package com.example.hospital.review;

import com.example.hospital.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewDto.ReviewResponse create(ReviewDto.ReviewRequest dto) {
        Review review = reviewRepository.save(dto.toEntity());
        return ReviewDto.ReviewResponse.of(review);
    }


}
