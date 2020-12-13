package com.ex.mater.intro;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("IntroService")
public class IntroServiceImpl implements IntroService {

	@Resource
    private IntroMapper introMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<Map<String, Object>> selectIdFind(Map<String, Object> param) throws Exception {
		return introMapper.selectIdFind(param);
	}

	@Override
	public Integer updateNewPw(Map<String, Object> param) throws Exception {
		return introMapper.updateNewPw(param);
	}
	
}
