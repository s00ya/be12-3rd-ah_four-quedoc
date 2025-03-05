package com.example.apireservation.reservation.model;


import com.example.admin.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import com.example.hospital.hospital.model.Hospital;
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
@Schema(description = "예약 정보를 저장하는 Reservation Entity")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Reservation 테이블의 primary key, auto_increment", example = "1")
    private long idx;

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    @Schema(description = "예약한 사람의 정보")
    private User user; // user foreign key

    @ManyToOne
    @JoinColumn(name = "hospital_idx", nullable = false)
    @Schema(description = "예약된 병원의 정보")
    private Hospital hospital; // hospital foreign key

    @Column(nullable = false)
    @Schema(description = "병원 방문자의 이름", example = "홍길동")
    private String name;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @Schema(description = "예약 시간", example = "2025-03-01")
    private LocalDateTime time;

    @PrePersist
    protected void onCreate() {
        time = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES); // 초 제거
    }
}
