package com.gaaji.auth.applicationservice;

import org.springframework.web.multipart.MultipartFile;

import com.gaaji.auth.controller.dto.ReviewCreateRequest;

public interface ReviewCreateService {

	void createReview(String authId, MultipartFile multipartFile, ReviewCreateRequest dto);

}
