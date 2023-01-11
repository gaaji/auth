package com.gaaji.auth.applicationservice;

import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class NicknameRegisterServiceImpl implements NicknameRegisterService{

    private final AuthRepository authRepository;


    public void registerNickname(String authId, String nickname){
        Auth auth = authRepository
                .findById(authId)
                .orElseThrow(); // TODO Exception 추가

        auth.registerNickname(nickname);
    }
}
