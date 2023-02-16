package com.gaaji.auth.domain;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Comment {

	private String pictureUrl;
	private String contents;
	private boolean ispurchaser;
	private LocalDateTime createdAt;

	public Comment(String pictureUrl, String contents, boolean ispurchaser) {
		this.pictureUrl = pictureUrl;
		this.contents = contents;
		this.ispurchaser = ispurchaser;
		this.createdAt = LocalDateTime.now();
	}

	public static Comment of(String pictureUrl, String contents, boolean ispurchaser) {
		return new Comment(pictureUrl, contents, ispurchaser);

	}

}