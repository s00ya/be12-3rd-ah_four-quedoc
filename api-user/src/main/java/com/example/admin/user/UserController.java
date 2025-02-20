package com.example.admin.user;


import com.example.admin.user.model.User;
import com.example.admin.user.model.UserDTO;
import com.example.admin.common.BaseResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import com.example.core.util.JWTUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;


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

    @GetMapping("/testLogin")
    public BaseResponse<String> testLogin(HttpServletResponse response) {
        String token = JWTUtil.generateToken("test");


        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);   // HTTP-only 속성 설정
        cookie.setSecure(true);     // HTTPS에서만 전송되도록 설정 (배포 시)
        cookie.setPath("/");       // 쿠키의 유효 경로 설정
        cookie.setMaxAge(3600);    // 쿠키의 만료 시간 설정 (초 단위)

        // 쿠키를 응답에 추가
        response.addCookie(cookie);
        return BaseResponse.success("test");
    }

    @PostMapping("/signup")
    public BaseResponse<String> signup(@RequestBody UserDTO.signupDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhoneNumber());
        user.setNickname(dto.getNickname());
        user.setBirthDate(dto.getBirthDate());
        user.setGender(dto.getGender());

        userService.save(user);

        return BaseResponse.success("signup success");
    }

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserDTO.loginDTO dto) {

        User user = userService.findByEmail(dto.getEmail());

        if (user == null) {
            return BaseResponse.error(12001,"login fail");
        }


        return BaseResponse.success(user);
    }

    @PostMapping("/update")
    public BaseResponse<String> update(@RequestBody UserDTO.updateDTO dto) {
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
