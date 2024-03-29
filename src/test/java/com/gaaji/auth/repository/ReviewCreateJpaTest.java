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

@DataJpaTest
public class ReviewCreateJpaTest {

	@Autowired
	JpaReviewRepository jpaReviewRepository;
	
	@AfterEach
	void afterEach() {
		this.jpaReviewRepository.deleteAll();
	}
	
	@Test
	void 추가테스트() {
		List<GoodManner> good = new ArrayList<GoodManner>();
		good.add(GoodManner.gm1);
		List<BadManner> bad = new ArrayList<BadManner>();
		bad.add(BadManner.bm2);
		Review review = Review.of(ReviewId.of("review"), PostId.of("post"), AuthId.of("sender"), AuthId.of("receiver"), good, bad, Comment.of("사진", "내용", "남가좌동", true));
		
		this.jpaReviewRepository.save(review);
		
		Review newReview = this.jpaReviewRepository.findById(ReviewId.of("review")).orElseThrow(null);
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
