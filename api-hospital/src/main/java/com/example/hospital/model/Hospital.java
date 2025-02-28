package com.example.hospital.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String phoneNumber;


    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private String openTime;

    @Column(nullable = false)
    private String closeTime;

}
