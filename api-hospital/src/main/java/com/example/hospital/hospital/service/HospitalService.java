package com.example.hospital.hospital.service;

import com.example.hospital.hospital.model.Hospital;
import com.example.hospital.hospital.model.HospitalDetailDto;
import com.example.hospital.hospital.model.HospitalDto;
import com.example.hospital.hospital.repository.HospitalRepository;
import com.example.hospital.notice.service.NoticeService;
import com.example.hospital.notice.model.NoticeDto;
import com.example.hospital.review.service.ReviewService;
import com.example.hospital.review.model.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    private final ReviewService reviewService;
    private final NoticeService noticeService;

    public HospitalDto.HospitalResponse save(HospitalDto.HospitalRequest dto) {
        Hospital hospital = hospitalRepository.save(dto.toEntity());
        return HospitalDto.HospitalResponse.from(hospital);
    }

    public List<Hospital> findAll() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitals;
    }

    public Optional<Hospital> findById(Long idx) {
        Optional<Hospital> hospital = hospitalRepository.findById(idx);
        return hospital;
    }

    public List<Hospital> findByName(String name) {
        System.out.println("HospitalService.findByName " + name.trim());
        return hospitalRepository.findByNameContaining(name);
    }

    public HospitalDetailDto.HospitalDetailResponse findHospitalByIdx(Long hospitalIdx) {

        Hospital hospital = hospitalRepository.findById(hospitalIdx)
                .orElseThrow(() -> new RuntimeException("병원을 찾을 수 없습니다."));

        List<ReviewDto.ReviewResponse> reviews = reviewService.getReviewsByHospitalIdx(hospitalIdx);
        List<NoticeDto.Response> notices = noticeService.getNoticesByHospitalIdx(hospitalIdx);

        return HospitalDetailDto.HospitalDetailResponse.from(hospital, reviews, notices);
    }

}

