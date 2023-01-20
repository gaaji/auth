package com.gaaji.auth.applicationservice;

import com.gaaji.auth.controller.dto.RetrieveResponse;
import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthRetrieveServiceImpl implements
        AuthRetrieveService {

    private final AuthRepository authRepository;

    @Override
    public RetrieveResponse retrieveAuth(String authId) {
        Auth auth = authRepository.findById(authId)
                .orElseThrow();
        return RetrieveResponse.of(authId, auth.getNickname(), auth.getMannerTemperature());
    }
}
