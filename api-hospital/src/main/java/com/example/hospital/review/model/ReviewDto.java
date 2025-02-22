package com.example.hospital.review.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


public class ReviewDto {
    @Getter
    public static class ReviewRequest {
        private String title;
        private String contents;
        private String nickName;
        private String passWord;
        private LocalDate createdAt;
        private int score;
        private boolean isPublic;

        public Review toEntity() {
            return Review.builder()
                    .title(title)
                    .contents(contents)
                    .nickName(nickName)
                    .passWord(passWord)
                    .score(score)
                    .createdAt(createdAt)
                    .isPublic(isPublic)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ReviewResponse {
        private Long idx;
        private String title;
        private String contents;
        private String nickName;
        private String passWord;
        private int score;
        private LocalDate createdAt;
        private boolean isPublic;

        public static ReviewResponse of(Review entity) {
            return ReviewResponse.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .nickName(entity.getNickName())
                    .passWord(entity.getPassWord())
                    .score(entity.getScore())
                    .createdAt(entity.getCreatedAt())
                    .isPublic(entity.isPublic())
                    .build();
        }
    }

}
