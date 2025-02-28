package com.example.admin.user;


import com.example.admin.user.model.User;
import com.example.admin.user.model.UserDto;
import com.example.admin.user.test.UserService;
import com.example.core.common.BaseResponse;
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
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/test")
    public BaseResponse<String> test(@RequestHeader HttpHeaders headers) {
        logger.info("INFO 로그 - 일반적인 정보");
        logger.warn("WARN 로그 - 경고 발생");
        logger.error("ERROR 로그 - 에러 발생");
        logger.info(headers);
        String name = headers.getFirst("username");
        if(userService.findByEmail(name) != null) {}
        return BaseResponse.success("ok");
    }

    @PostMapping("/testLogin")
    public BaseResponse<UserDto.ResponseDto> testLogin(@RequestBody UserDto.LoginDto dto,HttpServletResponse response) {

        System.out.println(dto.getEmail());
        System.out.println(dto.getPassword());
        // 인증을 위한 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        Authentication authentication = null;
        // 인증을 시도
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            return BaseResponse.error(12001,"인증 실패 오류");
        }
        // 인증이 성공하면 JWT 토큰 생성
        if (authentication != null && authentication.isAuthenticated()) {
            System.out.println("authenticated");
            String token =  JWTUtil.generateToken(dto.getEmail()); // JWT 토큰 생성 후 반환
            Cookie cookie = new Cookie("JWT", token);
            cookie.setHttpOnly(true);   // HTTP-only 속성 설정
            cookie.setPath("/");       // 쿠키의 유효 경로 설정
            cookie.setMaxAge(3600);    // 쿠키의 만료 시간 설정 (초 단위)

            // 쿠키를 응답에 추가
            response.addCookie(cookie);
            User user = userService.findByEmail(dto.getEmail());

            return BaseResponse.success(UserDto.ResponseDto.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickname(user.getNickname())
                    .build());
        } else {
            System.out.println("authenticated fail");
            return BaseResponse.error(12002,"인증 실패 오류");
        }
    }

    @PostMapping("/signup")
    public BaseResponse<String> signup(@Valid @RequestBody UserDto.SignupDto dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            BaseResponse.error(12003, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setPhone(dto.getPhoneNumber());
        user.setNickname(dto.getNickname());
        user.setBirthDate(dto.getBirthDate());
        user.setGender(dto.getGender());

        userService.save(user);

        return BaseResponse.success("ok");
    }

    @PostMapping("/login")
    public BaseResponse<User> login(@Valid @RequestBody UserDto.LoginDto dto , BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            BaseResponse.error(12003, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        User user = userService.findByEmail(dto.getEmail());

        if (user == null) {
            return BaseResponse.error(12001,"login fail");
        }
        return BaseResponse.success(user);
    }

    @PostMapping("/update")
    public BaseResponse<String> update(@RequestBody UserDto.UpdateDto dto) {
        User user = userService.findByEmail(dto.getEmail());
        if(user != null) {
            user.setName(dto.getName());
            user.setPassword(dto.getPassword());
            user.setPhone(dto.getPhoneNumber());
            userService.save(user);
            return BaseResponse.success("update success");
        } else {
            return BaseResponse.error(12002,"not found");
        }
    }
}