package com.gaaji.auth.applicationservice;

import com.gaaji.auth.controller.dto.TokenResponse;
import com.gaaji.auth.exception.AuthIdNotFoundException;
import com.gaaji.auth.jwt.JwtProvider;
import com.gaaji.auth.repository.AuthRepository;
import io.jsonwebtoken.Claims;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

    private final JwtProvider jwtProvider;
    private final StringRedisTemplate stringRedisTemplate;
    private final AuthRepository authRepository;

    public TokenResponse createTokens(String authId){
        String accessToken = jwtProvider.createAccessToken(authId);
        String refreshToken = jwtProvider.createRefreshToken(authId);

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(refreshToken,authId);
        stringRedisTemplate.expire(refreshToken,30, TimeUnit.DAYS);

        return TokenResponse.of(accessToken,refreshToken);
    }

    public String refresh(String refreshToken){
        // refreshToken 유효성 검증 (시간)
        jwtProvider.validateToken(refreshToken);// error

        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String authId = ops.get(refreshToken);

        authRepository.findById(authId)
                .orElseThrow(AuthIdNotFoundException::new);
        return jwtProvider.createAccessToken(authId);
    }


}
