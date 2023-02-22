package com.gaaji.auth.applicationservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaaji.auth.domain.BadManner;
import com.gaaji.auth.domain.GoodManner;
import com.gaaji.auth.repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
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
