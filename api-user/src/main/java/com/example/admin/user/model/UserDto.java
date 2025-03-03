package com.example.admin.user.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;


public class UserDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "로그인할때 사용하는 dto, email과 password 필요")
    public static class LoginDto {
        @NotBlank(message = "이메일을 입력하세요.")
        @Email(message = "유효한 이메일 형식이 아닙니다.")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "올바른 이메일 주소를 입력하세요."
        )
        private String email;

        @NotBlank(message = "비밀번호를 입력하세요.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "비밀번호는 8~20자의 영문, 숫자, 특수문자를 포함해야 합니다."
        )
        private String password;
    }

    @Getter
    @Setter
    @Schema(description = "유저 정보를 수정할때 사용하는 dto")
    public static class UpdateDto {
        private String email;
        private String name;
        private String password;
        private String nickname;
        private String birthDate;
        private String phoneNumber;
        private String customerTypeCode;
    }

    @Getter
    @Setter
    @Schema(description = "유저 회원가입을 할때 사용하는 dto")
    public static class SignupDto {
        @NotBlank(message = "이메일을 입력하세요.")
        @Email(message = "유효한 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "이름을 입력하세요.")
        @Size(min = 2, max = 20, message = "이름은 2~20자 사이여야 합니다.")
        private String name;

        @NotBlank(message = "비밀번호를 입력하세요.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
                message = "비밀번호는 8~20자의 영문, 숫자, 특수문자를 포함해야 합니다."
        )
        private String password;

        @NotBlank(message = "닉네임을 입력하세요.")
        @Size(min = 3, max = 15, message = "닉네임은 3~15자 사이여야 합니다.")
        private String nickname;

        private String birthDate;

        @NotBlank(message = "전화번호를 입력하세요.")
        @Pattern(
                regexp = "^01[0-9]\\d{7,8}$",
                message = "전화번호 형식이 올바르지 않습니다. (예: 010-1234-5678)"
        )
        private String phoneNumber;

        private String customerTypeCode;
    }

    @Getter
    @Setter
    @Builder
    @Schema(description = "유저 정보 응답할때 사용하는 dto")
    public static class ResponseDto {
        private String email;
        private String name;
        private String nickname;
        private String type;
    }
}