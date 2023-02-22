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

import com.gaaji.auth.applicationservice.ReviewCreateService;
import com.gaaji.auth.controller.dto.ReviewCreateRequest;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.Comment;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;
import com.gaaji.auth.exception.EqualsSellerAndPurchaserException;
import com.gaaji.auth.exception.NoMatchIdException;
import com.gaaji.auth.exception.NoReviewException;
import com.gaaji.auth.exception.NoSearchReviewException;
import com.gaaji.auth.exception.NoTownException;
import com.gaaji.auth.exception.NonexistentTargetException;
import com.gaaji.auth.repository.JpaReviewRepository;

@Transactional
@SpringBootTest
public class ReviewCreateServiceTest {

	@Autowired
	ReviewCreateService reviewCreateService;
	
	@Autowired
	JpaReviewRepository jpaReviewRepository;
	
	@AfterEach
	void afterEach() {
		this.jpaReviewRepository.deleteAll();
	}
	
	@Test
	void 추가서비스 (){
		List<String> no = new ArrayList<String>();
		List<String> good = new ArrayList<String>();
		List<String> bad = new ArrayList<String>();
		good.add("gm1");
		good.add("gm5");
		bad.add("bm3");
		bad.add("bm4");
		
		 ReviewCreateRequest dto1 = new ReviewCreateRequest("post", "aaa", "purchaser", "남가좌동", no, no, "빨라요");
		 ReviewCreateRequest dto2 = new ReviewCreateRequest("post1", "seller", "aaa", "남가좌동", good, bad, null);
		 
		 
		 reviewCreateService.createReview("aaa", null, dto1);
		 reviewCreateService.createReview("aaa", null, dto2);
		 
		 
		 
		 Review newReview = this.jpaReviewRepository.findByPostIdAndSenderId(PostId.of("post"), AuthId.of("aaa")).orElseThrow(NoSearchReviewException::new);
			assertThat(newReview.getPostId().getId()).isEqualTo("post");
			
			assertThat(newReview.getSenderId().getId()).isEqualTo("aaa");
			assertThat(newReview.getReceiverId().getId()).isEqualTo("purchaser");
			assertThat(newReview.getGoodManners()).isEqualTo(null);
			assertThat(newReview.getBadManners()).isEqualTo(null);
			
			assertThat(newReview.getComment().getPictureUrl()).isEqualTo(null);
			assertThat(newReview.getComment().getContents()).isEqualTo("빨라요");
			assertThat(newReview.getComment().getTown()).isEqualTo("남가좌동");
			assertThat(newReview.getComment().isIspurchaser()).isEqualTo(false);
		 
			 Review newReview1 = this.jpaReviewRepository.findByPostIdAndSenderId(PostId.of("post1"), AuthId.of("aaa")).orElseThrow(NoSearchReviewException::new);
			assertThat(newReview1.getPostId().getId()).isEqualTo("post1");
			
			assertThat(newReview1.getSenderId().getId()).isEqualTo("aaa");
			assertThat(newReview1.getReceiverId().getId()).isEqualTo("seller");
			assertThat(newReview1.getGoodManners().get(0)).isEqualTo(GoodManner.gm1);
			assertThat(newReview1.getGoodManners().get(1)).isEqualTo(GoodManner.gm5);
			assertThat(newReview1.getBadManners().get(0)).isEqualTo(BadManner.bm3);
			assertThat(newReview1.getBadManners().get(1)).isEqualTo(BadManner.bm4);		
			assertThat(newReview1.getComment().getPictureUrl()).isEqualTo(null);
			assertThat(newReview1.getComment().getContents()).isEqualTo(null);
			assertThat(newReview.getComment().getTown()).isEqualTo("남가좌동");
			assertThat(newReview1.getComment().isIspurchaser()).isEqualTo(true);
	}
	
	@Test
	void 추가서비스에러케이스5타운이없는경우 (){
		List<String> no = new ArrayList<String>();
		 ReviewCreateRequest dto = new ReviewCreateRequest("post", "purchaser", "purchaser", null, no, no, "123");
		 assertThatThrownBy(()->reviewCreateService.createReview("aaa", null, dto)).isInstanceOf(NoTownException.class);
	}
	
	@Test
	void 추가서비스에러케이스4판매자와구매자가같은경우 (){
		List<String> no = new ArrayList<String>();
		 ReviewCreateRequest dto = new ReviewCreateRequest("post", "purchaser", "purchaser", "남가좌동", no, no, "123");
		 assertThatThrownBy(()->reviewCreateService.createReview("aaa", null, dto)).isInstanceOf(EqualsSellerAndPurchaserException.class);
	}
	
	@Test
	void 추가서비스에러케이스3후기작성자가구매자와판매자가아닌경우 (){
		List<String> no = new ArrayList<String>();
		ReviewCreateRequest dto = new ReviewCreateRequest("post", "seller", "purchaser", "남가좌동", no, no, "123");

		 assertThatThrownBy(()->reviewCreateService.createReview("aaa", null, dto)).isInstanceOf(NoMatchIdException.class);
	}
	
	@Test
	void 추가서비스에러케이스2후기가없는경우 (){
		List<String> no = new ArrayList<String>();
		 ReviewCreateRequest dto = new ReviewCreateRequest("post", "aaa", "purchaser", "남가좌동", no, no, null);

		 assertThatThrownBy(()->reviewCreateService.createReview("aaa", null, dto)).isInstanceOf(NoReviewException.class);
	}
	
	@Test
	void 추가서비스에러케이스1글과관련된아이디없는경우 (){
		List<String> no = new ArrayList<String>();
		 ReviewCreateRequest dto = new ReviewCreateRequest("aaa", null, null, "남가좌동", no, no, "123");

		 assertThatThrownBy(()->reviewCreateService.createReview("aaa", null, dto)).isInstanceOf(NonexistentTargetException.class);
	}
}
