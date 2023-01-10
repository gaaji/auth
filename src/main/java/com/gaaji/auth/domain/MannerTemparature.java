package com.gaaji.auth.domain;

import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable @NoArgsConstructor
public class MannerTemparature {

    private double temparature;

    private MannerTemparature(double temparature) {
        this.temparature = temparature;
    }
    public static MannerTemparature of(double temparature){
        return new MannerTemparature(temparature);
    }
}
