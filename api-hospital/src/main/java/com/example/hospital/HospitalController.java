package com.example.hospital;

import com.example.hospital.model.Hospital;
import com.example.hospital.model.HospitalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @PostMapping("/register")
    public ResponseEntity<HospitalDto.HospitalResponse> addHospital(@RequestBody HospitalDto.HospitalRequest dto) {
        HospitalDto.HospitalResponse response = hospitalService.save(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<HospitalDto.HospitalResponse>> getAllHospitals() {
        List<Hospital> hospitals = hospitalService.findAll();

        // Hospital 엔터티를 HospitalResponse DTO로 변환
        List<HospitalDto.HospitalResponse> response = hospitals.stream()
                .map(HospitalDto.HospitalResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idx}")
    public ResponseEntity findHospitalById(@PathVariable Long idx) {
        Optional<Hospital> hospital = hospitalService.findById(idx);

        return ResponseEntity.ok(hospital.get());
    }

    @PostMapping("/name")
    public ResponseEntity<List<HospitalDto.HospitalResponse>> findHospitalByName(@RequestBody HospitalDto.HospitalRequest dto) {
        List<Hospital> hospitals = hospitalService.findByName(dto.getName());

        List<HospitalDto.HospitalResponse> response = hospitals.stream()
                .map(HospitalDto.HospitalResponse::from)
                .collect(Collectors.toList());

        System.out.println("Response: " + response);

        return ResponseEntity.ok(response);
    }
}


