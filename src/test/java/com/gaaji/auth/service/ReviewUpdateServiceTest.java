package com.gaaji.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.auth.applicationservice.ReviewUpdateService;
import com.gaaji.auth.controller.dto.ReviewUpdateRequest;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.Comment;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;
import com.gaaji.auth.exception.EqualsSellerAndPurchaserException;
import com.gaaji.auth.exception.NoMatchSenderException;
import com.gaaji.auth.exception.NoSearchReviewException;
import com.gaaji.auth.repository.JpaReviewRepository;

@Transactional
@SpringBootTest
public class ReviewUpdateServiceTest {

	@Autowired
	ReviewUpdateService reviewUpdateService;
	
	@Autowired
	JpaReviewRepository jpaReviewRepository;
	
	@AfterEach
	void afterEach() {
		this.jpaReviewRepository.deleteAll();
	}
	
	@Test
	void 수정서비스 (){
	List<GoodManner> good = new ArrayList<GoodManner>();
	good.add(GoodManner.gm1);
	List<BadManner> bad = new ArrayList<BadManner>();
	bad.add(BadManner.bm2);
	Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", true));
	
	this.jpaReviewRepository.save(review);
	
	List<String> goodEdit = new ArrayList<String>();
	goodEdit.add("gm3");
	List<String> badEdit = new ArrayList<String>();
	badEdit.add("bm3");
	
	
	List<GoodManner> goodCheck = new ArrayList<GoodManner>();
	goodCheck.add(GoodManner.gm3);
	List<BadManner> badCheck = new ArrayList<BadManner>();
	badCheck.add(BadManner.bm3);
	ReviewUpdateRequest reviewUpdateRequest = new ReviewUpdateRequest("review", goodEdit, badEdit, "수정", false);
	this.reviewUpdateService.updateReview("sender", null, reviewUpdateRequest);
	
	 Review newReview = this.jpaReviewRepository.findByPostIdAndSenderId(PostId.of("post"), AuthId.of("sender"));
		assertThat(newReview.getPostId().getId()).isEqualTo("post");
		
		assertThat(newReview.getSenderId().getId()).isEqualTo("sender");
		assertThat(newReview.getReceiverId().getId()).isEqualTo("receiver");
		assertThat(newReview.getGoodManners().get(0)).isEqualTo(goodCheck.get(0));
		assertThat(newReview.getBadManners().get(0)).isEqualTo(badCheck.get(0));
		
		assertThat(newReview.getComment().getPictureUrl()).isEqualTo("사진");
		assertThat(newReview.getComment().getContents()).isEqualTo("수정");
		assertThat(newReview.getComment().isIspurchaser()).isEqualTo(true);
	
	}
	
	@Test
	void 수정서비스예외1 (){
		List<GoodManner> good = new ArrayList<GoodManner>();
		good.add(GoodManner.gm1);
		List<BadManner> bad = new ArrayList<BadManner>();
		bad.add(BadManner.bm2);
		Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", true));
		
		this.jpaReviewRepository.save(review);

		List<String> goodEdit = new ArrayList<String>();
		goodEdit.add("gm3");
		List<String> badEdit = new ArrayList<String>();
		badEdit.add("bm3");
		
		ReviewUpdateRequest reviewUpdateRequest = new ReviewUpdateRequest("review123", goodEdit, badEdit, "수정", false);
		 assertThatThrownBy(()->this.reviewUpdateService.updateReview("sender", null, reviewUpdateRequest)).isInstanceOf(NoSearchReviewException.class);

	}
	
	@Test
	void 수정서비스예외2 (){
		List<GoodManner> good = new ArrayList<GoodManner>();
		good.add(GoodManner.gm1);
		List<BadManner> bad = new ArrayList<BadManner>();
		bad.add(BadManner.bm2);
		Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", true));
		
		this.jpaReviewRepository.save(review);
		
		List<String> goodEdit = new ArrayList<String>();
		goodEdit.add("gm3");
		List<String> badEdit = new ArrayList<String>();
		badEdit.add("bm3");
		
		ReviewUpdateRequest reviewUpdateRequest = new ReviewUpdateRequest("review", goodEdit, badEdit, "수정", false);
		 assertThatThrownBy(()->this.reviewUpdateService.updateReview("aaa", null, reviewUpdateRequest)).isInstanceOf(NoMatchSenderException.class);
		

	}
	
}
