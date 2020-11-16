package com.ex.mater.user;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ex.mater.user.model.User;


@Service("UserService")
public class UserServiceImpl implements UserService {

	@Resource
    private UserMapper userMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public User selectLogin(Map<String, Object> param) throws Exception {
		return userMapper.selectLogin(param);
	}


	
}
