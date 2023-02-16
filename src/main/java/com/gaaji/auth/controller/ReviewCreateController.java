package com.gaaji.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaaji.auth.applicationservice.ReviewCreateService;
import com.gaaji.auth.controller.dto.ReviewCreateRequest;
import lombok.RequiredArgsConstructor;

@RequestMapping("/review")
@RequiredArgsConstructor
@RestController
public class ReviewCreateController {

	private final ReviewCreateService reviewCreateService;
	
	@PostMapping
	private ResponseEntity<Void> createReview(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestBody ReviewCreateRequest dto) {
		this.reviewCreateService.createReview(authId, dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
