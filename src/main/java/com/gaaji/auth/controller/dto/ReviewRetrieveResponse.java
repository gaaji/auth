package com.gaaji.auth.controller.dto;

import java.util.List;

import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.GoodManner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class ReviewRetrieveResponse {

	private String reviewId;
    private String contents;
    private String pictureUrl;
    private List<GoodManner> goodManners;
    private List<BadManner> badManners;
    
    public static ReviewRetrieveResponse of(String reviewId, String contents, String pictureUrl, List<GoodManner> goodManners, List<BadManner> badManners) {
        return new ReviewRetrieveResponse(reviewId, contents, pictureUrl, goodManners, badManners);
    }

}
