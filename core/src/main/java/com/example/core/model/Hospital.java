package com.example.core.model;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

//    @Column(nullable = false, length = 100)
    private String name;

//    @Column(nullable = false, length = 255)
    private String address;

//    @Column(nullable = true)
    private String department;

//    @Column(nullable = false)
    private String phone;

}
