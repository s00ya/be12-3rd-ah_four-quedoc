package com.example.payment;

import com.example.apireservation.reservation.ReservationRepository;
import com.example.apireservation.reservation.ReservationService;
import com.example.apireservation.reservation.model.Reservation;
import com.example.apireservation.reservation.model.ReservationDto;
import com.example.core.common.BaseResponse;
import com.example.core.common.CustomException;
import com.example.core.common.ErrorCode;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentDto;
import com.example.payment.model.PaymentStatus;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);
    private final PaymentRepository paymentRepository;

    public PaymentDto.PaymentResponse saveById(Long reservationIdx, PaymentDto.PaymentRequest dto) {
        try {
            logger.info("saveById - 예약 idx: {}", reservationIdx);

            Payment payment = Payment.builder()
                    .amount(dto.getAmount())
                    .status(PaymentStatus.UNCOMPLETED)
                    .reservationIdx(reservationIdx)
                    .build();

            Payment savedPayment = paymentRepository.save(payment);
            logger.info("Payment saved successfully with id: {}", savedPayment.getIdx());

            return PaymentDto.PaymentResponse.from(savedPayment);

        } catch (DataIntegrityViolationException e) {  // 무결성 제약 조건 위반
            logger.error("DataIntegrityViolationException occurred while saving payment", e);
            throw new CustomException(ErrorCode.PAYMENT_PROCESS_FAILED);

        } catch (DataAccessException e) {  // 데이터베이스 관련 예외
            logger.error("DataAccessException occurred while saving payment", e);
            throw new CustomException(ErrorCode.DATABASE_ERROR);

        } catch (Exception e) {  // 기타 예외
            logger.error("Exception occurred while saving payment", e);
            throw new CustomException(ErrorCode.PAYMENT_PROCESS_FAILED);
        }
    }

    @Transactional
    public PaymentDto.PaymentResponse processPayment(Long reservationIdx, PaymentDto.PaymentRequest dto) {
        logger.info("Processing payment for reservationIdx: {}", reservationIdx);

        // 예약 ID로 결제 정보 찾기
        Payment payment = paymentRepository.findByReservationIdx(reservationIdx);
        if (payment == null) {
            throw new CustomException(ErrorCode.PAYMENT_NOT_FOUND);
        }

        System.out.println(dto.getPaymentMethod());
        // 결제 정보 업데이트
        payment.updatePaymentInfo(dto.getPaymentMethod());

        logger.info("Payment processed successfully for reservationIdx: {}, Status: {}", reservationIdx, payment.getStatus());
        return PaymentDto.PaymentResponse.from(paymentRepository.save(payment));
    }


    public List<PaymentDto.PaymentResponse> getList() {
        try {
            logger.info("getList - Fetching all payments");

            List<PaymentDto.PaymentResponse> list = paymentRepository.findAll().stream()
                    .map(PaymentDto.PaymentResponse::from)
                    .collect(Collectors.toList());

            logger.info("Fetched {} payments successfully", list.size());
            return list;

        } catch (DataAccessException e) {  // 데이터베이스 접근 관련 예외
            logger.error("DataAccessException occurred while fetching payment list", e);
            throw new CustomException(ErrorCode.DATABASE_ERROR);

        } catch (Exception e) {  // 기타 예외
            logger.error("Exception occurred while fetching payment list", e);
            throw new CustomException(ErrorCode.PAYMENT_PROCESS_FAILED);
        }
    }


    public BaseResponse<PaymentDto.PaymentResponse> findByReservationIdx(Long reservationIdx) {
        try {
            logger.info("findByReservationIdx - Finding payment for reservationIdx: {}", reservationIdx);

            Payment pay = paymentRepository.findByReservationIdx(reservationIdx);
            if (pay == null) {
                logger.warn("No payment found for reservationIdx: {}", reservationIdx);
                throw new CustomException(ErrorCode.PAYMENT_NOT_FOUND);
            }

            return BaseResponse.success(PaymentDto.PaymentResponse.from(pay));

        } catch (DataAccessException e) {  // 데이터베이스 관련 예외
            logger.error("DataAccessException occurred while finding payment", e);
            throw new CustomException(ErrorCode.DATABASE_ERROR);

        } catch (Exception e) {  // 기타 예외
            logger.error("Exception occurred while finding payment", e);
            throw new CustomException(ErrorCode.PAYMENT_PROCESS_FAILED);
        }
    }


}
