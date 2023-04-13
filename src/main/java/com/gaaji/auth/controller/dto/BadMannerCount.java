package com.gaaji.auth.controller.dto;

import com.gaaji.auth.domain.AuthId;
import com.gaaji.auth.domain.BadManner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BadMannerCount {
	private BadManner badManner;
	private int count;
	
	public static BadMannerCount of(BadManner badManner, int counter) {
		return new BadMannerCount(badManner, counter);
	}
}
