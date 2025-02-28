package com.example.apireservation.reservation.model;


import com.example.admin.user.model.User;
import jakarta.persistence.*;
import com.example.hospital.model.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private User user; // user foreign key

    @ManyToOne
    @JoinColumn(name = "hospital_idx", nullable = false)
    private Hospital hospital; // hospital foreign key

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    @PrePersist
    protected void onCreate() {
        time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES); // 초 제거
    }
}
