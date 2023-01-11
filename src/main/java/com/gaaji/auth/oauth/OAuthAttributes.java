package com.gaaji.auth.oauth;

import com.gaaji.auth.domain.PlatformType;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;

    private String email;

    private PlatformType platformType;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,  String email, PlatformType platformType) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.platformType = platformType;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (registrationId.equals("kakao")) {
            return ofKakao(userNameAttributeName, attributes);
        } else if (registrationId.equals("naver")) {
            return ofNaver(userNameAttributeName,attributes);
        }
        // TODO Facebook 찾아보기
        return ofGoogle(userNameAttributeName, attributes);
    }
    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");  // 카카오로 받은 데이터에서 계정 정보가 담긴 kakao_account 값을 꺼낸다.

        return  OAuthAttributes.builder()
                .email((String) kakao_account.get("email"))
                .platformType(PlatformType.KAKAO)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");    // 네이버가지로 profile(nickname, image_url.. 등) 정보가 담긴 값을 꺼낸다.

        return  OAuthAttributes.builder()
                .email((String) response.get("email"))
                .platformType(PlatformType.NAVER)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .email((String) attributes.get("email"))
                .platformType(PlatformType.GOOGLE)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return null;
    }
}