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
	public Integer updateNotice(Notice noti) throws Exception {
		// 기존 데이터가 비어있는 경우
		if( noti.getEtcFileName().length() < 1 || noti.getEtcFileName() == "" || noti.getEtcFileName() == null ) {
			if( noti.getAttflNm().length() > 1 || noti.getAttflNm() != "" || noti.getAttflNm() != null ) {
				// 처음 데이터가 들어오기떄문에 생성
				noticeMapper.insertNoticeAttfl(noti);
			}
		} else {
			if( noti.getAttflNm().length() < 1 || noti.getAttflNm() == "" || noti.getAttflNm() == null ) {
			}else {
				// 기존에 데이터가 있고 새로운 데이터도 있음
				if(!noti.getAttflNm().equals(noti.getEtcFileName())) {
					// 기존 파일과 새로운 파일이 다를경우 업데이트
					noticeMapper.updateNoticeAttfl(noti);
				}
			}
		}
		return noticeMapper.updateNotice(noti);
	}

	@Override
	public Integer insertNotice(Notice noti) throws Exception {
		if( noti.getAttflNm().length() < 1 || noti.getAttflNm() == "" || noti.getAttflNm() == null ) {
			noti.setAttflSeq("");
		} else {
			noticeMapper.insertNoticeAttfl(noti);
		}
		return noticeMapper.insertNotice(noti);
	}

	@Override
	public Integer deleteNotice(Map<String, Object> paramMap) throws Exception {
		return noticeMapper.deleteNotice(paramMap);
	}

	@Override
	public Notice selectNoticeDetail(Map<String, Object> paramMap) throws Exception {
		return noticeMapper.selectNoticeDetail(paramMap);
	}

	@Override
	public List<Notice> selectNoticeMainList(Map<String, Object> paramMap) throws Exception {
		return noticeMapper.selectNoticeMainList(paramMap);
	}

}
