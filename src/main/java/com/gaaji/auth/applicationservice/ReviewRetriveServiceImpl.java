package com.gaaji.auth.applicationservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.auth.controller.dto.BadMannerCount;
import com.gaaji.auth.controller.dto.CommentInfo;
import com.gaaji.auth.controller.dto.CommentRetrieveResponse;
import com.gaaji.auth.controller.dto.GoodMannerCount;
import com.gaaji.auth.controller.dto.MannerRetrieveResponse;
import com.gaaji.auth.controller.dto.PreviewReviewRetrieveResponse;
import com.gaaji.auth.controller.dto.ReviewRetrieveResponse;
import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.GoodManner;
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
	public List<CommentRetrieveResponse> retriveComment(String userId) {
		List<Review> reviewList = this.reviewRepository.findByReceiverIdAndComment_ContentsIsNotNullOrderByComment_CreatedAtDesc(AuthId.of(userId));
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

	@Override
	public MannerRetrieveResponse retriveManner(String authId, String userId) {
		MannerRetrieveResponse response = new MannerRetrieveResponse();
	
		if(authId.equals(userId)) {
			return MannerRetrieveResponse.of(getGoodMannerCount(userId), getBadMannerCount(userId));
		} else {
			return MannerRetrieveResponse.of(getGoodMannerCount(userId), null);
		}
	}

	private List<GoodMannerCount> getGoodMannerCount(String userId) {
		
		List<GoodManner> goodManners = new ArrayList<GoodManner>();
		List<GoodManner> goodMannerList = Arrays.asList(GoodManner.values());
		List<Review> reviewList = this.reviewRepository.findDistinctByReceiverIdAndGoodMannersNotNull(AuthId.of(userId));
		List<GoodMannerCount> goodMannerCountList = new ArrayList<GoodMannerCount>();
		
		if(reviewList.size() == 0) {
			return null;
		}
		
		for(Review review : reviewList) {
			goodManners.addAll(review.getGoodManners());
		}

		for(GoodManner goodManner : goodMannerList) {
			int count = Collections.frequency(goodManners, goodManner);
			if(count != 0) {
				goodMannerCountList.add(GoodMannerCount.of(goodManner, count));
			}
		}
		goodMannerCountList.sort(Comparator.comparing(GoodMannerCount::getCount).reversed());
		return goodMannerCountList;
	}

	private List<BadMannerCount> getBadMannerCount(String userId) {
		List<BadManner> badManners = new ArrayList<BadManner>();
		List<BadManner> badMannerList = Arrays.asList(BadManner.values());
		List<Review> reviewList = this.reviewRepository.findDistinctByReceiverIdAndBadMannersNotNull(AuthId.of(userId));
		List<BadMannerCount> badMannerCountList = new ArrayList<BadMannerCount>();
		
		if(reviewList.size() == 0) {
			return null;
		}
		
		for(Review review : reviewList) {
			badManners.addAll(review.getBadManners());
		}

		for(BadManner badManner : badMannerList) {
			int count = Collections.frequency(badManners, badManner);
			if(count != 0) {
				badMannerCountList.add(BadMannerCount.of(badManner, count));
			}
		}
		badMannerCountList.sort(Comparator.comparing(BadMannerCount::getCount).reversed());

		return badMannerCountList;
	}

	@Override
	public PreviewReviewRetrieveResponse retriveReview(String userId) {
		
		List<Review> reviewList = this.reviewRepository.findTop3ByReceiverIdAndComment_ContentsIsNotNullOrderByComment_CreatedAtDesc(AuthId.of(userId));
		List<CommentInfo> commentInfoList = new ArrayList<CommentInfo>();
		for(Review review : reviewList) {
			
			Auth auth = this.authRepository.findById(review.getSenderId().getId()).orElse(Auth.signUp(null, null, null));
			if(auth.getAuthIdForToken()==null) {
			auth.registerNickname(null);
			}
			commentInfoList.add(CommentInfo.of(review.getSenderId().getId(), auth.getNickname(), auth.getProfilePictureUrl(), review.getComment().getTown(), review.getComment().getContents(), review.getComment().getPictureUrl(), review.getComment().isIspurchaser(), review.getComment().getCreatedAt()));
			
		}
		List<GoodMannerCount> goodMannerCount= getGoodMannerCount(userId);
		if(goodMannerCount.size() > 3) {
			goodMannerCount.subList(3, goodMannerCount.size()).clear();
		}
		
		
		return PreviewReviewRetrieveResponse.of(commentInfoList, goodMannerCount);
	}

	
}
