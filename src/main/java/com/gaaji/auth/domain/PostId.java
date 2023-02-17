package com.gaaji.auth.domain;

import java.util.Objects;

import javax.persistence.Embeddable;

import org.springframework.util.StringUtils;

import com.gaaji.auth.exception.InputNullDataOnPostIdException;
import com.gaaji.auth.exception.InputNullDataOnReviewIdException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PostId {

	private String id;
	
	public static PostId of(String id) {
		if(!StringUtils.hasText(id)) throw new InputNullDataOnPostIdException();
		return new PostId(id);
	}

	public String getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostId postId = (PostId) o;
        return Objects.equals(id, postId.getId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
