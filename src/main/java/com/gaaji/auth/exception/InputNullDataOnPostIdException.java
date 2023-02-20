package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.Input_Null_Data_On_Post_Id;

public class InputNullDataOnPostIdException extends AbstractApiException {

	public InputNullDataOnPostIdException() {
		super(Input_Null_Data_On_Post_Id);
	}

}