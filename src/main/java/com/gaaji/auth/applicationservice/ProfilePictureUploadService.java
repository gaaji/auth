package com.gaaji.auth.applicationservice;

import org.springframework.web.multipart.MultipartFile;

public interface ProfilePictureUploadService {

    void uploadPicture(MultipartFile multipartFile, String authId);

}
