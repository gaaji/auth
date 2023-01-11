package com.gaaji.auth.oauth;

import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.repository.AuthRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AuthRepository authRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("[OAUTH] HELLO");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();



        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                oAuth2User.getAttributes());

        log.info("registrationId : {}", registrationId);
        log.info("userNameAttributeName : {}", userNameAttributeName);
        log.info("attributes.attributes = {} ", attributes.getAttributes());
        log.info("attributes.email : {}", attributes.getEmail());
        log.info("attributes.type : {}", attributes.getPlatformType());

        Auth user = saveOrUpdate(attributes);


        return CustomOAuth2UserImpl.builder()
                .authId(user.getAuthIdForToken())
                .nameAttributeKey(attributes.getNameAttributeKey())
                .authorities(Collections.emptyList())
                .attributes(attributes.getAttributes())
                .build();
    }

    private Auth saveOrUpdate(OAuthAttributes attributes) {
        return authRepository.findByPlatformInfo(attributes.getPlatformType(),
                        attributes.getEmail())  // <- 조회 없으면 save 후 반환, 있으면 반환,
                .orElse(authRepository.save(attributes.getPlatformType(), attributes.getEmail()));
    }

}
