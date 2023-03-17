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
    private String profilePictureUrl;

    @Embedded
    private PlatformInfo platformInfo;

    @Embedded
    private MannerTemperature mannerTemperature;


    @Builder
    private Auth(AuthId id, String nickname, PlatformInfo platformInfo,
            MannerTemperature mannerTemperature) {
        this.id = id;
        this.nickname = nickname;
        this.platformInfo = platformInfo;
        this.mannerTemperature = mannerTemperature;
    }

    public static Auth signUp(String id, PlatformType type, String email){
        return Auth.builder()
                .id(AuthId.of(id))
                .nickname("익명")
                .platformInfo(PlatformInfo.of(type,email))
                .mannerTemperature(MannerTemperature.of(36.5))
                .build();
    }
    public void registerNickname(String nickname){
        this.nickname = nickname;
    }
    public void registerProfilePicture(String profilePictureUrl){
        this.profilePictureUrl = profilePictureUrl;
    }
    public String getAuthIdForToken(){
        return id.getId();
    }
    public String getNickname(){
        return nickname;
    }
    public double getMannerTemperature(){
        return mannerTemperature.getTemperature();
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }
}
