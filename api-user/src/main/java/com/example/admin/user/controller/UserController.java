package com.example.admin.user.controller;


import com.example.admin.common.EmailService;
import com.example.admin.user.model.User;
import com.example.admin.user.model.UserDto;
import com.example.admin.user.service.UserService;
import com.example.admin.verify.EmailVerificationService;
import com.example.core.common.BaseResponse;
import com.example.core.common.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.core.util.JWTUtil;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name="USER API", description = "User API 입니다.")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailVerificationService emailVerificationService;

    @GetMapping("/test")
    @Operation(summary = "테스트", description = "접속이 잘 되는지 테스트하는 API 입니다.")
    public BaseResponse<String> test(@RequestHeader HttpHeaders headers) {
        logger.info("INFO 로그 - 일반적인 정보");
        logger.error("error 로그 - 경고 발생");
        logger.error("ERROR 로그 - 에러 발생");
        logger.info(headers);

        emailService.sendHtmlEmail("cksdudtj0221@gmail.com","test메일이에요","304912");
        return BaseResponse.success("ok");
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일, 비밀번호로 로그인, 인증 후 JWT 토큰을 반환하는 API 입니다.")
    public BaseResponse<UserDto.ResponseDto> login(@RequestBody UserDto.LoginDto dto,HttpServletResponse response) {

        logger.info("login api");

        // 인증을 위한 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication authentication = null;
        // 인증을 시도
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            logger.error("인증 실패 오류" + e.getMessage());
            return BaseResponse.error(ErrorCode.AUTHENTICATION_FAIL);
        }
        // 인증이 성공하면 JWT 토큰 생성
        if (authentication != null && authentication.isAuthenticated()) {

            logger.info("인증 성공");
            String token =  JWTUtil.generateToken(dto.getEmail()); // JWT 토큰 생성 후 반환
            Cookie cookie = new Cookie("JWT", token);
            cookie.setHttpOnly(true);   // HTTP-only 속성 설정
            cookie.setPath("/");       // 쿠키의 유효 경로 설정
            cookie.setMaxAge(3600);    // 쿠키의 만료 시간 설정 (초 단위)

            // 쿠키를 응답에 추가
            response.addCookie(cookie);
            User user = userService.findByEmail(dto.getEmail());
            logger.info("JWT 토큰 포함해서 반환");
            return BaseResponse.success(UserDto.ResponseDto.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickname(user.getNickname())
                    .type(user.getType())
                    .register(user.getRegister())
                    .build());
        } else {
            logger.error("인증 실패 오류");
            return BaseResponse.error(ErrorCode.AUTHENTICATION_FAIL);
        }
    }

    @PostMapping("/signup")
    @Operation(summary = "회원 가입", description = "회원 정보를 받아 회원 가입을 합니다.")
    public BaseResponse<String> signup(@Valid @RequestBody UserDto.SignupDto dto, BindingResult bindingResult) {

        logger.info("signup api");

        if(bindingResult.hasErrors()) {
            logger.error("검증 실패 오류");
            return BaseResponse.error(ErrorCode.VERIFICATION_FAILED);
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhoneNumber())
                .nickname(dto.getNickname())
                .type(dto.getCustomerTypeCode())
                .register(false)
                .build();

        userService.save(user);

        return BaseResponse.success("Signup success");
    }

    @PostMapping("/update")
    @Operation(summary = "정보 수정", description = "사용자의 정보를 수정하는 API 입니다.")
    public BaseResponse<String> update(@RequestBody UserDto.UpdateDto dto) {
        logger.info("update api");
        User user = userService.findByEmail(dto.getEmail());
            user.setName(dto.getName());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setPhone(dto.getPhoneNumber());
            userService.save(user);
            return BaseResponse.success("update success");

    }

    @GetMapping("/getUser")
    @Operation(summary = "유저 조회", description = "해당 id의 user의 정보를 조회합니다.")
    public BaseResponse<UserDto.ResponseDto> getUser(@RequestParam long id) {
        logger.info("getUser api");
        User user = userService.findById(id);
            UserDto.ResponseDto responseDto = UserDto.ResponseDto.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickname(user.getNickname())
                    .type(user.getType())
                    .build();
            return BaseResponse.success(responseDto);
    }

    @PostMapping("/verify")
    @Operation(summary = "비밀번호 인증", description = "해당 회원의 비밀번호가 맞는지 검사하는 API 입니다.")
    public BaseResponse<String> verify(@RequestParam String email, String password) {
        logger.info("verify api");
        User user = userService.findByEmail(email);
            if(passwordEncoder.matches(password, user.getPassword())) {
                return BaseResponse.success("ok");
            } else {
                logger.info("password not match");
                return BaseResponse.error(ErrorCode.INCORRECT_PASSWORD);
            }
    }

    @PostMapping("/emailVerification")
    @Operation(summary = "이메일 인증번호 발송", description = "회원의 비밀번호를 찾기위해 email로 인증번호를 발송하는 API 입니다.")
    public BaseResponse<String> emailVerify(@RequestParam String email) {
        logger.info("emailVerify api");
        User user = userService.findByEmail(email);
            String code = emailVerificationService.createVerificationCode(email);
            emailService.sendHtmlEmail(email,"[Quedoc] 이메일 인증",code);

        return BaseResponse.success("ok");
    }
    @GetMapping("/verify")
    @Operation(summary = "이메일 인증번호 확인", description = "회원의 비밀번호를 찾기위해 인증번호를 받아 검사하는 API 입니다.")
    public BaseResponse<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        logger.info("verifyCode api");
        boolean isValid = emailVerificationService.verifyCode(email, code);
        return isValid ? BaseResponse.success("ok") : BaseResponse.error(ErrorCode.AUTHENTICATION_FAIL);
    }


    @PostMapping("/withdrawal")
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 API 입니다.")
    public BaseResponse<String> withdrawal(@RequestParam String email) {
        logger.info("withdrawal api");
        User user = userService.findByEmail(email);
            userService.withdrawal(user);
        return BaseResponse.success("ok");
    }


}
