package com.gaaji.auth.domain;

public enum GoodManner {
	gm1("무료로 나눠주셨어요"),
	gm2("상품상태가 설명한 것과 같아요"),
	gm3("상품설명이 자세해요"),
	gm4("좋은 상품을 저렴하게 판매해요"),
	gm5("시간 약속을 잘 지켜요"),
	gm6("응답이 빨라요"),
	gm7("친절하고 매너가 좋아요"),
	;

	private String mannerContents;
	
	GoodManner(String mannerContents) {
		this.mannerContents = mannerContents;
	}

	public String getMannerContents() {
		return mannerContents;
	}
	
}
