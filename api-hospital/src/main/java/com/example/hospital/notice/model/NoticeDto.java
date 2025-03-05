package com.example.hospital.notice.model;


import com.example.hospital.hospital.model.Hospital;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class NoticeDto {
    @Getter
    public static class Register {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime created_at;
        private Long hospitalId;

        public Notice toEntity(Hospital hospital) {
            Notice notice = new Notice(null, title, content, created_at, hospital);
            return notice;
        }
    }

    @Getter
    public static class Response {
        private Long idx;
        private String title;
        private String content;
        private String created_at;
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


