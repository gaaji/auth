package com.gaaji.auth.applicationservice;

import java.util.List;

import com.gaaji.auth.controller.dto.CommentRetrieveResponse;
import com.gaaji.auth.controller.dto.MannerRetrieveResponse;
import com.gaaji.auth.controller.dto.PreviewReviewRetrieveResponse;
import com.gaaji.auth.controller.dto.ReviewRetrieveResponse;

public interface ReviewRetriveService {

	ReviewRetrieveResponse retriveMyReview(String authId, String postId);

	List<CommentRetrieveResponse> retriveComment(String userId);

	MannerRetrieveResponse retriveManner(String authId, String userId);

	PreviewReviewRetrieveResponse retriveReview(String userId);

}
