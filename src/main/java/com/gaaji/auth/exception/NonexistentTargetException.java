package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.Nonexistent_Target;

public class NonexistentTargetException extends AbstractApiException {

	public NonexistentTargetException() {
		super(Nonexistent_Target);
	}

}