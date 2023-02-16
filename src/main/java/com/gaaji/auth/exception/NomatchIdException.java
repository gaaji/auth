package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.No_match_Id;

public class NomatchIdException extends AbstractApiException {

	public NomatchIdException() {
		super(No_match_Id);
	}

}
