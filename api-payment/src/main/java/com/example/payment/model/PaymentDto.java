package com.example.payment.model;

import com.example.admin.user.model.User;
import com.example.hospital.hospital.model.Hospital;
import lombok.AllArgsConstructor;
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
        private User user;
        private Hospital hospital;
        private Double amount;
        private LocalDateTime paymentDate;
        private String paymentMethod;
        private String status;

        public Payment toEntity() {
            return Payment.builder()
                    .user(user)
                    .hospital(hospital)
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
        private User user;
        private Hospital hospital;
        private Double amount;
        private LocalDateTime paymentDate;
        private String paymentMethod;
        private String status;
        public static PaymentResponse from(Payment payment) {
            return PaymentResponse.builder()
                    .user(payment.getUser())
                    .hospital(payment.getHospital())
                    .amount(payment.getAmount())
                    .paymentDate(payment.getPaymentDate())
                    .paymentMethod(payment.getPaymentMethod())
                    .status(payment.getStatus())
                    .amount(payment.getAmount())
                    .build();
        }
    }
}
