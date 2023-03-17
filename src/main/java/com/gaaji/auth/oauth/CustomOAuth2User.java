package com.gaaji.auth.oauth;

import org.springframework.security.oauth2.core.user.OAuth2User;

public interface CustomOAuth2User extends OAuth2User {


    String getAuthId();

    String getNameAttributeKey();


}
