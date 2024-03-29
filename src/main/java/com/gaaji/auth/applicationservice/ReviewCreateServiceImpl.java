package com.gaaji.auth.applicationservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaaji.auth.adaptor.S3Uploader;
import com.gaaji.auth.controller.dto.ReviewCreateRequest;
import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.Comment;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.domain.PostId;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;
import com.gaaji.auth.exception.EqualsSellerAndPurchaserException;
import com.gaaji.auth.exception.NoMatchIdException;
import com.gaaji.auth.exception.NoReviewException;
import com.gaaji.auth.exception.NoTownException;
import com.gaaji.auth.exception.NonexistentTargetException;
import com.gaaji.auth.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewCreateServiceImpl implements ReviewCreateService {

	private final ReviewRepository reviewRepository;
	private final S3Uploader s3Uploader;

	@Override
	public void createReview(String authId, MultipartFile multipartFile,ReviewCreateRequest dto) {
		saveEntity(createReviewEntity(dto, multipartFile, authId));
	}

	private void saveEntity(Review review) {
		this.reviewRepository.save(review);
	}

	private Review createReviewEntity(ReviewCreateRequest dto, MultipartFile multipartFile, String authId) {
		if(dto.getTown() == null) {
			throw new NoTownException();
		}
		
		if((dto.getPurchaserId() == null) || dto.getSellerId() == null) {
			throw new NonexistentTargetException();
		} 

		if((dto.getContents() == null) && (dto.getGoodManners().size() == 0) && (dto.getBadManners().size() == 0)) {
			throw new NoReviewException();
		}
		
		if(dto.getSellerId().equals(dto.getPurchaserId())) {
			throw new EqualsSellerAndPurchaserException();
		}
		
		String pictureUrl = uploadImage(multipartFile);
		List<GoodManner> goodManners = getGoodManners(dto.getGoodManners());
		List<BadManner> badManners = getBaddManners(dto.getBadManners());
		if (dto.getPurchaserId().equals(authId)) {
			return Review.of(ReviewId.of(this.reviewRepository.nextId()), PostId.of(dto.getPostId()), AuthId.of(authId),
					AuthId.of(dto.getSellerId()), goodManners, badManners,
					Comment.of(pictureUrl, dto.getContents(), dto.getTown(), true));
			
		} else if (dto.getSellerId().equals(authId)) {
			return Review.of(ReviewId.of(this.reviewRepository.nextId()), PostId.of(dto.getPostId()), AuthId.of(authId),
					AuthId.of(dto.getPurchaserId()), goodManners, badManners,
					Comment.of(pictureUrl, dto.getContents(), dto.getTown(), false));
		} else {
			throw new NoMatchIdException();
		}
	}

	private List<BadManner> getBaddManners(List<String> badManners) {
		if (badManners.size() == 0) {
			return null;
		}

		List<BadManner> badMannerList = new ArrayList<BadManner>();

		for (String badManner : badManners) {
			badMannerList.add(BadManner.valueOf(badManner));
		}
		return badMannerList;
	}

	private List<GoodManner> getGoodManners(List<String> goodManners) {
		if (goodManners.size() == 0) {
			return null;
		}

		List<GoodManner> goodMannerList = new ArrayList<GoodManner>();

		for (String goodManner : goodManners) {
			goodMannerList.add(GoodManner.valueOf(goodManner));
		}
		return goodMannerList;
	}

	private String uploadImage(MultipartFile multipartFile) {
		if(multipartFile == null) {
			return null;
		}
		return s3Uploader.upload(multipartFile);
	}

}
