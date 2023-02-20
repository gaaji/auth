package com.gaaji.auth.repository;

import org.springframework.stereotype.Repository;

import com.gaaji.auth.domain.Review;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
	
	private final JpaReviewRepository jpaReviewRepository;

	@Override
	public void save(Review review) {
		this.jpaReviewRepository.save(review);
	}

}
