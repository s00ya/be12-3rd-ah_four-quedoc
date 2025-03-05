package com.example.payment;

import com.example.apireservation.reservation.ReservationRepository;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public PaymentDto.PaymentResponse saveById(Long reservationIdx, PaymentDto.PaymentRequest dto) {
        Payment payment = Payment.builder()
                .amount(dto.getAmount())
                .status("미결제")
                .reservationIdx(reservationIdx)
                .build();
        return PaymentDto.PaymentResponse.from(paymentRepository.save(payment));
    }

    public List<PaymentDto.PaymentResponse> getList() {
        List<PaymentDto.PaymentResponse> list = paymentRepository.findAll().stream()
                .map(PaymentDto.PaymentResponse::from)
                .collect(Collectors.toList());
        return list;
    }

    public void findByreservationIdx(Long reservationIdx) {
        reservationRepository.findById(reservationIdx);

    }
}
