package com.ex.mater.mypage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ex.mater.mater.FileCommand;


@Service("MypageService")
public class MypageServiceImpl implements MypageService {

	@Resource
    private MypageMapper myPageMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Mypage selectMypage(Map<String, Object> paramMap) throws Exception {

		return myPageMapper.selectMypage(paramMap);
	}

	@Override
	public Integer updateMypage(Map<String, Object> paramMap) throws Exception {
		return myPageMapper.updateMypage(paramMap);
	}

	@Override
	public Map<String, Object> selectMyPassword(Map<String, Object> paramMap) throws Exception {
		return myPageMapper.selectMyPassword(paramMap);
	}

	@Override
	public Integer deleteMypage(Map<String, Object> paramMap) throws Exception {
		return myPageMapper.deleteMypage(paramMap);
	}
	
}
