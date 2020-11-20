package com.ex.mater.notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService{
	@Resource
	private NoticeMapper noticeMapper; 
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<Notice> selectNotice(Map<String, Object> paramMap) throws Exception {

		return noticeMapper.selectNotice(paramMap);
	}

	@Override
	public Integer updateNotice(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insertNotice(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteNotice(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice selectNoticeDetail(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.selectNoticeDetail(paramMap);
	}

}
