package com.example.hospital.service;

import com.example.core.common.CustomException;
import com.example.core.common.ErrorCode;
import com.example.hospital.repository.HospitalRepository;
import com.example.hospital.repository.ReviewRepository;
import com.example.hospital.model.Review;
import com.example.hospital.model.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;
    private static final Logger logger = LogManager.getLogger(ReviewService.class);

    @Transactional
    public ReviewDto.ReviewResponse create(ReviewDto.ReviewRequest dto) {
        logger.info("Create review for hospitalIdx: {}", dto.getHospitalIdx());

        hospitalRepository.findById(dto.getHospitalIdx())
                .orElseThrow(() -> new CustomException(ErrorCode.HOSPITAL_NOT_FOUND));

        Optional<Review> existingReview = reviewRepository.findByHospitalIdxAndUserIdx(dto.getHospitalIdx(), dto.getUserIdx());
        if (existingReview.isPresent()) {
            logger.error("Review already exists for hospitalIdx: {}", dto.getHospitalIdx());
            throw new CustomException(ErrorCode.REVIEW_ALREADY_EXIST);
        }

        Review review = reviewRepository.save(dto.toEntity());
        return ReviewDto.ReviewResponse.of(review);
    }

    public ReviewDto.ReviewResponse get(Long reviewIdx) {
        logger.info("get reviewIdx: {}", reviewIdx);
        try {
            Review review = reviewRepository.findById(reviewIdx)
                    .orElseThrow(() -> {
                        logger.error("No review found for reviewIdx: {}", reviewIdx);
                        return new CustomException(ErrorCode.NO_REVIEW_EXIST);
                    });

            return ReviewDto.ReviewResponse.of(review);
        } catch (Exception e) {
            logger.error("Error fetching reviewIdx: {}", reviewIdx, e);
            throw new CustomException(ErrorCode.NO_DATA);
        }
    }

    public List<ReviewDto.ReviewResponse> getList() {
        logger.info("Fetching all reviews");
        try {
            List<Review> reviewList = reviewRepository.findAll();
            if (reviewList.isEmpty()) {
                logger.warn("No reviews found");
                throw new CustomException(ErrorCode.NO_REVIEW_EXIST);
            }
            return reviewList.stream().map(ReviewDto.ReviewResponse::of).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error fetching review list", e);
            throw new CustomException(ErrorCode.NO_DATA);
        }
    }
    public List<ReviewDto.ReviewResponse> getReviewsByHospitalIdx(Long hospitalIdx) {
        List<Review> reviews = reviewRepository.findByHospitalIdx(hospitalIdx);
        return reviews.stream().map(ReviewDto.ReviewResponse::of).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long reviewIdx) {
        logger.info("Delete review with reviewIdx: {}", reviewIdx);

        Optional<Review> review = reviewRepository.findById(reviewIdx);
        if (!review.isPresent()) {
            logger.error("No review found with reviewIdx: {}", reviewIdx);
            throw new CustomException(ErrorCode.NO_REVIEW_EXIST);
        }

        try {
            reviewRepository.deleteById(reviewIdx);
        } catch (EmptyResultDataAccessException e) {
            logger.error("No review found to delete with reviewIdx: {}", reviewIdx);
            throw new CustomException(ErrorCode.NO_REVIEW_EXIST);
        } catch (Exception e) {
            logger.error("Failed to delete review with reviewIdx: {}", reviewIdx, e);
            throw new CustomException(ErrorCode.REVIEW_DELETE_FAILED);
        }
    }
}
