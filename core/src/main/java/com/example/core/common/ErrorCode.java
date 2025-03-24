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
    EXPIRED_AUTHENTICATION(12010,"만료된 인증번호"),
    DELETE_FAIL(12011,"유저 삭제 실패"),

    // Reservation 관련 ErrorCode
    RESERVATION_FAIL(52000, "예약 실패."),
    RESERVATION_SAVE_FAIL(52001,"예약 저장 실패"),
    NO_DATA(52002,"조회된 데이터 없음"),
    RESERVATION_DELETE_FAIL(52003,"예약 삭제 실패"),
    NO_RESERVATION_EXIST(52004,"존재하지 않는 예약입니다."),
    RESERVATION_ALREADY_EXIST(52005,"이미 예약된 시간대입니다."),

    // Board 관련 ErrorCode
    BOARD_SAVE_FAIL(62001,"게시글 저장 실패"),
    BOARD_LOAD_FAIL(62002,"게시글 불러오기 실패"),

    // Hospital 관련 ErrorCode
    HOSPITAL_NOT_FOUND(20000, "병원을 찾을 수 없습니다."),
    HOSPITAL_CREATION_FAILED(20001, "병원 등록에 실패했습니다."),
    HOSPITAL_UPDATE_FAILED(20002, "병원 정보 업데이트에 실패했습니다."),
    HOSPITAL_DELETE_FAILED(20003, "병원 삭제에 실패했습니다."),
    HOSPITAL_DUPLICATE(20004, "이미 존재하는 병원입니다."),
    HOSPITAL_DETAIL_FAILED(21000, "병원 상세정보 불러오기에 실패했습니다."),
    REVIEW_CREATE_FAILED(22000, "리뷰 등록에 실패했습니다."),
    REVIEW_DELETE_FAILED(22001, "리뷰 삭제에 실패했습니다."),
    NO_REVIEW_EXIST(22002, "존재하지 않는 리뷰입니다."),
    REVIEW_ALREADY_EXIST(22003, "이미 작성된 리뷰입니다."),
    REVIEW_FETCH_FAILED(22004, "리뷰 조회에 실패했습니다."),


    // Payment 관련 ErrorCode
    PAYMENT_PROCESS_FAILED(13000, "결제 처리 실패"),
    PAYMENT_NOT_FOUND(13001, "결제 정보를 찾을 수 없습니다."),
    PAYMENT_INSUFFICIENT_FUNDS(13002, "결제 금액 부족"),
    PAYMENT_METHOD_NOT_SUPPORTED(13003, "지원되지 않는 결제 방법"),
    PAYMENT_ALREADY_PROCESSED(13004, "이미 처리된 결제입니다."),
    PAYMENT_REFUND_FAILED(13005, "결제 환불 실패");



    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
