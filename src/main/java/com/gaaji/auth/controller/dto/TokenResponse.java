package com.gaaji.auth.controller.dto;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    private TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static TokenResponse of(String accessToken, String refreshToken){
        return new TokenResponse(accessToken,refreshToken);
    }
}
