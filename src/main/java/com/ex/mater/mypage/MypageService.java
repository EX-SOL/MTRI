package com.ex.mater.mypage;

import java.util.List;
import java.util.Map;

public interface MypageService {
	
	public Mypage selectMypage(Map<String, Object> paramMap) throws Exception;
	
	public Integer updateMypage(Map<String, Object> paramMap) throws Exception;
	
	public Map<String,Object> selectMyPassword(Map<String,Object> paramMap) throws Exception;
	
	public Integer deleteMypage(Map<String,Object> paramMap) throws Exception;

}
