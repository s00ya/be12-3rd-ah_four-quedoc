package com.example.apiboard.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "게시글의 정보를 저장하는 entity")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
