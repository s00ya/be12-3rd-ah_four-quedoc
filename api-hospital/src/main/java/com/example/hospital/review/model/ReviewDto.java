package com.example.hospital.review.model;

import com.example.hospital.hospital.model.Hospital;
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
        private int score;
        private boolean isPublic = true;
        private Long hospitalId;

        public Review toEntity(Hospital hospital) {
            return Review.builder()
                    .title(title)
                    .contents(contents)
                    .nickName(nickName)
                    .passWord(passWord)
                    .score(score)
                    .createdAt(LocalDate.now())
                    .isPublic(isPublic)
                    .hospital(hospital)
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
        private int score;
        private LocalDate createdAt;
        private boolean isPublic;
        private Long hospitalIdx;

        public static ReviewResponse of(Review entity) {
            return ReviewResponse.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .nickName(entity.getNickName())
                    .score(entity.getScore())
                    .createdAt(entity.getCreatedAt())
                    .isPublic(entity.isPublic())
                    .hospitalIdx(entity.getIdx())
                    .build();
        }
    }

}
