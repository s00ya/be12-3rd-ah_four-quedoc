package com.example.hospital.notice.model;


import com.example.hospital.hospital.model.Hospital;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class NoticeDto {
    @Getter
    @Schema(description = "병원 공지사항 등록을 위한 dto")
    public static class Register {
        private Long id;
        @Schema(description = "공지사항 제목", required = true, example = "이번주 토요일 휴진합니다.")
        private String title;
        @Schema(description = "공지사항 내용", required = true, example = "3.8 토요일 원장선생님 일정으로 하루 휴진합니다.")
        private String content;
        @Schema(description = "작성 일자", example = "2025-03-07")
        private LocalDateTime created_at;
        @Schema(description = "병원 idx", example = "1")
        private Long hospitalId;

        public Notice toEntity(Hospital hospital) {
            Notice notice = new Notice(null, title, content, created_at, hospital);
            return notice;
        }
    }

    @Getter
    public static class Response {
        @Schema(description = "Notice 테이블의 primary key, auto_increment", example = "1")
        private Long idx;
        @Schema(description = "공지사항 제목", required = true, example = "이번주 토요일 휴진합니다.")
        private String title;
        @Schema(description = "공지사항 내용", required = true, example = "3.8 토요일 원장선생님 일정으로 하루 휴진합니다.")
        private String content;
        @Schema(description = "작성 일자", example = "2025-03-07")
        private String created_at;
        @Schema(description = "병원 idx", example = "1")
        private Long hospitalIdx;

        public static Response from(Notice notice) {
            Response res = new Response();
            res.idx = notice.getId();
            res.title = notice.getTitle();
            res.content = notice.getContent();
            res.created_at = (notice.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            res.hospitalIdx = notice.getHospital().getIdx();
            return res;
        }
    }
}


