package com.ex.mater.user;

import java.util.Map;

import com.ex.mater.user.model.User;

public interface UserService {
	
	public User selectLogin(Map<String, Object> param) throws Exception;
}
