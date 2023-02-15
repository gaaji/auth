package com.gaaji.auth.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {

	@EmbeddedId
	private ReviewId reviewId;
	
	@Embedded
	private AuthId senderID;
	
	@Embedded
	private AuthId receiverID;
	
	
	@Enumerated(EnumType.STRING)
    private List<GoodManner> goodManners;
	
	@Enumerated(EnumType.STRING)
    private List<BadManner> badManners;
	
	@Embedded
	private Comment comment;
	
	public static Review of(ReviewId reviewId, AuthId senderID, AuthId receiverID, List<GoodManner> goodManners, List<BadManner> badManners, Comment comment) {
		return new Review(reviewId, senderID, receiverID, goodManners, badManners, comment);
		
	}

	


}
