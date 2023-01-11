package com.gaaji.auth.oauth;

import com.gaaji.auth.controller.dto.TokenResponse;

public interface CustomOAuth2UserService {

    TokenResponse kakaoLogin(String code);

    TokenResponse naverLogin(String code, String state);
}
