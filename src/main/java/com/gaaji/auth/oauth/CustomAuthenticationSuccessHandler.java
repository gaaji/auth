package com.gaaji.auth.oauth;

import com.gaaji.auth.applicationservice.TokenService;
import com.gaaji.auth.controller.dto.TokenResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {

    private final TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        CustomOAuth2User principal = (CustomOAuth2User) oAuth2AuthenticationToken.getPrincipal();

        TokenResponse tokens = tokenService.createTokens(principal.getAuthId());

        response.addHeader("access_token", tokens.getAccessToken());
        response.addHeader("refresh_token", tokens.getRefreshToken());
    }

    /* ... */

}