package com.example.payment.model;

import com.example.admin.user.model.User;
import com.example.apireservation.reservation.model.Reservation;
import com.example.hospital.hospital.model.Hospital;
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

    @ManyToOne
    @JoinColumn(name = "hospital_idx", nullable = false)
    private Hospital hospital;  // 병원 (외래 키)

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private User user;  // 병원 (외래 키)

    @ManyToOne
    @JoinColumn(name = "reservation_idx", nullable = false)
    private Reservation reservation;  // 예약 (외래 키)

    @Column(nullable = false)
    private Double amount;  // 결제 금액

    @Column(nullable = false)
    private LocalDateTime paymentDate;  // 결제 날짜

    @Column(nullable = false)
    private String paymentMethod;  // 결제 방법

    @Column(nullable = false)
    private String status;  // 결제 상태

}
