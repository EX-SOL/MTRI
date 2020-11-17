package com.ex.mater.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.mater.mypage.Mypage;
import com.ex.mater.notice.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	NoticeService noticeService;

    @PostMapping(value = "/selectNotice")
    public List<Notice> selectNotice(@RequestBody Map<String, Object> paramMap) throws Exception {
    	List<Notice> rtnMap = new ArrayList<Notice>();

    	System.out.println("paramMap : " + paramMap);
    	try {
    		rtnMap = noticeService.selectNotice(paramMap);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
		return rtnMap;
    }

}
