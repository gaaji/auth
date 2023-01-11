package com.gaaji.auth.applicationservice;

import com.gaaji.auth.controller.dto.TokenResponse;

public interface TokenService {

     TokenResponse createTokens(String authId);

     void refresh(String refreshToken);
}
