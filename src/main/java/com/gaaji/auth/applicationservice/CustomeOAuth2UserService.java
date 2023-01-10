package com.gaaji.auth.applicationservice;

import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.repository.AuthRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomeOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final AuthRepository authRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();


        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());



        Auth user = saveOrUpdate(attributes);

        Map<String, Object> attribute = new HashMap<>();
        attribute.put("id",user.getAuthIdForToken());


        return new DefaultOAuth2User(Collections.emptyList(),
                attribute,
                attributes.getNameAttributeKey());
    }

    private Auth saveOrUpdate(OAuthAttributes attributes) {
        Auth user = authRepository.findByPlatformInfo(attributes.getPlatformType(), attributes.getEmail())  // <- 조회 없으면 save 후 반환, 있으면 반환,
                .orElse(authRepository.save(attributes.getPlatformType(), attributes.getEmail()));

        return user;
    }

}
