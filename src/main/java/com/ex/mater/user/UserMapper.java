package com.ex.mater.user;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ex.mater.user.model.User;

@Mapper
public interface UserMapper {
	
	public User selectLogin(Map<String, Object> param);
	
	public Map<String, Object> selectIdCheck(Map<String, Object> param);
	public Map<String, Object> selectRprsCrnoCheck(Map<String, Object> param);
	
	public Integer insertRegister(User user);
	public Integer insertUserAttfl(User user);
	public Integer insertUserDetailAttfl(User user);

	public Integer deleteRegister(User user);
}
