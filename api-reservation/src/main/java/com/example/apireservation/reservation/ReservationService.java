package com.example.apireservation.reservation;

import com.example.apireservation.reservation.model.Reservation;
import com.example.core.common.CustomException;
import com.example.core.common.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private static final Logger logger = LogManager.getLogger(ReservationService.class);
    public final ReservationRepository reservationRepository;

    public Reservation save (Reservation reservation) {
        // 예약 시간이 이미 존재하는지 확인 (기존 예약 시간대와 겹치는지 체크)
        try {
            return reservationRepository.save(reservation);
        } catch (Exception e) {
            logger.error("save reservation error", e);
            throw new CustomException(ErrorCode.RESERVATION_SAVE_FAIL);
        }
    }
    public void delete (Long idx)  {
        logger.info("delete reservation");
        Optional<Reservation> reservation = reservationRepository.findById(idx);
        if (!reservation.isPresent()) {
            logger.error("no reservation found");
            throw new CustomException(ErrorCode.NO_RESERVATION_EXIST);
        }
        try {
            reservationRepository.deleteById(idx);
        } catch (EmptyResultDataAccessException e) {
            logger.error("no reservation found");
            throw new CustomException(ErrorCode.NO_DATA);
        } catch (Exception e) {
            logger.error("delete reservation error", e);
            throw new CustomException(ErrorCode.RESERVATION_DELETE_FAIL);
        }
    }
    public Reservation findByIdx(Long idx) {
        logger.info("findByIdx reservation");
        Optional<Reservation> reservationOptional = null;
        try {
            reservationOptional = reservationRepository.findById(idx);
            if(!reservationOptional.isPresent()) {
                logger.error("no reservation found");
                throw new CustomException(ErrorCode.NO_RESERVATION_EXIST);
            }
        } catch (Exception e) {
            logger.error("findByIdx reservation error", e);
            throw new CustomException(ErrorCode.NO_DATA);
        }
        return reservationOptional.get();
    }

    public List<Reservation> findByHospitalId(Long hospitalId) {

        logger.info("findByHospitalId reservation");
        List<Reservation> reservationList = null;
        try {
            reservationList = reservationRepository.findByHospitalIdx(hospitalId);
        } catch (Exception e) {
            logger.error("findByHospitalId reservation error", e);
            throw new CustomException(ErrorCode.NO_DATA);
        }
        return reservationList;
    }

}
