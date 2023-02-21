package com.gaaji.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gaaji.auth.applicationservice.ReviewRetriveService;
import com.gaaji.auth.controller.dto.ReviewRetrieveResponse;
import com.gaaji.auth.domain.Review;

import lombok.RequiredArgsConstructor;

@RequestMapping("/review")
@RequiredArgsConstructor
@RestController
public class ReviewRetriveController {

	private final ReviewRetriveService reviewRetriveService;
	
	@GetMapping("/my")
	private ResponseEntity<ReviewRetrieveResponse> createReview(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestBody String postId) {
		ReviewRetrieveResponse dto = this.reviewRetriveService.retriveMyReview(authId, postId);
		return ResponseEntity.ok(dto);
		}
}
