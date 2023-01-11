package com.gaaji.auth.controller;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.auth.controller.dto.ResponseDto;
import com.gaaji.auth.oauth.CustomOAuth2UserService;
import java.util.HashMap;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CallbackController {


    private final CustomOAuth2UserService customOAuth2UserService;


    @GetMapping("/login/naverlogin")
    public void  redirectUriProcessor(@RequestParam String code,
            @RequestParam String state)  {

        customOAuth2UserService.naverLogin(code, state);
    }
    @GetMapping("login/kakao")
    public void  redirectKakao(@RequestParam String code) {
        customOAuth2UserService.kakaoLogin(code);
    }

}
