package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.Input_Null_Data_On_Review_Id;

public class InputNullDataOnReviewIdException extends AbstractApiException {

	public InputNullDataOnReviewIdException() {
		super(Input_Null_Data_On_Review_Id);
	}

}
