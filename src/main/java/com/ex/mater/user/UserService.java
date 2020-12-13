package com.ex.mater.user;

import java.util.Map;

import com.ex.mater.user.model.User;

public interface UserService {
	
	public User selectLogin(Map<String, Object> param) throws Exception;
	
	public Map<String, Object> selectIdCheck(Map<String, Object> param) throws Exception;
	public Map<String, Object> selectRprsCrnoCheck(Map<String, Object> param) throws Exception;

	public Integer insertRegister(User user) throws Exception;
	public Integer deleteRegister(User user) throws Exception;
}
