package com.gaaji.auth.controller;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.auth.controller.dto.ResponseDto;
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

    private final Environment env;


    @GetMapping("/login/naverlogin")
    public void  redirectUriProcessor(@RequestParam String code,
            @RequestParam String state) throws JsonProcessingException {


        log.info(code); // Authorization Code 값
        log.info(state); // 내가 임의로 만든 state 값

        val grantType = "grant_type=authorization_code";
        val clientId = "client_id=" + env.getProperty("key.naver.id");
        val clientSecret = "client_secret=" + env.getProperty("key.naver.secret");

        val url = "https://nid.naver.com/oauth2.0/token" +
                "?" + grantType +
                "&" + clientId +
                "&" + clientSecret +
                "&code=" + code +
                "&state=" + state;

        RestTemplate restTemplate = new RestTemplate();
        HashMap body = restTemplate.getForEntity(url, HashMap.class).getBody();

        log.info(String.valueOf(body)); // --> access, refresh 토큰 값이 들어있다.

        String last = "https://openapi.naver.com/v1/nid/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + body.get("access_token"));
        HttpEntity request = new HttpEntity(headers);
        restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.exchange(last, HttpMethod.GET, request,
                String.class);
        log.info("res ={}", res.getBody());
        log.info("res.body = {}", res.getBody());
        HashMap hashMap = new ObjectMapper().readValue(res.getBody(), HashMap.class);

        log.info("response ={} ",hashMap.get("response"));
        LinkedHashMap response = (LinkedHashMap) hashMap.get("response");

        log.info("response.email = {}", response.get("email"));
    }
    @GetMapping("login/kakao")
    public void  redirectKakao(@RequestParam String code,
            @RequestParam String state) throws JsonProcessingException {

        log.info(code); // Authorization Code 값
        log.info(state); // 내가 임의로 만든 state 값



        val grantType = "grant_type=authorization_code";
        val clientId = "client_id=" + env.getProperty("key.kakao.id");
        val clientSecret = "client_secret=" + env.getProperty("key.kakao.secret");

        val url = "https://kauth.kakao.com/oauth/token" +
                "?" + grantType +
                "&" + clientId +
                "&" + clientSecret +
                "&code=" + code+
                "&redirect_uri" + "http://localhost:8080/login/kakao";


        RestTemplate restTemplate = new RestTemplate();
        HashMap body = restTemplate.postForEntity(url,null, HashMap.class).getBody();

        log.info(String.valueOf(body)); // --> access, refresh 토큰 값이 들어있다.

        String last = "https://kapi.kakao.com//v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + body.get("access_token"));
        HttpEntity request = new HttpEntity(headers);
        restTemplate = new RestTemplate();
        ResponseEntity<String> res = restTemplate.exchange(last, HttpMethod.GET, request,
                String.class);
        log.info("res ={}", res.getBody());
        log.info("res.body = {}", res.getBody());
        HashMap hashMap = new ObjectMapper().readValue(res.getBody(), HashMap.class);

        log.info("response ={} ",hashMap.get("kakao_account"));
        LinkedHashMap response = (LinkedHashMap) hashMap.get("kakao_account");

        log.info("response.email = {}", response.get("email"));
    }

}
