package com.gaaji.auth.controller.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CommentInfo {

	private String senderId;
	private String nickname;
    private String profilePictureUrl;
	private String town;
    private String contents;
    private String pictureUrl;
    private boolean ispurchaser;
	private LocalDateTime createdAt;
	
	public static CommentInfo of(String senderId, String nickname, String profilePictureUrl, String town, String contents, String pictureUrl, boolean ispurchaser, LocalDateTime createdAt) {
		return new CommentInfo(senderId, nickname, profilePictureUrl, town, contents, pictureUrl, ispurchaser, createdAt);
	}
}
