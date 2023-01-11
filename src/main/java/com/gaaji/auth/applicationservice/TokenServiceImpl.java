package com.gaaji.auth.applicationservice;

import com.gaaji.auth.controller.dto.TokenResponse;
import com.gaaji.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

    private final JwtProvider jwtProvider;

    public TokenResponse createTokens(String authId){
        String accessToken = jwtProvider.createAccessToken(authId);
        String refreshToken = jwtProvider.createRefreshToken(authId);

        // TODO refreshToken은 DB에 저장. //

        return TokenResponse.of(accessToken,refreshToken);
    }

    public void refresh(String refreshToken){
        // refreshToken 유효성 검증 (시간)

        // refreshToken 값 검증 (db에 있는지) //

        // db value 검증 -> auth repo에 있는지

//        return jwtProvider.createAccessToken(authId);
    }


}
