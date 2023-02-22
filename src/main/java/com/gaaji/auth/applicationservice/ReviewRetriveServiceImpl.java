package com.gaaji.auth.applicationservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.auth.controller.dto.CommentRetrieveResponse;
import com.gaaji.auth.controller.dto.ReviewRetrieveResponse;
import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.exception.NoSearchReviewException;
import com.gaaji.auth.repository.AuthRepository;
import com.gaaji.auth.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewRetriveServiceImpl implements ReviewRetriveService{
	
	private final ReviewRepository reviewRepository;
	private final AuthRepository authRepository;

	@Override
	public ReviewRetrieveResponse retriveMyReview(String authId, String postId) {
		Review review = this.reviewRepository.findByPostIdAndSenderId(PostId.of(postId), AuthId.of(authId)).orElseThrow(NoSearchReviewException::new);
		return ReviewRetrieveResponse.of(review.getReviewId().getId(), review.getComment().getContents(), review.getComment().getPictureUrl(), review.getGoodManners(), review.getBadManners());
	}

	@Override
	public List<CommentRetrieveResponse> retriveComment(String authId) {
		List<Review> reviewList = this.reviewRepository.findByReceiverIdAndComment_ContentsIsNotNullOrderByComment_CreatedAtDesc(AuthId.of(authId));
		List<CommentRetrieveResponse> commentList = new ArrayList<CommentRetrieveResponse>();
		for(Review review : reviewList) {
			
			Auth auth = this.authRepository.findById(review.getSenderId().getId()).orElse(Auth.signUp(null, null, null));
			if(auth.getAuthIdForToken()==null) {
			auth.registerNickname(null);
			}
			commentList.add(CommentRetrieveResponse.of(review.getSenderId().getId(), auth.getNickname(), auth.getProfilePictureUrl(), review.getComment().getTown(), review.getComment().getContents(), review.getComment().getPictureUrl(), review.getComment().isIspurchaser(), review.getComment().getCreatedAt()));
			
		}
		
		return commentList;
	}

	
}
