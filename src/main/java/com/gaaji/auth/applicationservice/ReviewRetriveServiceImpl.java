package com.gaaji.auth.applicationservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.auth.controller.dto.ReviewRetrieveResponse;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.exception.NoSearchReviewException;
import com.gaaji.auth.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewRetriveServiceImpl implements ReviewRetriveService{
	
	private final ReviewRepository reviewRepository;

	@Override
	public ReviewRetrieveResponse retriveMyReview(String authId, String postId) {
		Review review = this.reviewRepository.findByPostIdAndSenderId(PostId.of(postId), AuthId.of(authId)).orElseThrow(NoSearchReviewException::new);
		return ReviewRetrieveResponse.of(review.getReviewId().getId(), review.getComment().getContents(), review.getComment().getPictureUrl(), review.getGoodManners(), review.getBadManners());
	}

	
}
