package com.example.hospital.review;

import com.example.hospital.review.model.Review;
import lombok.Builder;
import lombok.Getter;


public class ReviewDto {
    @Getter
    public static class ReviewRequest {
        private String title;
        private String contents;
        private String nickName;
        private String passWord;
        private int score;
        private boolean isPublic;

        public Review toEntity() {
            return Review.builder()
                    .title(title)
                    .contents(contents)
                    .nickName(nickName)
                    .passWord(passWord)
                    .score(score)
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
        private boolean isPublic;

        public static ReviewResponse of(Review entity) {
            return ReviewResponse.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .nickName(entity.getNickName())
                    .passWord(entity.getPassWord())
                    .score(entity.getScore())
                    .isPublic(entity.isPublic())
                    .build();
        }
    }

}
