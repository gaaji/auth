package com.gaaji.auth.applicationservice;

import org.springframework.web.multipart.MultipartFile;

import com.gaaji.auth.controller.dto.ReviewUpdateRequest;

public interface ReviewUpdateService {

	void updateReview(String authId, MultipartFile multipartFile, ReviewUpdateRequest dto);

}
