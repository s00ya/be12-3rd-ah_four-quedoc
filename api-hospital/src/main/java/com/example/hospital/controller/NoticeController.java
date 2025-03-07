package com.example.hospital.controller;

import com.example.hospital.model.Notice;
import com.example.hospital.model.NoticeDto;
import com.example.hospital.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@Tag(name = "Notice API", description = "Notice API 입니다.")
public class NoticeController {
    private final NoticeService noticeService;

    @Operation(summary = "병원 공지사항 조회", description = "idx로 공지사항을 조회하는 API입니다.")
    @GetMapping("/read/{idx}")
    public ResponseEntity<NoticeDto.Response> read(@PathVariable Long idx) {
        NoticeDto.Response response = noticeService.read(idx);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "병원 공지사항 리스트 조회", description = "병원 공지사항 리스트를 조회하는 API입니다.")
    @GetMapping("/list")
    public ResponseEntity<List<Notice>> list() {
        List<Notice> templeteResList = noticeService.list();

        return ResponseEntity.ok(templeteResList);
    }

    @Operation(summary = "병원별 공지사항 조회", description = "병원 idx로 병원 별 공지사항을 조회하는 API입니다.")
    @GetMapping("/search")
    public ResponseEntity<NoticeDto.Response> search(@RequestParam Long hospitalId) {
        NoticeDto.Response response = noticeService.searchByHospitalId(hospitalId);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "병원 공지사항 등록", description = "병원 관계자 유저에게 공지사항 정보를 받아 등록하는 API입니다.")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody NoticeDto.Register dto ) {
        noticeService.register(dto);
        return ResponseEntity.ok("성공");
    }
}
