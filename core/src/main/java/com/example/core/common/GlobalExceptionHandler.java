package com.example.core.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public BaseResponse<String> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return BaseResponse.error(errorCode);
    }
}