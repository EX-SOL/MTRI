package com.ex.mater.mypage;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageMapper {
	
	public Mypage selectMypage(Map<String, Object> paramMap);

	public Integer updateMypage(Map<String, Object> paramMap);
	
	public Map<String, Object> selectMyPassword(Map<String, Object> paramMap);
	
	public Integer deleteMypage(Map<String,Object> paramMap);
}
