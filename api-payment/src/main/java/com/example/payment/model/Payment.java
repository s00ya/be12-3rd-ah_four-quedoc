package com.example.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

//    @ManyToOne
//    @JoinColumn(name = "reservation_idx", nullable = false)
    @Column(nullable = false)
    private Long reservationIdx;  // 예약 (외래 키)

    private Double amount;  // 결제 금액

    private LocalDateTime paymentDate;  // 결제 날짜

    private PaymentMethod paymentMethod;  // 결제 방법

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;  // 결제 상태

    // 결제 정보 업데이트 메서드
    public void updatePaymentInfo(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.paymentDate = LocalDateTime.now();

        // 결제 정보가 둘 다 설정되면 상태를 COMPLETED로 변경
        if (this.paymentMethod != null && this.paymentDate != null) {
            this.status = PaymentStatus.COMPLETED;
        } else {
            this.status = PaymentStatus.UNCOMPLETED;
        }
    }

}
