package com.gaaji.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;

public interface JpaReviewRepository extends JpaRepository<Review, ReviewId>{

	Optional<Review> findByPostIdAndSenderId(PostId postId, AuthId senderId);

}
