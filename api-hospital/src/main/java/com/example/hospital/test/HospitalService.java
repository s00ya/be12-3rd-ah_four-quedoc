package com.example.hospital.test;

import com.example.hospital.model.Hospital;
import com.example.hospital.model.HospitalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

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

}

