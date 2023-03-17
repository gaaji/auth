package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.No_Town;

public class NoTownException extends AbstractApiException {

	public NoTownException() {
		super(No_Town);
	}

}