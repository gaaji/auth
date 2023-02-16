package com.gaaji.auth.domain;

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
public class PostId  {

	private String id;
	
	public static PostId of(String id) {
		if(!StringUtils.hasText(id)) throw new InputNullDataOnPostIdException();
		return new PostId(id);
	}
}
