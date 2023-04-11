package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.No_Review;

public class NoReviewException extends AbstractApiException {

	public NoReviewException() {
		super(No_Review);
	}

}