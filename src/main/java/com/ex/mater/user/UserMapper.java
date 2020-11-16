package com.ex.mater.user;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ex.mater.user.model.User;

@Mapper
public interface UserMapper {
	
	public User selectLogin(Map<String, Object> param);
}
