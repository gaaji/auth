package com.gaaji.auth.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${key.jwt}")
    private String secretKey;
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24L;
    private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 30L;

    private final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @PostConstruct // init() 메소드
    protected void init() {  // secretKey를 Base64형식으로 인코딩함. 인코딩 전후 확인 로깅
        LOGGER.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        System.out.println(secretKey);
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        System.out.println(secretKey);
        LOGGER.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    private String createToken(long expirationTime, String authId) {
        // 토큰 생성 시작

        Claims claims = Jwts.claims().setSubject(authId);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret 값 세팅
                .compact();
    }


    public String createAccessToken(String authId) {
        return createToken(ACCESS_TOKEN_EXPIRATION, authId);
    }

    public String createRefreshToken(String authId) {
        return createToken(REFRESH_TOKEN_EXPIRATION, authId);
    }


}
