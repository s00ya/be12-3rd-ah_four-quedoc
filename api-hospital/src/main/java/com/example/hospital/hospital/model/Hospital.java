package com.example.hospital.hospital.model;

import com.example.hospital.notice.model.Notice;
import com.example.hospital.review.model.Review;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = true)
    private String department;
    // 진료과목 소아과,

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String type;
    // 병원 종류 종합병원, 소형병원

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private String openTime;

    @Column(nullable = false)
    private String closeTime;

    @JsonManagedReference
    @OneToMany(mappedBy = "hospital")
    private List<Review> reviews = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(mappedBy = "hospital")
    private List<Notice> notices = new ArrayList<>();

}
