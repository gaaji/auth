package com.gaaji.auth.controller.dto;

import java.util.List;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewUpdateRequest {
	
	private String reviewId;
	private List<String> goodManners;
	private List<String> badManners;
	private String contents;
	private boolean pictureChanged;
}
