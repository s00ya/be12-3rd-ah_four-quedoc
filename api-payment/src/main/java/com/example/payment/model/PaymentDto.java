package com.example.payment.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
public class PaymentDto {

    @Getter
    public static class PaymentRequest {
        private Double amount;
        private LocalDateTime paymentDate;
        private String paymentMethod;
        private String status;

        public Payment toEntity() {
            return Payment.builder()
                    .amount(amount)
                    .paymentDate(paymentDate)
                    .paymentMethod(paymentMethod)
                    .status(status)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class PaymentResponse {
        private Long idx;
        private Double amount;
        private LocalDateTime paymentDate;
        private String paymentMethod;
        private String status;

        public static PaymentResponse from(Payment payment) {
            return PaymentResponse.builder()
                    .idx(payment.getIdx())
                    .amount(payment.getAmount())
                    .paymentDate(payment.getPaymentDate())
                    .paymentMethod(payment.getPaymentMethod())
                    .status(payment.getStatus())
                    .amount(payment.getAmount())
                    .build();
        }
    }
}
