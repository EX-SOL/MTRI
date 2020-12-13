package com.ex.mater.notice;

import java.util.List;
import java.util.Map;

import com.ex.mater.mater.FileCommand;


public interface NoticeService {
	// 조회
	public List<Notice> selectNotice(Map<String, Object> paramMap) throws Exception;

	// 상세조회
	public Notice selectNoticeDetail(Map<String, Object> paramMap) throws Exception;
	
	// 수정
	public Integer updateNotice(Notice noti) throws Exception;

	// 입력
	public Integer insertNotice(Notice noti) throws Exception;
	
	// 삭제
	public Integer deleteNotice(Map<String, Object> paramMap) throws Exception;

	public List<Notice> selectNoticeMainList(Map<String, Object> paramMap) throws Exception;
}
