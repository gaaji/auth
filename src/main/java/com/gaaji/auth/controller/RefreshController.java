package com.gaaji.auth.controller;

import com.gaaji.auth.applicationservice.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RefreshController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseEntity<Void> refresh(@RequestHeader ("refresh-token") String refreshToken){
        String accessToken = tokenService.refresh(refreshToken);
        return ResponseEntity.ok().header("access-token",accessToken).build();
    }

}
