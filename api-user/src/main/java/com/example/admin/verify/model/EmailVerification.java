package com.example.admin.verify.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @Column(nullable = false, unique = false)
    private String email; // 인증 대상 이메일

    @Column(nullable = false, length = 6)
    private String code; // 6자리 인증 코드

    @Column(nullable = false)
    private LocalDateTime expiresAt; // 인증 코드 만료 시간

    @Column(nullable = false)
    private boolean verified = false; // 인증 여부 (초기값 false)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시각

    // 인증 만료 여부 확인 메서드
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }
}