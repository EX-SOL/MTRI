package com.ex.mater.mypage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("MypageService")
public class MypageServiceImpl implements MypageService {

	@Resource
    private MypageMapper materrMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());


	
}
