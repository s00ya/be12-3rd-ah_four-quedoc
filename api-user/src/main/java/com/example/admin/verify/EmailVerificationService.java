package com.example.admin.verify;

import com.example.admin.verify.model.EmailVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;

    // 6자리 인증 코드 생성 메서드
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 100000 ~ 999999
        return String.valueOf(code);
    }

    // 인증 코드 생성 및 저장
    @Transactional
    public String createVerificationCode(String email) {
        // 기존 코드 삭제 (새 코드 발급)
        emailVerificationRepository.deleteAll(emailVerificationRepository.findAll());

        String code = generateVerificationCode();
        EmailVerification verification = EmailVerification.builder()
                .email(email)
                .code(code)
                .expiresAt(LocalDateTime.now().plusMinutes(5)) // 5분 유효
                .createdAt(LocalDateTime.now())
                .build();

        emailVerificationRepository.save(verification);
        return code;
    }

    // 인증 코드 검증
    @Transactional
    public boolean verifyCode(String email, String code) {
        return emailVerificationRepository.findByEmailAndCode(email, code)
                .filter(verification -> !verification.isExpired()) // 만료되지 않은 코드만
                .map(verification -> {
                    verification.setVerified(true); // 인증 완료 처리
                    emailVerificationRepository.save(verification);
                    return true;
                }).orElse(false);
    }
}