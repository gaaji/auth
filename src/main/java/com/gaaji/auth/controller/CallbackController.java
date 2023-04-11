package com.gaaji.auth.controller;

import com.gaaji.auth.controller.dto.TokenResponse;
import com.gaaji.auth.oauth.CustomOAuth2UserService;
import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController

public class CallbackController {


    private final CustomOAuth2UserService customOAuth2UserService;
    private final Environment env;

    private final String REDIRECT_URL;

    public CallbackController(CustomOAuth2UserService customOAuth2UserService, Environment env) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.env = env;
        REDIRECT_URL = env.getProperty("oauth.redirect");
    }

    @GetMapping("/login/naverlogin")
    public ResponseEntity<Void> redirectUriProcessor(@RequestParam String code,
            @RequestParam String state, HttpServletResponse response) throws IOException {

        log.info("[NAVER] : CALLBACK!!!");
        TokenResponse tokens = customOAuth2UserService.naverLogin(code, state);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(REDIRECT_URL+"access="+tokens.getAccessToken()+"&refresh="+tokens.getRefreshToken()));


        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }
    @GetMapping("login/kakao")
    public ResponseEntity<Void>  redirectKakao(@RequestParam String code) {
        TokenResponse tokens = customOAuth2UserService.kakaoLogin(code);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(REDIRECT_URL + "access="+tokens.getAccessToken()+"&refresh="+tokens.getRefreshToken()));

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

}
