package com.example.payment.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentMethod {
    @Schema(description = "현금 결제", example = "CASH")
    CASH("현금"),

    @Schema(description = "계좌이체 결제", example = "BANK_TRANSFER")
    BANK_TRANSFER("계좌이체"),

    @Schema(description = "카드 결제", example = "CREDIT_CARD")
    CREDIT_CARD("카드");

    private final String description;

    @JsonCreator
    public static PaymentMethod fromString(String value) {
        try {
            return PaymentMethod.valueOf(value);
        } catch (IllegalArgumentException e) {
            // 유효하지 않은 값이 들어올 경우 처리
            throw new IllegalArgumentException("유효하지 않은 결제 방법입니다. (유효한 값: CASH, BANK_TRANSFER, CREDIT_CARD)");
        }
    }

}