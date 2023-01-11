package com.gaaji.auth.controller;

import com.gaaji.auth.applicationservice.NicknameRegisterService;
import com.gaaji.auth.controller.dto.NicknameRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NicknameRegisterController {

    private final NicknameRegisterService nicknameRegisterService;

    @PatchMapping("/auth/nickname")
    public ResponseEntity<Void> registerNickname(@RequestHeader("AUTH-ID") String authId, @RequestBody NicknameRegisterRequest dto){
        // body
        nicknameRegisterService.registerNickname(authId, dto.getNickname());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/profile/nickname")
    public ResponseEntity<Void> registerProfileNickname(@RequestHeader("AUTH-ID") String authId, @RequestBody NicknameRegisterRequest dto){
        // body
        nicknameRegisterService.registerNickname(authId, dto.getNickname());
        return ResponseEntity.ok().build();
    }

}
