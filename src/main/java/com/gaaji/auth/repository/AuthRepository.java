package com.gaaji.auth.repository;

import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.domain.PlatformType;
import java.util.Optional;
import java.util.UUID;

public interface AuthRepository {
    Auth save(PlatformType type, String email);

    Optional<Auth> findById(String id);

    Optional<Auth> findByPlatformInfo(PlatformType type, String email);

    default String nextId(){
        return UUID.randomUUID().toString();
    }
}
