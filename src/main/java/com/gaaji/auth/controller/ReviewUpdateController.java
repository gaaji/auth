package com.gaaji.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gaaji.auth.applicationservice.ReviewUpdateService;
import com.gaaji.auth.controller.dto.ReviewUpdateRequest;

import lombok.RequiredArgsConstructor;

@RequestMapping("/review")
@RequiredArgsConstructor
@RestController
public class ReviewUpdateController {

	private final ReviewUpdateService reviewUpdateService;
	
	@PatchMapping
	private ResponseEntity<Void> updateReview(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestPart("reviewCreateRequest") ReviewUpdateRequest dto, @RequestPart("file")MultipartFile multipartFile) {
		this.reviewUpdateService.updateReview(authId, multipartFile, dto);
		
		return ResponseEntity.ok().build();
	}
}
