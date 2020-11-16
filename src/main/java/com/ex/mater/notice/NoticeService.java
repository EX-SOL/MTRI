package com.ex.mater.notice;

import java.util.List;
import java.util.Map;

import com.ex.mater.mypage.Mypage;

public interface NoticeService {
	// 조회
	public List<Mypage> selectMypage(Map<String, Object> paramMap) throws Exception;
	
	// 수정
	public Integer updateNotice(Map<String, Object> paramMap) throws Exception;

	// 입력
	public Integer insertNotice(Map<String, Object> paramMap) throws Exception;
	
	// 삭제
	public Integer deleteNotice(Map<String, Object> paramMap) throws Exception;
}