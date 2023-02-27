package com.gaaji.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gaaji.auth.controller.dto.GoodMannerCount;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;

public interface JpaReviewRepository extends JpaRepository<Review, ReviewId>{

	Optional<Review> findByPostIdAndSenderId(PostId postId, AuthId senderId);

	List<Review> findByReceiverIdAndComment_ContentsIsNotNullOrderByComment_CreatedAtDesc(AuthId receiverId);

	List<Review> findByReceiverId(String receiverId);

	List<Review> findDistinctByReceiverIdAndGoodMannersNotNull(AuthId receiverId);

	List<Review> findDistinctByReceiverIdAndBadMannersNotNull(AuthId receiverId);

}
