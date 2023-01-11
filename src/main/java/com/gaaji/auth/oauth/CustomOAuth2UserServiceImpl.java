package com.gaaji.auth.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.domain.PlatformType;
import com.gaaji.auth.repository.AuthRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserServiceImpl implements CustomOAuth2UserService,
        OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AuthRepository authRepository;
    private final Environment env;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes());


        Auth user = saveOrFind(attributes.getPlatformType(), attributes.getEmail());

        return CustomOAuth2UserImpl.builder()
                .authId(user.getAuthIdForToken())
                .nameAttributeKey(attributes.getNameAttributeKey())
                .authorities(Collections.emptyList())
                .attributes(attributes.getAttributes())
                .build();
    }


    @Override
    public void kakaoLogin(String code) {
        val grantType = "grant_type=authorization_code";
        val clientId = "client_id=" + env.getProperty("key.kakao.id");
        val clientSecret = "client_secret=" + env.getProperty("key.kakao.secret");

        val url = "https://kauth.kakao.com/oauth/token" +
                "?" + grantType +
                "&" + clientId +
                "&" + clientSecret +
                "&code=" + code +
                "&redirect_uri" + "http://localhost:8080/login/kakao";

        RestTemplate restTemplate = new RestTemplate();
        HashMap<String,String> body  = restTemplate.postForEntity(url, null, HashMap.class).getBody();

        String responseCode = "kakao_account";
        String last = "https://kapi.kakao.com//v2/user/me";
        String email = getEmailFromServiceProvider(body.get("access_token"), last, responseCode);
        saveOrFind(PlatformType.KAKAO, email);
    }

    @Override
    public void naverLogin(String code, String state) {
        val grantType = "grant_type=authorization_code";
        val clientId = "client_id=" + env.getProperty("key.naver.id");
        val clientSecret = "client_secret=" + env.getProperty("key.naver.secret");

        val url = "https://nid.naver.com/oauth2.0/token" +
                "?" + grantType +
                "&" + clientId +
                "&" + clientSecret +
                "&code=" + code +
                "&state=" + state;
        String last = "https://openapi.naver.com/v1/nid/me";
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String,String> body = restTemplate.getForEntity(url, HashMap.class).getBody();
        String responseCode = "response";
        String email = getEmailFromServiceProvider(body.get("access_token"), last, responseCode);
        saveOrFind(PlatformType.NAVER, email);
    }

    private String getEmailFromServiceProvider(String token, String last, String responseCode) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity request = new HttpEntity(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> res = restTemplate.exchange(last, HttpMethod.GET, request,
                    String.class);

            HashMap hashMap = new ObjectMapper().readValue(res.getBody(), HashMap.class);
            LinkedHashMap<String, String> response =
                    (LinkedHashMap) hashMap.get(
                            responseCode);
            return response.get("email");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(); // CustomJSONProcessing Exception
        }
    }

    private Auth saveOrFind(PlatformType platformType, String email) {
        return authRepository.findByPlatformInfo(platformType,
                email).orElseGet(() -> authRepository.save(platformType, email));
    }

}
