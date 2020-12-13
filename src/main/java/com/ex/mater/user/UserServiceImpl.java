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

	@Override
	public Map<String, Object> selectIdCheck(Map<String, Object> param) throws Exception {
		return userMapper.selectIdCheck(param);
	}
	
	@Override
	public Map<String, Object> selectRprsCrnoCheck(Map<String, Object> param) throws Exception {
		return userMapper.selectRprsCrnoCheck(param);
	}

	@Override
	public Integer insertRegister(User user) throws Exception {
		int result = 0;
		// 1. 파일업로드
		// 2. 파일2 -> 장비테이블
		// 3. 
		if( user.getRprsCrnoAttflNm().length() < 1 || user.getRprsCrnoAttflNm() == "" || user.getRprsCrnoAttflNm() == null ) {
			user.setRprsCrnoAttflSeq("");
		} else {
			user.setAttflNm(user.getRprsCrnoAttflNm());
			user.setAttflPath(user.getRprsCrnoAttflPath());
			userMapper.insertUserAttfl(user);
			
			if( user.getContractAttflNm().length() < 1 || user.getContractAttflNm() == "" || user.getContractAttflNm() == null ) {
				user.setRprsCrnoAttflSeq("");
			} else {
				user.setAttflSeq(user.getRprsCrnoAttflSeq());
				user.setAttflNm(user.getContractAttflNm());
				user.setAttflPath(user.getContractAttflPath());
				userMapper.insertUserAttfl(user);
				
			}
		}
		//회원 등록
		result = userMapper.insertRegister(user);
		
		
//		if (result > 0 ) {
//			// 장비테이블
//			if( user.getMnpbRgsrSeqAttflNm().length() < 1 || user.getMnpbRgsrSeqAttflNm() == "" || user.getMnpbRgsrSeqAttflNm() == null ) {
//				user.setMnpbRgsrSeqAttflSeq("");
//			} else {
//				user.setAttflSeq(user.getMnpbRgsrSeqAttflSeq());
//				user.setAttflNm(user.getMnpbRgsrSeqAttflNm());
//				user.setAttflPath(user.getMnpbRgsrSeqAttflPath());
//				userMapper.insertUserDetailAttfl(user);
//			}
//		}
		
		return result;
	}

	@Override
	public Integer deleteRegister(User user) throws Exception {
		return userMapper.deleteRegister(user);
	}


	
}
