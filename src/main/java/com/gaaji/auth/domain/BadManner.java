package com.gaaji.auth.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BadManner {

	bm1("반말을 사용해요"),
	bm2("불친절해요"),
	bm3("무조건 택배거래만 하려고 해요"),
	bm4("채팅 메시지를 보내도 답이 없어요"),
	bm5("차에서 내리지도 않고 창문만 열고 거래하려고 해요"),
	bm6("무리하게 가격을 깎아요"),
	;

	private String mannerContents;
	
	BadManner(String mannerContents) {
		this.mannerContents = mannerContents;
	}

	public String getMannerContents() {
		return mannerContents;
	}
}
