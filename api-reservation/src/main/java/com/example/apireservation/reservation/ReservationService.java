package com.example.apireservation.reservation;

import com.example.apireservation.reservation.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {
    public final ReservationRepository reservationRepository;


    public void save (Reservation reservation) throws Exception {
        reservationRepository.save(reservation);
    }
    public int delete (Long idx)  {
        Optional<Reservation> reservation = reservationRepository.findById(idx);

        if (!reservation.isPresent()) {
            // 존재하지 않는 예약일 경우 예외를 던짐
            return 1; // 예를 들어, 존재하지 않는 경우는 1 반환
        }
        try {
            reservationRepository.deleteById(idx);
            return 0;
        } catch (EmptyResultDataAccessException e) {
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }
    public Reservation findByIdx(Long idx) throws Exception {
        Optional<Reservation> reservationOptional = reservationRepository.findById(idx);
        return reservationOptional.orElse(null);
    }

}
