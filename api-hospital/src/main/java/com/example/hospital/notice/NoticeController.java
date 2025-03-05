package com.example.hospital.notice;

import com.example.hospital.notice.model.Notice;
import com.example.hospital.notice.model.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping("/read/{idx}")
    public ResponseEntity<NoticeDto.Response> read(@PathVariable Long idx) {
        NoticeDto.Response response = noticeService.read(idx);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Notice>> list() {
        List<Notice> templeteResList = noticeService.list();

        return ResponseEntity.ok(templeteResList);
    }

    @GetMapping("/search")
    public ResponseEntity<NoticeDto.Response> search(@RequestParam Long hospitalId) {
        NoticeDto.Response response = noticeService.searchByHospitalId(hospitalId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody NoticeDto.Register dto ) {
        noticeService.register(dto);
        return ResponseEntity.ok("성공");
    }
}
