package com.example.apireservation.reservation;

import com.example.apireservation.reservation.model.Reservation;
import lombok.RequiredArgsConstructor;
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
    public void delete (Long idx) throws Exception {
        reservationRepository.deleteById(idx);
    }
    public Reservation findByIdx(Long idx) throws Exception {
        Optional<Reservation> reservationOptional = reservationRepository.findById(idx);
        return reservationOptional.orElse(null);
    }

}
