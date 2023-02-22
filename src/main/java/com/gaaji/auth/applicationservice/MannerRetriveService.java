package com.gaaji.auth.applicationservice;

import java.util.List;

import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.GoodManner;

public interface MannerRetriveService {

	List<GoodManner> retriveGoodMannerList();

	List<BadManner> retriveBadMannerList();

}
