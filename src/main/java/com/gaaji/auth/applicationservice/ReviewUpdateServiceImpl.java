package com.gaaji.auth.applicationservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaaji.auth.adaptor.S3Uploader;
import com.gaaji.auth.controller.dto.ReviewUpdateRequest;
import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.domain.Review;
import com.gaaji.auth.domain.ReviewId;
import com.gaaji.auth.exception.NoMatchSenderException;
import com.gaaji.auth.exception.NoSearchReviewException;
import com.gaaji.auth.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewUpdateServiceImpl implements ReviewUpdateService{

	private final ReviewRepository reviewRepository;
	private final S3Uploader s3Uploader;
	
	@Override
	public void updateReview(String authId, MultipartFile multipartFile, ReviewUpdateRequest dto) {
		Review review = this.reviewRepository.findById(ReviewId.of(dto.getReviewId())).orElseThrow(NoSearchReviewException::new);
		
		if(!review.getSenderId().getId().equals(authId)) {
			throw new NoMatchSenderException(); 
		}
		
		String pictureUrl;
		List<GoodManner> goodManners = getGoodManners(dto.getGoodManners());
		List<BadManner> badManners = getBaddManners(dto.getBadManners());
		if(dto.isPictureChanged()) {
			pictureUrl = uploadImage(multipartFile);
		} else {
			pictureUrl = review.getComment().getPictureUrl();
		}
			
		
		review.modify(pictureUrl, goodManners, badManners, dto.getContents());
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
