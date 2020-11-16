package com.ex.mater.mypage;

import java.util.List;
import java.util.Map;

import com.ex.mater.mater.FileCommand;

public interface MypageService {
	
	public List<Mypage> selectMypage(Map<String, Object> paramMap) throws Exception;
	
	public Integer updateMypage(Map<String, Object> paramMap) throws Exception;

}
