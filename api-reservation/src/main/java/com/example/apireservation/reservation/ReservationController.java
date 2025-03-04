package com.example.apireservation.reservation;


import com.example.admin.user.service.UserService;
import com.example.apireservation.reservation.model.Reservation;
import com.example.apireservation.reservation.model.ReservationDto;
import com.example.core.common.BaseResponse;
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
    public BaseResponse<String> register(@RequestBody ReservationDto.requestDto dto) {
        logger.info("Register reservation api");
            reservationService.save(Reservation.builder()
                    .user(userService.findById(dto.getUser_id()))
                    .hospital(hospitalService.findById(dto.getHospital_idx()).orElse(null))
                    .name(dto.getName())
                    .build());
        return BaseResponse.success("ok");
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
    public BaseResponse<Reservation> getReservation(@PathVariable Long reservationIdx) {
        logger.info("Get reservation api");
        Reservation reservation = reservationService.findByIdx(reservationIdx);
        return BaseResponse.success(reservation);
    }
}
