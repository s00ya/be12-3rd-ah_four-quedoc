package com.example.admin.user.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원 정보를 저장하는 User Entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "User 테이블의 primary key, auto_increment", example = "1")
    private Long idx;

    @Column(nullable = false)
    @Schema(description = "User의 이름, 2~20자 사이", example = "홍길동")
    private String name;

    @Column(nullable = false)
    @Schema(description = "User의 비밀번호, 암호화되어 저장, 8~20자의 영문, 숫자, 특수문자를 포함", example = "qwer1234Q!")
    private String password;

    @Column(nullable = false,unique = true)
    @Schema(description = "User의 이메일, 중복 허용 안됨", example = "hong@naver.com")
    private String email;

    @Column(nullable = false)
    @Schema(description = "User의 전화번호, - 없이 저장", example = "01012345678")
    private String phone;

    @Column(nullable = false)
    @Schema(description = "User의 별명, 3~15자 사이", example = "길동이")
    private String nickname;

    @Column(nullable = false)
    @Schema(description = "User의 회원 타입, 일반사용자 - U, 사업자 - B", example = "U")
    private String type;

    @Schema(description = "User가 사업자일때 등록하는 사업자번호", example = "454-04-01752")
    private String businessNumber;

    @Schema(description = "User가 사업자인 경우, 병원 정보 등록 유무", example = "false")
    private Boolean register;
}