package com.gaaji.auth.oauth;

import java.util.Collection;
import java.util.Map;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

public class CustomOAuth2UserImpl implements CustomOAuth2User {

    private String authId;
    private Map<String, Object> attributes;
    private Collection<? extends GrantedAuthority> authorities;
    private String nameAttributeKey;


    @Builder
    public CustomOAuth2UserImpl(String authId, Map<String, Object> attributes,
            Collection<? extends GrantedAuthority> authorities, String nameAttributeKey) {
        this.authId = authId;
        this.attributes = attributes;
        this.authorities = authorities;
        this.nameAttributeKey = nameAttributeKey;
    }

    @Override
    public String getAuthId() {
        return authId;
    }

    @Override
    public String getNameAttributeKey() {
        return nameAttributeKey;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getName() {
        return this.attributes.get(nameAttributeKey).toString();
    }
}
