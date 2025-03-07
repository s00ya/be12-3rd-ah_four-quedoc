package com.example.payment.model;

import io.swagger.v3.oas.annotations.media.Schema;

public enum PaymentStatus {
    @Schema(description = "결제가 완료된 상태")
    COMPLETED,

    @Schema(description = "결제가 미완료된 상태")
    UNCOMPLETED,

    @Schema(description = "결제가 취소된 상태")
    CANCELLED
}