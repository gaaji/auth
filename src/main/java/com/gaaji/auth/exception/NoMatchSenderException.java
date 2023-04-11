package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.No_Match_Sender;

public class NoMatchSenderException extends AbstractApiException {

	public NoMatchSenderException() {
		super(No_Match_Sender);
	}

}