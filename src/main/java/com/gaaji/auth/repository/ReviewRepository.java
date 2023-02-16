package com.gaaji.auth.repository;

import java.util.UUID;

import com.gaaji.auth.domain.Review;

public interface ReviewRepository {

	default String nextId(){
        return UUID.randomUUID().toString();
    }

	void save(Review review);

}
