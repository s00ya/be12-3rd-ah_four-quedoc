package com.example.admin.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


public class UserDTO {

    @Getter
    public static class loginDTO {
        private String email;
        private String password;
    }

    @Getter
    public static class updateDTO {
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
    public static class signupDTO {
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