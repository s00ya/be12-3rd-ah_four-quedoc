package com.example.hospital.notice.service;

import com.example.hospital.hospital.model.Hospital;
import com.example.hospital.hospital.repository.HospitalRepository;
import com.example.hospital.notice.repository.NoticeRepository;
import com.example.hospital.notice.model.Notice;
import com.example.hospital.notice.model.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final HospitalRepository hospitalRepository;

    public void register(NoticeDto.Register dto) {
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new RuntimeException("병원을 찾을 수 없습니다."));

        Notice notice = dto.toEntity(hospital);

        noticeRepository.save(notice);
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

    public NoticeDto.Response searchByHospitalId(Long hospitalId) {
        Notice notice = noticeRepository.findByHospital_Idx(hospitalId)
                .orElseThrow(() -> new RuntimeException("해당 병원의 공지사항을 찾을 수 없습니다."));
        return NoticeDto.Response.from(notice);
    }

    public List<NoticeDto.Response> getNoticesByHospitalIdx(Long hospitalIdx) {
        List<Notice> notices = noticeRepository.findByHospitalIdx(hospitalIdx);
        if (notices.isEmpty()) {
            throw new RuntimeException("해당 병원의 공지사항을 찾을 수 없습니다.");
        }
        return notices.stream().map(NoticeDto.Response::from).collect(Collectors.toList());
    }
}
