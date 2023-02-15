package com.gaaji.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum AuthErrorCode implements ErrorCode{


    AUTH_ID_NOT_FOUND(HttpStatus.UNAUTHORIZED,"A-0001", "등록되지 않은 계정입니다."), // v
    Input_Null_Data_On_Review_Id(HttpStatus.INTERNAL_SERVER_ERROR, "r-0001",
            "후기 생성 과정에서 ReviewId에 Null이 입력되었습니다.");
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}