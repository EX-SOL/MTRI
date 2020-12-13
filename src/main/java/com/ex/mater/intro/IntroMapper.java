package com.ex.mater.intro;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IntroMapper {
	
	public List<Map<String, Object>> selectIdFind(Map<String, Object> param);
	
	public Integer updateNewPw(Map<String, Object> param);
}
