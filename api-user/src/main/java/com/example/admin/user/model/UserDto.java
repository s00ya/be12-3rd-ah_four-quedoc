package com.example.admin.user.model;


import lombok.Getter;


public class UserDto {

    @Getter
    public static class LoginDto {
        private String email;
        private String password;
    }

    @Getter
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
    public static class SignupDto {
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
}