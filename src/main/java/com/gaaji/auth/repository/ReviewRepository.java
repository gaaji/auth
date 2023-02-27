package com.gaaji.auth.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.gaaji.auth.controller.dto.GoodMannerCount;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;

public interface ReviewRepository {

	default String nextId(){
        return UUID.randomUUID().toString();
    }

	void save(Review review);

	Optional<Review> findById(ReviewId of);

	Optional<Review> findByPostIdAndSenderId(PostId postId, AuthId senderId);

	List<Review> findByReceiverIdAndComment_ContentsIsNotNullOrderByComment_CreatedAtDesc(AuthId receiverId);

	List<Review> findByReceiverId(String receiverId);

	List<Review> findDistinctByReceiverIdAndGoodMannersNotNull(AuthId receiverId);

	List<Review> findDistinctByReceiverIdAndBadMannersNotNull(AuthId receiverId);

	List<Review> findTop3ByReceiverIdAndComment_ContentsIsNotNullOrderByComment_CreatedAtDesc(AuthId receiverId);

}
