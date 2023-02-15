package com.gaaji.auth.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

import com.gaaji.auth.exception.InputNullDataOnReviewIdException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReviewId implements Serializable {

	private String id;
	
	public static ReviewId of(String id) {
		if(!StringUtils.hasText(id)) throw new InputNullDataOnReviewIdException();
		return new ReviewId(id);
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReviewId that = (ReviewId) o;
        return Objects.equals(id, that.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
