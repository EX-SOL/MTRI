package com.ex.mater.notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ex.mater.notice.Notice;

@Mapper
public interface NoticeMapper {

	// 조회
	public List<Notice> selectNotice(Map<String, Object> paramMap);

	// 상세 조회
	public Notice selectNoticeDetail(Map<String, Object> paramMap);

	public Integer insertNotice(Notice noti);
	public Integer insertNoticeAttfl(Notice noti);
	
	public Integer updateNoticeAttfl(Notice noti);
	public Integer updateNotice(Notice noti);
	
	public Integer deleteNotice(Map<String, Object> paramMap);
	
	public List<Notice> selectNoticeMainList(Map<String, Object> paramMap);
}
