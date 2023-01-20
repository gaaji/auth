package com.gaaji.auth.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Access(AccessType.FIELD)
@Embeddable @NoArgsConstructor
public class MannerTemperature {

    private double temperature;

    private MannerTemperature(double temperature) {
        this.temperature = temperature;
    }
    public static MannerTemperature of(double temperature){
        return new MannerTemperature(temperature);
    }

    public double getTemperature() {
        return temperature;
    }
}
