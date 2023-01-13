package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.AUTH_ID_NOT_FOUND;

public class AuthIdNotFoundException extends AbstractApiException{

    public AuthIdNotFoundException() {
        super(AUTH_ID_NOT_FOUND);
    }
}
