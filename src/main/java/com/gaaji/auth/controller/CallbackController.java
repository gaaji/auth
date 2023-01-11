package com.gaaji.auth.controller;

import com.gaaji.auth.controller.dto.TokenResponse;
import com.gaaji.auth.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CallbackController {


    private final CustomOAuth2UserService customOAuth2UserService;


    @GetMapping("/login/naverlogin")
    public ResponseEntity<Void> redirectUriProcessor(@RequestParam String code,
            @RequestParam String state)  {

        TokenResponse tokens = customOAuth2UserService.naverLogin(code, state);

        return ResponseEntity.ok()
                .header("access-token",tokens.getAccessToken())
                .header("refresh-token",tokens.getRefreshToken())
                .build();
    }
    @GetMapping("login/kakao")
    public ResponseEntity<Void>  redirectKakao(@RequestParam String code) {
        TokenResponse tokens = customOAuth2UserService.kakaoLogin(code);
        return ResponseEntity.ok()
                .header("access-token",tokens.getAccessToken())
                .header("refresh-token",tokens.getRefreshToken())
                .build();
    }

}
