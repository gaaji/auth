package com.gaaji.auth.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.gaaji.auth.applicationservice.MannerRetriveService;
import com.gaaji.auth.applicationservice.MannerRetriveServiceImpl;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.GoodManner;

public class MannerRetriveServiceTest {

	@Test
	void 매너리스트테스트() {
		MannerRetriveService mannerRetriveService = new MannerRetriveServiceImpl();
		
		//good
		List<GoodManner> goodMannerList = mannerRetriveService.retriveGoodMannerList();
		int i= 1;
		for(GoodManner goodManner: goodMannerList) {
			assertThat(goodManner.name()).isEqualTo("gm"+i);
			assertThat(goodManner.getMannerContents()).isEqualTo(GoodManner.valueOf("gm"+i).getMannerContents());
			i++;
		}
		//bad
		
		List<BadManner> badMannerList = mannerRetriveService.retriveBadMannerList();
		i= 1;
		for(BadManner badManner: badMannerList) {
			assertThat(badManner.name()).isEqualTo("bm"+i);
			assertThat(badManner.getMannerContents()).isEqualTo(BadManner.valueOf("bm"+i).getMannerContents());
			i++;
		}
	}
}
