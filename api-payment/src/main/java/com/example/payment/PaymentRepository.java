package com.example.payment;

import com.example.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
//    PaymentDto.PaymentResponse findByIdx(Long idx);
    Payment findByReservationIdx(Long reservationIdx);
}
