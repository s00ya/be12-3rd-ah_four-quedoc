package com.example.hospital.notice;

import com.example.hospital.notice.model.Notice;
import com.example.hospital.notice.model.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public void register(NoticeDto.Register dto) {
        noticeRepository.save(dto.toEntity());
    }

    public List<Notice> list() {
        List<Notice> NoticeList = noticeRepository.findAll();

        return NoticeList;
    }

    public NoticeDto.Response read(Long idx) {
        Optional<Notice> result = noticeRepository.findById(idx);

        if (result.isPresent()) {
            Notice templete = result.get();

            return NoticeDto.Response.from(templete);
        }
        return null;
    }

    public NoticeDto.Response searchByName(String hospitalId) {
        Notice notice = noticeRepository.findByHospitalId(hospitalId).orElseThrow();

        return NoticeDto.Response.from(notice);
    }
}
