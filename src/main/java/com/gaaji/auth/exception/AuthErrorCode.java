package com.gaaji.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum AuthErrorCode implements ErrorCode{


    AUTH_ID_NOT_FOUND(HttpStatus.UNAUTHORIZED,"A-0001", "등록되지 않은 계정입니다."), // v

    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}