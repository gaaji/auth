package com.gaaji.auth.repository;

import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.PlatformType;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AuthRepositoryImpl implements AuthRepository{

    private final JpaAuthRepository jpaAuthRepository;

    @Override
    public Auth save(PlatformType type, String email) {
        return jpaAuthRepository.save(Auth.signUp(this.nextId(), type, email));
    }
    @Override
    public Optional<Auth> findById(String id) {
        return jpaAuthRepository.findById(AuthId.of(id));
    }

    @Override
    public Optional<Auth> findByPlatformInfo(PlatformType type, String email) {
        return jpaAuthRepository.findAuthByPlatformInfo_PlatformEmailAndPlatformInfo_PlatformType( email,type);
    }


}
