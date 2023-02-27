package com.gaaji.auth.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.GoodManner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GoodMannerCount {
	private GoodManner goodManners;
	private int count;
	
	public static GoodMannerCount of(GoodManner goodManners, int count) {
		return new GoodMannerCount(goodManners, count);
	}

}
