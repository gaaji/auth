package com.gaaji.auth.applicationservice;

import com.gaaji.auth.controller.dto.ReviewCreateRequest;

public interface ReviewCreateService {

	void createReview(String authId, ReviewCreateRequest dto);

}
