package com.gaaji.auth.oauth;

public interface CustomOAuth2UserService {

    void kakaoLogin(String code);

    void naverLogin(String code, String state);
}
