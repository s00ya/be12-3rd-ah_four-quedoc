package com.example.hospital.review;


import com.example.hospital.review.model.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<ReviewDto.ReviewResponse> create (
            @RequestBody ReviewDto.ReviewRequest dto) {
        ReviewDto.ReviewResponse response = reviewService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/read/{reviewIdx}")
    public ResponseEntity<ReviewDto.ReviewResponse> read (
            @PathVariable Long reviewIdx) {
        ReviewDto.ReviewResponse response = reviewService.get(reviewIdx);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReviewDto.ReviewResponse>> list () {
        List<ReviewDto.ReviewResponse> response = reviewService.getList();
        return ResponseEntity.ok(response);
    }







}
