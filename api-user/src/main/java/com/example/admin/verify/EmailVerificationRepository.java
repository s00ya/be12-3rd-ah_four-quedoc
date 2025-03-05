package com.example.admin.verify;

import com.example.admin.verify.model.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification,Long> {
    Optional<EmailVerification> findByEmailAndCode(String email, String code);

    boolean existsByEmailAndVerified(String email, boolean verified);
}
