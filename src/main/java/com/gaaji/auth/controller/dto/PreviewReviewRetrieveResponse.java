package com.gaaji.auth.controller.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PreviewReviewRetrieveResponse {

	private List<CommentInfo> commentInfo;
	private List<GoodMannerCount> goodMannerCount;
	
	public static PreviewReviewRetrieveResponse of(List<CommentInfo> commentInfo,
			List<GoodMannerCount> goodMannerCount) {
		return new PreviewReviewRetrieveResponse(commentInfo, goodMannerCount);
	}

}
