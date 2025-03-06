package com.example.apireservation.reservation;


import com.example.apireservation.reservation.model.Reservation;
import com.example.hospital.hospital.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Override
    Optional<Reservation> findById(Long id);

    Optional<Reservation> findByHospitalAndTimeBetween(Hospital hospital, LocalDateTime start, LocalDateTime end);

}
