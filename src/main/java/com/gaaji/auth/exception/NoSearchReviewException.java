package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.No_Search_Review;

public class NoSearchReviewException extends AbstractApiException {

	public NoSearchReviewException() {
		super(No_Search_Review);
	}

}