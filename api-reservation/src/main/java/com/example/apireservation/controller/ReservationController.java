package com.example.apireservation.controller;


import com.example.apireservation.service.ReservationService;
import com.example.apireservation.model.Reservation;
import com.example.apireservation.model.ReservationDto;
import com.example.core.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
@Tag(name="Reservation API", description = "Reservation API 입니다.")
public class ReservationController {
    private static final Logger logger = LogManager.getLogger(ReservationController.class);
    private final ReservationService reservationService;

    @PostMapping("/register")
    @Operation(summary = "예약 등록", description = "예약 정보를 받아 예약을 등록하는 API입니다.")
    public BaseResponse<ReservationDto.responseDto> register(@RequestBody ReservationDto.requestDto dto) {
        logger.info("Register reservation api");
        Reservation reservation = reservationService.save(Reservation.builder()
                    .userIdx(dto.getUser_idx())
                    .hospitalIdx(dto.getHospital_idx())
                    .name(dto.getName())
                    .content(dto.getContent())
                    .phoneNumber(dto.getPhoneNumber())
                    .isApproved(false)
                    .build());
        ReservationDto.responseDto responseDto = ReservationDto.responseDto.builder()
                .user_idx(reservation.getUserIdx())
                .hospital_idx(reservation.getHospitalIdx())
                .name(reservation.getName())
                .content(reservation.getContent())
                .phoneNumber(reservation.getPhoneNumber())
                .isApproved(reservation.getIsApproved())
                .build();
        return BaseResponse.success(responseDto);
    }

    @GetMapping("/delete/{reservationIdx}")
    @Operation(summary = "예약 삭제", description = "예약의 Id를 받아 해당 예약을 받아 API 입니다.")
    public BaseResponse<String> delete(@PathVariable Long reservationIdx) {
        logger.info("Delete reservation api");
        reservationService.delete(reservationIdx);
        return BaseResponse.success("ok");
    }


    @GetMapping("/get/{reservationIdx}")
    @Operation(summary = "예약 조회", description = "예약의 Id를 받아 해당 예약을 조회하는 API 입니다.")
    public BaseResponse<ReservationDto.responseDto> getReservation(@PathVariable Long reservationIdx) {
        logger.info("Get reservation api");
        Reservation reservation = reservationService.findByIdx(reservationIdx);
        ReservationDto.responseDto responseDto = ReservationDto.responseDto.builder()
                .idx(reservation.getIdx())
                .name(reservation.getName())
                .user_idx(reservation.getUserIdx())
                .hospital_idx(reservation.getHospitalIdx())
                .phoneNumber(reservation.getPhoneNumber())
                .content(reservation.getContent())
                .isApproved(reservation.getIsApproved())
                .build();
        return BaseResponse.success(responseDto);
    }

    @GetMapping("/update/{reservationIdx}")
    @Operation(summary = "예약 변경", description = "예약을 변경하는 API 입니다.")
    public BaseResponse<Reservation> updateReservation(@RequestBody ReservationDto.updateDto dto) {
        logger.info("Get reservation api");
        Reservation reservation = reservationService.findByIdx(dto.getIdx());
        reservation.setName(dto.getName());
        reservation.setPhoneNumber(dto.getPhoneNumber());
        reservation.setContent(dto.getContent());
        reservationService.save(reservation);

        return BaseResponse.success(reservation);
    }

    @GetMapping("/getAllReservation/{hospitalIdx}")
    @Operation(summary = "병원의 예약 조회", description = "병원의 Id를 받아 병원의 모든 예약을 조회하는 API 입니다.")
    public BaseResponse<List<ReservationDto.responseDto>> getReservationList(@PathVariable Long hospitalIdx) {
        logger.info("Get all reservation api");
        List<Reservation> reservation = reservationService.findByHospitalId(hospitalIdx);

        List<ReservationDto.responseDto> responseDtoList =  reservation.stream().map(reservationDto -> ReservationDto.responseDto.builder()
                .idx(reservationDto.getIdx())
                .name(reservationDto.getName())
                .user_idx(reservationDto.getUserIdx())
                .hospital_idx(reservationDto.getHospitalIdx())
                .phoneNumber(reservationDto.getPhoneNumber())
                .content(reservationDto.getContent())
                .isApproved(reservationDto.getIsApproved())
                .build()).toList();

        return BaseResponse.success(responseDtoList);
    }


}
