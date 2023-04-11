package com.gaaji.auth.applicationservice;

import com.gaaji.auth.controller.dto.RetrieveResponse;

public interface AuthRetrieveService {

    RetrieveResponse retrieveAuth(String authId);
}
