package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.No_match_Id;

public class NoMatchIdException extends AbstractApiException {

	public NoMatchIdException() {
		super(No_match_Id);
	}

}
