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

    private String paymentMethod;  // 결제 방법

    @Column(nullable = false)
    private String status;  // 결제 상태

}
