package com.gaaji.auth.exception;

import static com.gaaji.auth.exception.AuthErrorCode.Equals_Seller_And_Purchaser;

public class EqualsSellerAndPurchaserException extends AbstractApiException{

    public EqualsSellerAndPurchaserException() {
        super(Equals_Seller_And_Purchaser);
    }
}
