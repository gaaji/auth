package com.gaaji.auth.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.NoArgsConstructor;

@Embeddable @NoArgsConstructor
public class PlatformInfo {

    @Enumerated(EnumType.STRING)
    private PlatformType platformType;

    private String platformEmail;

    private PlatformInfo(PlatformType platformType, String platformEmail) {
        this.platformType = platformType;
        this.platformEmail = platformEmail;
    }

    public static PlatformInfo of(PlatformType platformType, String platformEmail){
        return new PlatformInfo(platformType,platformEmail);
    }
}
