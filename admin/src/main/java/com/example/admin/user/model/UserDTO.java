package com.example.admin.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


public class UserDTO {

    @Getter
    public class loginDTO {
        private String email;
        private String password;
    }

    @Getter
    public class updateDTO {
        private String email;
        private String password;
        private String name;
        private String phone;
    }
}
