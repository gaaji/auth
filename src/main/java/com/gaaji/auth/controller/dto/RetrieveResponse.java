package com.gaaji.auth.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RetrieveResponse {

    private String authId;
    private String nickname;
    private double mannerTemperature;


    public static RetrieveResponse of(String authId, String nickname, double mannerTemperature) {
        return new RetrieveResponse(authId, validateNickname(nickname), mannerTemperature);
    }

    private static String validateNickname(String nickname){
        return nickname == null ? "익명" : nickname;
        }
}
