package com.example.core.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // User 관련 ErrorCode
    SAVE_FAILED(12000,"오류로 인한 회원가입 실패"),
    INTEGRITY_CONSTRAINT_VIOLATION(12001,"무결성 제약 조건 위반"),
    VERIFICATION_FAILED(12002,"입력값 검증 실패"),
    DATABASE_ERROR(12003,"데이터베이스 오류 발생"),
    ILLEGAL_ARGUMENT_EMAIL(12004,"email은 null이거나 비어 있을 수 없습니다."),
    ILLEGAL_ARGUMENT_ID(12005,"id는 null이거나 비어 있을 수 없습니다."),
    UPDATE_FAILED(12006,"user 정보 update 실패"),
    INCORRECT_PASSWORD(12007,"비밀번호가 다릅니다."),
    NO_EXIST(12008,"사용자를 찾을 수 없습니다."),
    AUTHENTICATION_FAIL(12009,"인증 실패 오류"),

    INVALID_INPUT(400, "잘못된 입력입니다."),
    RESOURCE_NOT_FOUND(404, "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
