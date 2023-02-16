package com.gaaji.auth.controller.dto;

import java.util.List;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class ReviewCreateRequest {
	
	MultipartFile multipartFile;
	private String postId;
	private String sellerId;
	private String purchaserId;
	private List<String> goodManners;
	private List<String> badManners;
	private String contents;
}
