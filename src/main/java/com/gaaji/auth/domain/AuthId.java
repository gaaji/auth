package com.gaaji.auth.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable @NoArgsConstructor @Getter
public class AuthId implements Serializable {

    @Id
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthId authId = (AuthId) o;
        return Objects.equals(id, authId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private AuthId(String id) {
        this.id = id;
    }

    public static AuthId of(String id) {
        return new AuthId(id);
    }
}
