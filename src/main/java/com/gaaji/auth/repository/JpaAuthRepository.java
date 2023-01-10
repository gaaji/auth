package com.gaaji.auth.repository;

import com.gaaji.auth.domain.Auth;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.PlatformType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAuthRepository extends JpaRepository<Auth, AuthId> {

    Optional<Auth> findAuthByPlatformInfo_PlatformEmailAndPlatformInfo_PlatformType(PlatformType type, String platformEmail);

    Optional<Auth> findAuthByPlatformInfo(PlatformType type, String platformEmail);
}
