package com.example.payment.repository;

import com.example.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
//    PaymentDto.PaymentResponse findByIdx(Long idx);
    Payment findByReservationIdx(Long reservationIdx);
}
