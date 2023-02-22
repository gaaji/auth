package com.gaaji.auth.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaaji.auth.applicationservice.MannerRetriveService;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.GoodManner;
import lombok.RequiredArgsConstructor;

@RequestMapping("/manner")
@RequiredArgsConstructor
@RestController
public class MannerRetriveController {

	private final MannerRetriveService mannerRetriveService;
	
	@GetMapping("/good")
	private ResponseEntity<List<GoodManner>> retriveGoodMannerList() {
		List<GoodManner> goodMannerList = this.mannerRetriveService.retriveGoodMannerList();
		return ResponseEntity.ok(goodMannerList);
		}
	
	@GetMapping("/bad")
	private ResponseEntity<List<BadManner>> retriveBadMannerList() {
		List<BadManner> badMannerList = this.mannerRetriveService.retriveBadMannerList();
		return ResponseEntity.ok(badMannerList);
		}
}
