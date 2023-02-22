package com.gaaji.auth.applicationservice;

import java.util.Arrays;
import java.util.List;

import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.GoodManner;

public class MannerRetriveServiceImpl implements MannerRetriveService {

	@Override
	public List<GoodManner> retriveGoodMannerList() {
		return Arrays.asList(GoodManner.values());
	}

	@Override
	public List<BadManner> retriveBadMannerList() {
		return Arrays.asList(BadManner.values());

	}

}
