package com.example.hospital.review.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String title;
    private String contents;
    private String nickName;
    private String passWord;
    private int score;
    private LocalDate createdAt;
    private boolean isPublic;

//    @ManyToOne
//    @JoinColumn(name="user_idx")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "doctor_idx")
//    private Doctor doctor;
//
//    @OneToOne
//    @JoinColumn(name="reservation_idx")
//    private Reservation reservation;

}
