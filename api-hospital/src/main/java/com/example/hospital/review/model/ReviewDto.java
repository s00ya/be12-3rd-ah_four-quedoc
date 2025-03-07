package com.example.hospital.review.model;

import com.example.hospital.hospital.model.Hospital;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


public class ReviewDto {
    @Getter
    @Schema(description = "Review 등록을 위한 dto")
    public static class ReviewRequest {
        @Schema(description = "제목", example = "친절하고 좋아요.")
        private String title;
        @Schema(description = "내용", example = "김철수 선생님 친절하시고 진료를 잘 봐주십니다.")
        private String contents;
//        @Schema(description = "닉네임", example = "스프링")
//        private String nickName;
        private String passWord;
        @Schema(description = "별점", example = "5")
        private int score;
        @Schema(description = "공개글", example = "true")
        private boolean isPublic = true;
        @Schema(description = "병원 idx", example = "1")
        private Long hospitalIdx;
        @Schema(description = "사용자 idx", example = "1")
        private Long userIdx;

        public Review toEntity() {
            return Review.builder()
                    .title(title)
                    .contents(contents)
                    .passWord(passWord)
                    .score(score)
                    .createdAt(LocalDate.now())
                    .isPublic(isPublic)
                    .hospitalIdx(hospitalIdx)
                    .userIdx(userIdx)
                    .build();
        }
    }

    @Getter
    @Builder
    @Schema(description = "Review 조회을 위한 dto")
    public static class ReviewResponse {
        @Schema(description = "Review 테이블의 primary key, auto_increment", example = "1")
        private Long idx;
        @Schema(description = "제목", example = "친절하고 좋아요.")
        private String title;
        @Schema(description = "내용", example = "김철수 선생님 친절하시고 진료를 잘 봐주십니다.")
        private String contents;
//        @Schema(description = "닉네임", example = "스프링")
//        private String nickName;
        @Schema(description = "별점", example = "5")
        private int score;
        @Schema(description = "작성일", example = "2025-03-06")
        private LocalDate createdAt;
        @Schema(description = "공개글", example = "true")
        private boolean isPublic;
        @Schema(description = "병원 idx", example = "1")
        private Long hospitalIdx;
        @Schema(description = "사용자 idx", example = "1")
        private Long userIdx;

        public static ReviewResponse of(Review entity) {
            return ReviewResponse.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .score(entity.getScore())
                    .createdAt(entity.getCreatedAt())
                    .isPublic(entity.isPublic())
                    .hospitalIdx(entity.getHospitalIdx())
                    .userIdx(entity.getUserIdx())
                    .build();
        }
    }

}
