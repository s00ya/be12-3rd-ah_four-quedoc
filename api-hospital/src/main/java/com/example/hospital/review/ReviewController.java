package com.example.hospital.review;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 저장
    @PostMapping("/create")
    public ResponseEntity<ReviewDto.ReviewResponse> create (
            @RequestBody ReviewDto.ReviewRequest dto) {
        ReviewDto.ReviewResponse response = reviewService.create(dto);
        return ResponseEntity.ok(response);
    }

}
