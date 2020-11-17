package com.ex.mater.notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ex.mater.notice.Notice;

@Mapper
public interface NoticeMapper {

	public List<Notice> selectNotice(Map<String, Object> paramMap);

}
