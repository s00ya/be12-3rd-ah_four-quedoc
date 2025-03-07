package com.example.payment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@Builder
public class PaymentDto {

    @Getter
    @Schema(description = "PaymentRequest - 결제 요청 정보")
    public static class PaymentRequest {

        @Schema(description = "결제 금액", example = "100.0", required = true)
        private Double amount;

        @Schema(description = "결제 날짜", example = "2025-03-01T10:00", required = true)
        private LocalDateTime paymentDate;

        @Schema(description = "결제 방법", example = "BANK_TRANSFER", required = true)
        private PaymentMethod paymentMethod;

        @Schema(description = "결제 상태", example = "UNCOMPLETED", required = false)
        private PaymentStatus status;

        private Long reservationIdx;

        public Payment toEntity(Long reservationIdx) {
            return Payment.builder()
                    .amount(amount)
                    .paymentDate(paymentDate)
                    .paymentMethod(paymentMethod)
                    .status(status)
                    .reservationIdx(reservationIdx)
                    .build();
        }
    }

    @Getter
    @Builder
    @Schema(description = "PaymentResponse - 결제 응답 정보")
    public static class PaymentResponse {

        @Schema(description = "결제 IDX", example = "1", required = true)
        private Long idx;

        @Schema(description = "결제 금액", example = "100.0", required = true)
        private Double amount;

        @Schema(description = "결제 날짜", example = "2025-03-01T10:00", required = true)
        private LocalDateTime paymentDate;

        @Schema(description = "결제 방법", example = "BANK_TRANSFER", required = true)
        private PaymentMethod paymentMethod;

        @Schema(description = "결제 상태", example = "UNCOMPLETED", required = true)
        private PaymentStatus status;

        @Schema(description = "예약 IDX", example="1L", required = true)
        private Long reservationIdx;

        public static PaymentResponse from(Payment payment) {
            return PaymentResponse.builder()
                    .idx(payment.getIdx())
                    .amount(payment.getAmount())
                    .paymentDate(payment.getPaymentDate())
                    .paymentMethod(payment.getPaymentMethod())
                    .reservationIdx(payment.getReservationIdx())
                    .status(payment.getStatus())
                    .build();
        }
    }
}
