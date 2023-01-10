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
}
