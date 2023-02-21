package com.gaaji.auth.domain;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.gaaji.auth.controller.dto.ReviewUpdateRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Review {

	@EmbeddedId
	private ReviewId reviewId;
	
	@Embedded 
    @AttributeOverride(name = "id", column = @Column(name = "post_Id"))
	private PostId postId;
	
	@Embedded 
    @AttributeOverride(name = "id", column = @Column(name = "sender_Id"))
	private AuthId senderId;
	
	@Embedded
    @AttributeOverride(name = "id", column = @Column(name = "receiver_Id"))
	private AuthId receiverId;
	
	@ElementCollection
    @CollectionTable(name = "goodManner", 
        joinColumns = @JoinColumn(name = "reviewId"))
    private List<GoodManner> goodManners;
	
	@ElementCollection
    @CollectionTable(name = "badManner", 
        joinColumns = @JoinColumn(name = "reviewId"))
    private List<BadManner> badManners;
	
	@Embedded
	private Comment comment;
	
	public static Review of(ReviewId reviewId, PostId postId, AuthId senderId, AuthId receiverId, List<GoodManner> goodManners, List<BadManner> badManners, Comment comment) {
		return new Review(reviewId, postId, senderId, receiverId, goodManners, badManners, comment);
	}

	public void modify(String pictureUrl, List<GoodManner> goodManners, List<BadManner> badManners, String contents) {
		this.goodManners = goodManners;
		this.badManners = badManners;
		this.comment = Comment.of(pictureUrl, contents, this.comment.isIspurchaser());
	}

	


}
