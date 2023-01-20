package com.gaaji.auth.controller;


import com.gaaji.auth.applicationservice.AuthRetrieveService;
import com.gaaji.auth.controller.dto.RetrieveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthRetrieveController {

    private final AuthRetrieveService authRetrieveService;

    @GetMapping("/auth/{authId}")
    public ResponseEntity<RetrieveResponse> retrieveAuth(@PathVariable("authId") String authId){

        RetrieveResponse dto = authRetrieveService.retrieveAuth(authId);
        return ResponseEntity.ok(dto);
    }

}
