package com.example.apireservation.reservation;


import com.example.admin.user.service.UserService;
import com.example.apireservation.reservation.model.Reservation;
import com.example.apireservation.reservation.model.ReservationDto;
import com.example.core.common.BaseResponse;
import com.example.core.common.CustomException;
import com.example.core.common.ErrorCode;
import com.example.hospital.hospital.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
@Tag(name="Reservation API", description = "Reservation API 입니다.")
public class ReservationController {
    private static final Logger logger = LogManager.getLogger(ReservationController.class);
    private final ReservationService reservationService;
    private final UserService userService;
    private final HospitalService hospitalService;

    @PostMapping("/register")
    @Operation(summary = "예약 등록", description = "예약 정보를 받아 예약을 등록하는 API입니다.")
    public BaseResponse<ReservationDto.responseDto> register(@RequestBody ReservationDto.requestDto dto) {
        logger.info("Register reservation api");
        Reservation reservation = reservationService.save(Reservation.builder()
                    .user(userService.findById(dto.getUser_id()))
                    .hospital(hospitalService.findById(dto.getHospital_idx()).orElse(null))
                    .name(dto.getName())
                    .build());
        ReservationDto.responseDto responseDto = ReservationDto.responseDto.builder()
                .user(reservation.getUser())
                .hospital(reservation.getHospital())
                .name(reservation.getName())
                .date(reservation.getTime().toString())
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
                .name(reservation.getName())
                .user(reservation.getUser())
                .hospital(reservation.getHospital())
                .date(reservation.getTime().toString())
                .build();
        return BaseResponse.success(responseDto);
    }

    @GetMapping("/update/{reservationIdx}")
    @Operation(summary = "예약 변경", description = "예약을 변경하는 API 입니다.")
    public BaseResponse<Reservation> updateReservation(@RequestBody ReservationDto.updateDto dto) {
        logger.info("Get reservation api");
        Reservation reservation = reservationService.findByIdx(dto.getIdx());
        reservation.setName(dto.getName());
        reservation.setTime(reservation.getTime());
        reservationService.save(reservation);

        return BaseResponse.success(reservation);
    }


}
