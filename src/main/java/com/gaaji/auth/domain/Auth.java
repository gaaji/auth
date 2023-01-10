package com.gaaji.auth.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor
public class Auth {


    @EmbeddedId @Column(name = "auth_id")
    private AuthId id;

    private String nickname;

    @Embedded
    private PlatformInfo platformInfo;

    @Embedded
    private MannerTemparature mannerTemparature;


    @Builder
    private Auth(AuthId id, String nickname, PlatformInfo platformInfo,
            MannerTemparature mannerTemparature) {
        this.id = id;
        this.nickname = nickname;
        this.platformInfo = platformInfo;
        this.mannerTemparature = mannerTemparature;
    }

    public static Auth signUp(String id, PlatformType type, String email){
        return Auth.builder()
                .id(AuthId.of(id))
                .nickname("익명")
                .platformInfo(PlatformInfo.of(type,email))
                .mannerTemparature(MannerTemparature.of(36.5))
                .build();
    }
    public void registerNickname(String nickname){
        this.nickname = nickname;
    }
    public String getAuthIdForToken(){
        return id.getId();
    }
}
