package com.gaaji.auth.domain;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class Comment {

	private String pictureUrl;
	private String contents;
	private String town;
	private boolean ispurchaser;
	private LocalDateTime createdAt;

	public Comment(String pictureUrl, String contents, String town, boolean ispurchaser) {
		this.pictureUrl = pictureUrl;
		this.contents = contents;
		this.ispurchaser = ispurchaser;
		this.town = town;
		this.createdAt = LocalDateTime.now();
	}

	public static Comment of(String pictureUrl, String contents, String town, boolean ispurchaser) {
		return new Comment(pictureUrl, contents, town, ispurchaser);

	}

}
