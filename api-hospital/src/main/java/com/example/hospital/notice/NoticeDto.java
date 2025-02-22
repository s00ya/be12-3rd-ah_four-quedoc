package com.example.hospital.notice;


import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class NoticeDto {
    @Getter
    public static class Register {
        private Long id;
        private String title;
        private String content;
        private String hospitalId;
        private LocalDateTime created_at;

        public Notice toEntity() {
            Notice notice = new Notice(null, title, content, hospitalId, created_at);
            return notice;
        }
    }

    @Getter
    public static class Response {
        private Long idx;
        private String title;
        private String content;
        private String hospitalId;
        private String created_at;

        public static Response from(Notice notice) {
            Response res = new Response();
            res.idx = notice.getId();
            res.title = notice.getTitle();
            res.content = notice.getContent();
            res.created_at = (notice.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            res.hospitalId = notice.getHospitalId();
            return res;
        }
    }
}


