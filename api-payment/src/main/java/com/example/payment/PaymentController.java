package com.example.payment;

import com.example.apireservation.reservation.model.Reservation;
import com.example.apireservation.reservation.model.ReservationDto;
import com.example.core.common.BaseResponse;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
@Tag(name="결제 API", description = "Payment API 입니다.")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create/{reservationIdx}")
    @Operation(summary = "결제 정보 등록", description = "예약 idx를 입력받아 결제 초기 정보를 등록하는 API입니다.")
    public BaseResponse<PaymentDto.PaymentResponse> create(
            @PathVariable("reservationIdx") Long reservationIdx,
            @RequestBody PaymentDto.PaymentRequest dto) {

        PaymentDto.PaymentResponse p = paymentService.saveById(reservationIdx, dto);
        return BaseResponse.success(p);
    }

    @PostMapping("/update/{reservationIdx}")
    @Operation(
            summary = "결제 정보 업데이트",
            description = "예약 idx를 입력받아 결제 정보를 수정하는 API입니다. 결제 상태와 결제 방법 등을 업데이트합니다. 결제 정보를 성공적으로 처리하면 업데이트된 결제 정보를 반환합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "결제 정보 수정",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n  \"paymentMethod\": \"BANK_TRANSFER\"}")
                    )
            )
    )
    public BaseResponse<PaymentDto.PaymentResponse> update(
            @PathVariable("reservationIdx") Long reservationIdx,
            @RequestBody PaymentDto.PaymentRequest dto) {
        PaymentDto.PaymentResponse p = paymentService.processPayment(reservationIdx, dto);
        return BaseResponse.success(p);
    }

    @GetMapping("/list")
    @Operation(summary = "전체 결제 리스트 보기", description = "전체 결제 정보를 리스트로 보여주는 API입니다.")
    public BaseResponse<List<PaymentDto.PaymentResponse>> list() {

        List<PaymentDto.PaymentResponse> p = paymentService.getList();
        return BaseResponse.success(p);
    }

    // 예약 아이디를 토대로 찾기 - 수정 예정
    @GetMapping("/find/{reservationIdx}")
    @Operation(summary = "결제 상세 정보", description = "예약 idx를 입력받아 해당하는 예약 정보 및 결제 정보를 보는 API입니다.")
    public BaseResponse<PaymentDto.PaymentResponse> findByUserIdx(@PathVariable("reservationIdx") Long reservationIdx) {
        return paymentService.findByReservationIdx(reservationIdx);

    }
}
