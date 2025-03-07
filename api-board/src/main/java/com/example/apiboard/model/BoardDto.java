package com.example.apiboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

public class BoardDto {

    @Getter
    @Schema(description = "board를 저장할때 사용하는 dto입니다.")
    public static class requestDto {

        @Schema(description = "게시글의 제목", required = true, example = "게시글제목")
        private String title;
        @Schema(description = "게시글의 내용", required = true, example = "게시글의 내용입니다.")
        private String content;
        @Schema(description = "게시글의 작성자", required = true, example = "홍길동")
        private String author;

    }

    @Getter
    @Setter
    @Builder
    @Schema(description = "board 정보를 반환할때 사용하는 dto입니다.")
    public static class responseDto {

        @Schema(description = "board의 id Long type Auto_increment", required = true,example = "1")
        private Long idx;
        @Schema(description = "게시글의 제목", required = true, example = "게시글제목")
        private String title;
        @Schema(description = "게시글의 내용", required = true, example = "게시글의 내용입니다.")
        private String content;
        @Schema(description = "게시글의 작성자", required = true, example = "홍길동")
        private String author;
        @Schema(description = "게시글의 작성 시각", required = true, example = "2025-03-06 14:23:03")
        private LocalDateTime createdAt;

        public responseDto(Board board) {
            this.idx = board.getIdx();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.author = board.getAuthor();
            this.createdAt = board.getCreatedAt();
        }
    }

}
