package com.gaaji.auth.config;

import com.gaaji.auth.oauth.CustomAuthenticationSuccessHandler;
import com.gaaji.auth.oauth.CustomOAuth2UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserServiceImpl customOAuth2UserServiceImpl;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/**", "/h2-console").permitAll()
                .anyRequest().authenticated()
                .and()

                .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserServiceImpl)
                    .and().successHandler(customAuthenticationSuccessHandler)
                .and()
                .build();


    }
}

