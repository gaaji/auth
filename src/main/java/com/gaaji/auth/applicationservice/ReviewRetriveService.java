package com.gaaji.auth.applicationservice;

import com.gaaji.auth.controller.dto.ReviewRetrieveResponse;

public interface ReviewRetriveService {

	ReviewRetrieveResponse retriveMyReview(String authId, String postId);

}
