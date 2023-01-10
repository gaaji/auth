package com.gaaji.auth.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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

    public void registerNickname(){

    }

    public static Auth signUp(){
        return null;
    }

}
