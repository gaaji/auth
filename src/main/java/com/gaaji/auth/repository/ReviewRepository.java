package com.gaaji.auth.repository;

import java.util.Optional;
import java.util.UUID;

import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;

public interface ReviewRepository {

	default String nextId(){
        return UUID.randomUUID().toString();
    }

	void save(Review review);

	Optional<Review> findById(ReviewId of);

}
