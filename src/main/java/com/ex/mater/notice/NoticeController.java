package com.ex.mater.notice;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ex.mater.mater.FileCommand;
import com.ex.mater.mypage.Mypage;
import com.ex.mater.notice.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	NoticeService noticeService;

	// 공지사항 리스트
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


    // 공지사항 상세
    @GetMapping(value = "/selectNoticeDetail")
    public ModelAndView selectMaterDetail(String notcMtriSeq) throws Exception {
    	ModelAndView mav = new ModelAndView();
    	Notice rtnMap = new Notice();

    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("notcMtriSeq", notcMtriSeq);

    	System.out.println("paramMap:" + paramMap);

    	rtnMap = noticeService.selectNoticeDetail(paramMap);

    	System.out.println("rtnMap1212121212:" + rtnMap);
    	
    	mav.setViewName("notice/noticeDetail");
    	mav.addObject("notcMtriTitlNm", rtnMap.getNotcMtriTitlNm());
    	mav.addObject("notcMtriCtnt", rtnMap.getNotcMtriCtnt());
    	mav.addObject("bltnStrtDates", rtnMap.getBltnStrtDates());
    	mav.addObject("bltnEndDates", rtnMap.getBltnEndDates());
    	mav.addObject("attflNm", rtnMap.getAttflNm());
    	mav.addObject("attflPath", rtnMap.getAttflPath());
    	return mav;
    }

    
    // 파일 다운로드시 로직
    @GetMapping(value = "/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String attflPath, @RequestParam String attflNm) throws Exception {
    	logger.info("downloadAttfl() - attflPath: {}", attflPath);
    	logger.info("downloadAttfl() - attflNm: {}", attflNm);
    	
    	
    	System.out.println("attflPath : " + attflPath);
    	System.out.println("attflNm : " + attflNm);
    	
    	String attflType = attflNm.substring(attflNm.lastIndexOf(".")).trim();
    	String mimeType = "";
    	logger.info("downloadAttfl() - attflType: {}", attflType);
    	
    	if(attflType.equals(".hwp")) {
    		mimeType = "application/x-hwp";
    	} else if(attflType.equals("pdf")) {
    		mimeType = "application/pdf";
    	} else if(attflType.equals(".doc") || attflType.equals(".docx")) {
    		mimeType = "application/msword";
    	} else if(attflType.equals(".xls") || attflType.equals(".xlsx")) {
    		mimeType = "application/vnd.ms-excel";
    	} else if(attflType.equals(".ppt") || attflType.equals(".pptx")) {
    		mimeType = "application/vnd.ms-powerpoint";
    	} else if(attflType.equals(".zip")) {
    		mimeType = "application/zip";
    	} else if(attflType.equals(".jpeg") || attflType.equals(".jpg") || attflType.equals(".png") || attflType.equals(".PNG")) {
    		mimeType = " image/jpeg";
    	} else if(attflType.equals(".txt")) {
    		mimeType = "text/plain";
    	} else {
    		mimeType = " image/jpeg";
    	}
    	
    	String fileNm = new String(attflNm.getBytes("UTF-8"),"8859_1");
    	
        File file = new File(attflPath, attflNm);
        InputStreamResource isr = new InputStreamResource(FileUtils.openInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mimeType));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "attachment; filename="+fileNm+" ;");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Set-Cookie", "fileDownload=true; path=/");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.setContentLength(file.length());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(isr, headers, HttpStatus.OK);
        return response;
    }

}
