package com.gaaji.auth.controller;


import com.gaaji.auth.applicationservice.ProfilePictureUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ProfilePictureUploadController {

    private final ProfilePictureUploadService profilePictureUploadService;


    @PostMapping("/auth/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestPart("file")MultipartFile multipartFile,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authId){
        profilePictureUploadService.uploadPicture(multipartFile,authId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
