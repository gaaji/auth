package com.gaaji.auth.applicationservice;

import com.gaaji.auth.adaptor.S3Uploader;
import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.exception.AuthIdNotFoundException;
import com.gaaji.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional
@Service
public class ProfilePictureUploadServiceImpl implements
        ProfilePictureUploadService {

    private final S3Uploader s3Uploader;
    private final AuthRepository authRepository;

    @Override
    public void uploadPicture(MultipartFile multipartFile, String authId) {
        getMember(authId)
                .registerProfilePicture(uploadImage(multipartFile));
    }

    private String uploadImage(MultipartFile multipartFile) {
        return s3Uploader.upload(multipartFile);
    }

    private Auth getMember(String authId) {
        return authRepository.findById(authId)
                .orElseThrow(AuthIdNotFoundException::new);
    }
}
