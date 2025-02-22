package com.example.admin.user.model;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


public class UserDto {

    @Getter
    @Setter
    public static class LoginDto {
        @NotBlank(message = "이메일을 입력하세요.")
        @Email(message = "유효한 이메일 형식이 아닙니다.")
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
    public static class UpdateDto {
        private String email;
        private String name;
        private String password;
        private String nickname;
        private String birthDate;
        private String gender;
        private String phoneNumber;
        private String emailDomain;
        private String customerTypeCode;
    }

    @Getter
    @Setter
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
        private String gender;

        @NotBlank(message = "전화번호를 입력하세요.")
        @Pattern(
                regexp = "^01[0-9]\\d{7,8}$",
                message = "전화번호 형식이 올바르지 않습니다. (예: 010-1234-5678)"
        )
        private String phoneNumber;
        private String emailDomain;
        private String customerTypeCode;
    }
}