package com.gaaji.auth.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gaaji.auth.applicationservice.ReviewRetriveService;
import com.gaaji.auth.controller.dto.CommentRetrieveResponse;
import com.gaaji.auth.controller.dto.MannerRetrieveResponse;
import com.gaaji.auth.controller.dto.PreviewReviewRetrieveResponse;
import com.gaaji.auth.controller.dto.ReviewRetrieveResponse;
import com.gaaji.auth.domain.Review;

import lombok.RequiredArgsConstructor;

@RequestMapping("/review")
@RequiredArgsConstructor
@RestController
public class ReviewRetriveController {

	private final ReviewRetriveService reviewRetriveService;
	
	@GetMapping("/my")
	private ResponseEntity<ReviewRetrieveResponse> retriveMyReview(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestBody String postId) {
		ReviewRetrieveResponse dto = this.reviewRetriveService.retriveMyReview(authId, postId);
		return ResponseEntity.ok(dto);
		}
	
	@GetMapping("/comment")
	private ResponseEntity<List<CommentRetrieveResponse>> retriveComments(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestBody String userId) {
		List<CommentRetrieveResponse> dto = this.reviewRetriveService.retriveComment(userId);
		return ResponseEntity.ok(dto);
		}
	
	@GetMapping("/manner")
	private ResponseEntity<MannerRetrieveResponse> retriveManner(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestBody String userId) {
		MannerRetrieveResponse dto = this.reviewRetriveService.retriveManner(authId, userId);
		return ResponseEntity.ok(dto);
		}
	
	@GetMapping
	private ResponseEntity<PreviewReviewRetrieveResponse> retriveReview(@RequestHeader(HttpHeaders.AUTHORIZATION) String authId, @RequestBody String userId) {
		PreviewReviewRetrieveResponse dto = this.reviewRetriveService.retriveReview(userId);
		return ResponseEntity.ok(dto);
		}
}
