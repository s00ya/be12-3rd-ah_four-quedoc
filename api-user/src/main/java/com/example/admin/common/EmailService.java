package com.example.admin.common;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendHtmlEmail(String to, String subject, String code) {
        try {
            // 1. HTML 템플릿 읽기
            String htmlContent = readHtmlTemplate("template/EmailTemplate.html");

            // 2. {{code}}, {{verification_link}} 자리표시자 값 대체
            //String verificationLink = "https://quedoc.com/verify?email=" + to + "&code=" + code;

            String verificationLink = "http://localhost:8081/user/verify?email=" + to + "&code=" + code;
            htmlContent = htmlContent.replace("{{code}}", code);
            htmlContent = htmlContent.replace("{{verification_link}}", verificationLink);

            // 3. 이메일 발송
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("your-email@gmail.com");
            helper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readHtmlTemplate(String path) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new RuntimeException("HTML 템플릿을 불러오는 중 오류 발생", e);
        }
    }
}