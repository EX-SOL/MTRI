package com.ex.mater.intro;

import java.util.List;
import java.util.Map;

public interface IntroService {
	
	public List<Map<String, Object>> selectIdFind(Map<String, Object> param) throws Exception;
	public Integer updateNewPw(Map<String, Object> param) throws Exception;
}
