package com.gaaji.auth.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
	
	private final JpaReviewRepository jpaReviewRepository;

	@Override
	public void save(Review review) {
		this.jpaReviewRepository.save(review);
	}

	@Override
	public Optional<Review> findById(ReviewId reviewId) {
		return this.jpaReviewRepository.findById(reviewId);
	}

	@Override
	public Optional<Review> findByPostIdAndSenderId(PostId postId, AuthId senderId) {
		return this.jpaReviewRepository.findByPostIdAndSenderId(postId, senderId);
	}

}
