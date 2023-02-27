package com.gaaji.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.Comment;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;
import com.gaaji.auth.exception.NoSearchReviewException;

@DataJpaTest
public class ReviewRetriveJpaTest {

	@Autowired
	JpaReviewRepository jpaReviewRepository;
	
	@AfterEach
	void afterEach() {
		this.jpaReviewRepository.deleteAll();
	}
	
	@Test
	void 내조회테스트() {
		List<GoodManner> good = new ArrayList<GoodManner>();
		good.add(GoodManner.gm1);
		List<BadManner> bad = new ArrayList<BadManner>();
		bad.add(BadManner.bm2);
		Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", "남가좌동", true));
		
		this.jpaReviewRepository.save(review);
		
		Review newReview = this.jpaReviewRepository.findByPostIdAndSenderId(PostId.of("post"), AuthId.of("sender")).orElseThrow(NoSearchReviewException::new);
		assertThat(newReview.getReviewId().getId()).isEqualTo("review");
		assertThat(newReview.getPostId().getId()).isEqualTo("post");
		
		assertThat(newReview.getSenderId().getId()).isEqualTo("sender");
		assertThat(newReview.getReceiverId().getId()).isEqualTo("receiver");
		assertThat(newReview.getGoodManners().get(0)).isEqualTo(GoodManner.gm1);
		assertThat(newReview.getBadManners().get(0)).isEqualTo(BadManner.bm2);
		
		assertThat(newReview.getComment().getPictureUrl()).isEqualTo("사진");
		assertThat(newReview.getComment().getContents()).isEqualTo("내용");
		assertThat(newReview.getComment().getTown()).isEqualTo("남가좌동");
		assertThat(newReview.getComment().isIspurchaser()).isEqualTo(true);
		
	}
	
	@Test
	void 후기조회테스트() {
		List<GoodManner> good = new ArrayList<GoodManner>();
		good.add(GoodManner.gm1);
		List<BadManner> bad = new ArrayList<BadManner>();
		bad.add(BadManner.bm2);
		Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", "남가좌동", true));
		Review review1 = Review.of(ReviewId.of("review1"), PostId.of("post1"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", null, "남가좌동", true));

		this.jpaReviewRepository.save(review);
		this.jpaReviewRepository.save(review1);
		
		List<Review> reviewList = this.jpaReviewRepository.findByReceiverIdAndComment_ContentsIsNotNullOrderByComment_CreatedAtDesc(AuthId.of("receiver"));
		
		assertThat(reviewList.size()).isEqualTo(1);
		Review newReview = reviewList.get(0);
		assertThat(newReview.getReviewId().getId()).isEqualTo("review");
		assertThat(newReview.getPostId().getId()).isEqualTo("post");
		
		assertThat(newReview.getSenderId().getId()).isEqualTo("sender");
		assertThat(newReview.getReceiverId().getId()).isEqualTo("receiver");
		assertThat(newReview.getGoodManners().get(0)).isEqualTo(GoodManner.gm1);
		assertThat(newReview.getBadManners().get(0)).isEqualTo(BadManner.bm2);
		
		assertThat(newReview.getComment().getPictureUrl()).isEqualTo("사진");
		assertThat(newReview.getComment().getContents()).isEqualTo("내용");
		assertThat(newReview.getComment().getTown()).isEqualTo("남가좌동");
		assertThat(newReview.getComment().isIspurchaser()).isEqualTo(true);
		
	}
	
	@Test
	void 매너조회테스트() {
		List<GoodManner> good = new ArrayList<GoodManner>();
		good.add(GoodManner.gm1);
		List<BadManner> bad = new ArrayList<BadManner>();
		bad.add(BadManner.bm2);
		
		List<GoodManner> notGood = new ArrayList<GoodManner>();
		List<BadManner> notBad = new ArrayList<BadManner>();
		Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", "남가좌동", true));
		Review review1 = Review.of(ReviewId.of("review1"), PostId.of("post1"), AuthId.of("sender"), AuthId.of("receiver"), notGood, notBad, Comment.of("사진", null, "남가좌동", true));

		this.jpaReviewRepository.save(review);
		this.jpaReviewRepository.save(review1);

		List<Review> reviewList = this.jpaReviewRepository.findDistinctByReceiverIdAndGoodMannersNotNull(AuthId.of("receiver"));
		
		assertThat(reviewList.size()).isEqualTo(1);
		Review newReview = reviewList.get(0);
		assertThat(newReview.getReviewId().getId()).isEqualTo("review");
		assertThat(newReview.getPostId().getId()).isEqualTo("post");
		
		assertThat(newReview.getSenderId().getId()).isEqualTo("sender");
		assertThat(newReview.getReceiverId().getId()).isEqualTo("receiver");
		assertThat(newReview.getGoodManners().get(0)).isEqualTo(GoodManner.gm1);
		assertThat(newReview.getBadManners().get(0)).isEqualTo(BadManner.bm2);
		
		assertThat(newReview.getComment().getPictureUrl()).isEqualTo("사진");
		assertThat(newReview.getComment().getContents()).isEqualTo("내용");
		assertThat(newReview.getComment().getTown()).isEqualTo("남가좌동");
		assertThat(newReview.getComment().isIspurchaser()).isEqualTo(true);
		
		
	}

}
