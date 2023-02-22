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

import com.gaaji.auth.applicationservice.ReviewRetriveService;
import com.gaaji.auth.applicationservice.ReviewUpdateService;
import com.gaaji.auth.controller.dto.CommentRetrieveResponse;
import com.gaaji.auth.controller.dto.ReviewRetrieveResponse;
import com.gaaji.auth.controller.dto.ReviewUpdateRequest;
import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.Comment;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.domain.PlatformType;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;
import com.gaaji.auth.exception.EqualsSellerAndPurchaserException;
import com.gaaji.auth.exception.NoMatchSenderException;
import com.gaaji.auth.exception.NoSearchReviewException;
import com.gaaji.auth.repository.JpaAuthRepository;
import com.gaaji.auth.repository.JpaReviewRepository;

@Transactional
@SpringBootTest
public class ReviewRetriveServiceTest {

	@Autowired
	ReviewRetriveService reviewRetriveService;
	
	@Autowired
	JpaReviewRepository jpaReviewRepository;
	
	@Autowired
	JpaAuthRepository jpaAuthRepository;
	
	@AfterEach
	void afterEach() {
		this.jpaReviewRepository.deleteAll();
	}
	
	@Test
	void 내조회서비스 (){
	List<GoodManner> good = new ArrayList<GoodManner>();
	good.add(GoodManner.gm1);
	List<BadManner> bad = new ArrayList<BadManner>();
	bad.add(BadManner.bm2);
	Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", "남가좌동", true));
	
	this.jpaReviewRepository.save(review);
	
	ReviewRetrieveResponse newReview = this.reviewRetriveService.retriveMyReview("sender","post");
	
		assertThat(newReview.getReviewId()).isEqualTo("review");
		
		assertThat(newReview.getContents()).isEqualTo("내용");
		assertThat(newReview.getPictureUrl()).isEqualTo("사진");
		assertThat(newReview.getGoodManners().get(0)).isEqualTo(GoodManner.gm1);
		assertThat(newReview.getBadManners().get(0)).isEqualTo(BadManner.bm2);

	}
	
	@Test
	void 수정서비스예외1 (){
		
		 assertThatThrownBy(()->this.reviewRetriveService.retriveMyReview("sender", "nope")).isInstanceOf(NoSearchReviewException.class);

	}

	@Test
	void 후기조회서비스 (){
	List<GoodManner> good = new ArrayList<GoodManner>();
	good.add(GoodManner.gm1);
	List<BadManner> bad = new ArrayList<BadManner>();
	bad.add(BadManner.bm2);
	Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", "남가좌동", true));
	this.jpaReviewRepository.save(review);
	Review review1 = Review.of(ReviewId.of("review1"), PostId.of("post1"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", null, "남가좌동", true));
	this.jpaReviewRepository.save(review1);
	Review review2 = Review.of(ReviewId.of("review2"), PostId.of("post2"), AuthId.of("sender1"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용1", "남가좌동", false));
	this.jpaReviewRepository.save(review2);
	jpaAuthRepository.save(Auth.signUp("sender", PlatformType.NAVER, "test@naver.com"));
	
	
	
	
	
	List<CommentRetrieveResponse> reviewList = this.reviewRetriveService.retriveComment("receiver");
	assertThat(reviewList.size()).isEqualTo(2);
	CommentRetrieveResponse newReview1 = reviewList.get(0);
	assertThat(newReview1.getSenderId()).isEqualTo("sender1");
	assertThat(newReview1.getNickname()).isEqualTo(null);
	assertThat(newReview1.getProfilePictureUrl()).isEqualTo(null);
	assertThat(newReview1.getTown()).isEqualTo("남가좌동");
	assertThat(newReview1.getContents()).isEqualTo("내용1");
	assertThat(newReview1.getPictureUrl()).isEqualTo("사진");
	assertThat(newReview1.isIspurchaser()).isEqualTo(false);

	
	CommentRetrieveResponse newReview = reviewList.get(1);
	assertThat(newReview.getSenderId()).isEqualTo("sender");
	assertThat(newReview.getNickname()).isEqualTo("익명");
	assertThat(newReview.getProfilePictureUrl()).isEqualTo(null);
	assertThat(newReview.getTown()).isEqualTo("남가좌동");
	assertThat(newReview.getContents()).isEqualTo("내용");
	assertThat(newReview.getPictureUrl()).isEqualTo("사진");
	assertThat(newReview.isIspurchaser()).isEqualTo(true);

	}
	
}
