package com.example.payment;

import com.example.core.common.BaseResponse;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create/{reservationIdx}")
    public BaseResponse<PaymentDto.PaymentResponse> create(
            @PathVariable("reservationIdx") Long reservationIdx,
            @RequestBody PaymentDto.PaymentRequest dto) {

        PaymentDto.PaymentResponse p = paymentService.saveById(reservationIdx, dto);
        return BaseResponse.success(p);
    }

    @GetMapping("/list")
    public BaseResponse<List<PaymentDto.PaymentResponse>> list() {

        List<PaymentDto.PaymentResponse> p = paymentService.getList();
        return BaseResponse.success(p);
    }

    // 예약 아이디를 토대로 찾기 - 수정 예정
    @GetMapping("/{reservationIdx}")
    public void findByUserIdx(@PathVariable("reservationIdx") Long reservationIdx) {
        paymentService.findByreservationIdx(reservationIdx);
    }
}
