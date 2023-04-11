package com.gaaji.auth.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MannerRetrieveResponse {
	private List<GoodMannerCount> goodMannerCount;
	private List<BadMannerCount> badMannerCount;
	

	public static MannerRetrieveResponse of(List<GoodMannerCount> goodMannerCount,
			List<BadMannerCount> badMannerCount) {
		return new MannerRetrieveResponse(goodMannerCount, badMannerCount);
	}
}
