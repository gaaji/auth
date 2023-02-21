package com.gaaji.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum AuthErrorCode implements ErrorCode{


    AUTH_ID_NOT_FOUND(HttpStatus.UNAUTHORIZED,"A-0001", "등록되지 않은 계정입니다."), // v
    Input_Null_Data_On_Review_Id(HttpStatus.INTERNAL_SERVER_ERROR, "r-0001","후기 생성 과정에서 ReviewId에 Null이 입력되었습니다."),
    Input_Null_Data_On_Post_Id(HttpStatus.INTERNAL_SERVER_ERROR, "r-0002","후기 생성 과정에서 PostId에 Null이 입력되었습니다."),
    No_match_Id(HttpStatus.BAD_REQUEST, "r-0003","후기 등록자의 Id와 일치하는 판매자 또는 구매자의 Id가 없습니다."),
    Nonexistent_Target(HttpStatus.BAD_REQUEST, "r-0004","판매자 또는 구매자의 Id가 없습니다."),
    No_Review(HttpStatus.BAD_REQUEST, "r-0005","후기가 없습니다."),
    Equals_Seller_And_Purchaser(HttpStatus.BAD_REQUEST, "r-0006","판매자와 구매자가 동일합니다."),
    No_Search_Review(HttpStatus.BAD_REQUEST, "r-0007","해당 후기를 찾지 못했습니다."),
    No_Match_Sender(HttpStatus.BAD_REQUEST, "r-0008","해당 후기 작성자와 정보가 맞지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    public String getErrorName() {
        return this.name();
    }
}