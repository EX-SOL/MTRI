package com.ex.mater.notice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/notice")
public class NoticeController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	NoticeService noticeService;

    @Value("${app.global.file-path}")
    protected String filePath;
    
	// 공지사항 리스트
    @PostMapping(value = "/selectNotice")
    public List<Notice> selectNotice(@RequestBody Map<String, Object> paramMap) throws Exception {
    	List<Notice> rtnMap = new ArrayList<Notice>();

    	System.out.println("paramMap : " + paramMap);
    	try {
    		rtnMap = noticeService.selectNotice(paramMap);
    		System.out.println("rtnMap : " + rtnMap);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
		return rtnMap;
    }

    // 공지사항 리스트
    @PostMapping(value = "/insertNotice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> insertNotice(Notice noti) throws Exception {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	//----------------------파일업로드----------------------------------------------
    	SimpleDateFormat today = new SimpleDateFormat("yyyyMMddHHmmss");
    	SimpleDateFormat year = new SimpleDateFormat("yyyy");
    	SimpleDateFormat month = new SimpleDateFormat("MM");
    	SimpleDateFormat day = new SimpleDateFormat("dd");
    	// 파일업로드 경로 :: 기본경로/년/월/일
		String materFilePath  = filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis())+"/"+day.format(System.currentTimeMillis());
		
		//해당경로 없을 시 경로 생성 
		File dir1 = new File(filePath+"/"+year.format(System.currentTimeMillis()));
		File dir2 = new File(filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis()));
		File dir3 = new File(filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis())+"/"+day.format(System.currentTimeMillis()));
		if(!dir1.exists()) { 
			dir1.mkdirs();
			dir1.setExecutable(true, false);
			dir1.setReadable(true, false);
			dir1.setWritable(true, false);
		}
		if(!dir2.exists()) { 
			dir2.mkdirs();
			dir2.setExecutable(true, false);
			dir2.setReadable(true, false);
			dir2.setWritable(true, false);
		}
		if(!dir3.exists()) { 
			dir3.mkdirs();
			dir3.setExecutable(true, false);
			dir3.setReadable(true, false);
			dir3.setWritable(true, false);
		}
		
    	String ext = noti.getFlUpFileData().getOriginalFilename().substring(noti.getFlUpFileData().getOriginalFilename().lastIndexOf(".") + 1);
    	
    	if(noti.getFlUpFileData() == null || noti.getFlUpFileData().getOriginalFilename().equals("") ) {
    		noti.setAttflNm("");
			noti.setAttflPath("");
			noti.setFsttmRgsrId(noti.getMtriCustNo());
			noti.setLsttmModfrId(noti.getMtriCustNo());
			
	    	int result = noticeService.insertNotice(noti);
			
			try {
				if ( result > 0 ) {
					paramMap.put("SUCCESS", true);
				}else {
					paramMap.put("SUCCESS", false);
				}
				
			} catch (Exception e) {
				throw e;
			}
    	} else {
    		if ( imageExt(ext.trim())) {
    			File file = new File(materFilePath+"/"+noti.getFlUpFileData().getOriginalFilename());
    			
    			String newFileName = noti.getMtriCustNo() + "_" +today.format(System.currentTimeMillis()) + "(1)";
    			try {
    	            // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
    	            if (!file.exists()) {
    	            	noti.getFlUpFileData().transferTo(file);
    	            	
    	            	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
    	        		boolean isRename = file.renameTo(newFile);
    	        		if(isRename) {
    	        			paramMap.put("SUCCESS", true);
    	        			noti.setAttflNm(newFileName+"."+ext);
    	    				noti.setAttflPath(materFilePath);
    	        		} else {
    	        			paramMap.put("SUCCESS", false);
    	        		}
    	            }
    	        } catch (IOException e) {
    	        	paramMap.put("SUCCESS", false);
    	        	e.printStackTrace();
    	        } catch (IllegalStateException ie) {
    	        	paramMap.put("SUCCESS", false);
    	        	ie.printStackTrace();
    	        }
    			
    			//----------------------파일업로드----------------------------------------------
    			
    			if(paramMap.get("SUCCESS").equals(true)) {
    				noti.setFsttmRgsrId(noti.getMtriCustNo());
    				noti.setLsttmModfrId(noti.getMtriCustNo());
    				
    		    	int result = noticeService.insertNotice(noti);
    				
    				try {
    					if ( result > 0 ) {
    						paramMap.put("SUCCESS", true);
    					}else {
    						paramMap.put("SUCCESS", false);
    					}
    					
    				} catch (Exception e) {
    					throw e;
    				}
    			}
    			
    		} else {
    			paramMap.put("SUCCESS", false);
        		paramMap.put("resultMsg", "이미지 또는 pdf파일만 업로드가능합니다.");
    		}
    	}
		
    	return paramMap;
    }


    // 공지사항 상세
    @GetMapping(value = "/selectNoticeDetail")
    public ModelAndView selectNoticeDetail(String notcMtriSeq) throws Exception {
    	ModelAndView mav = new ModelAndView();
    	Notice rtnMap = new Notice();
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	try {
    		paramMap.put("notcMtriSeq", notcMtriSeq);
    		rtnMap = noticeService.selectNoticeDetail(paramMap);
    		
    		mav.addObject("notcMtriTitlNm", rtnMap.getNotcMtriTitlNm());
        	mav.addObject("notcMtriCtnt", rtnMap.getNotcMtriCtnt());
        	mav.addObject("bltnStrtDates", rtnMap.getBltnStrtDates());
        	mav.addObject("bltnEndDates", rtnMap.getBltnEndDates());
        	mav.addObject("attflNm", rtnMap.getAttflNm());
        	mav.addObject("attflPath", rtnMap.getAttflPath());
        	mav.addObject("attflSeq", rtnMap.getAttflSeq());
        	mav.addObject("fsttmRgsrId", rtnMap.getFsttmRgsrId());
        	mav.addObject("notcMtriSeq", rtnMap.getNotcMtriSeq());
        	
        	
    		mav.setViewName("notice/noticeDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return mav;
    }
    
    @PostMapping(value = "/updateNotice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> updateNotice(Notice noti) throws Exception {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	//----------------------파일업로드----------------------------------------------
    	SimpleDateFormat today = new SimpleDateFormat("yyyyMMddHHmmss");
    	SimpleDateFormat year = new SimpleDateFormat("yyyy");
    	SimpleDateFormat month = new SimpleDateFormat("MM");
    	SimpleDateFormat day = new SimpleDateFormat("dd");
    	// 파일업로드 경로 :: 기본경로/년/월/일
		String materFilePath  = filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis())+"/"+day.format(System.currentTimeMillis());
		
		//해당경로 없을 시 경로 생성 
		File dir1 = new File(filePath+"/"+year.format(System.currentTimeMillis()));
		File dir2 = new File(filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis()));
		File dir3 = new File(filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis())+"/"+day.format(System.currentTimeMillis()));
		if(!dir1.exists()) { 
			dir1.mkdirs();
			dir1.setExecutable(true, false);
			dir1.setReadable(true, false);
			dir1.setWritable(true, false);
		}
		if(!dir2.exists()) { 
			dir2.mkdirs();
			dir2.setExecutable(true, false);
			dir2.setReadable(true, false);
			dir2.setWritable(true, false);
		}
		if(!dir3.exists()) { 
			dir3.mkdirs();
			dir3.setExecutable(true, false);
			dir3.setReadable(true, false);
			dir3.setWritable(true, false);
		}
		
    	String ext = noti.getFlUpFileData().getOriginalFilename().substring(noti.getFlUpFileData().getOriginalFilename().lastIndexOf(".") + 1);
		
    	if(noti.getFlUpFileData() == null || noti.getFlUpFileData().getOriginalFilename().equals("") ) {
    		noti.setAttflNm("");
			noti.setAttflPath("");
			noti.setFsttmRgsrId(noti.getMtriCustNo());
			noti.setLsttmModfrId(noti.getMtriCustNo());
			
	    	int result = noticeService.updateNotice(noti);
			
			try {
				if ( result > 0 ) {
					paramMap.put("SUCCESS", true);
				}else {
					paramMap.put("SUCCESS", false);
				}
				
			} catch (Exception e) {
				throw e;
			}
    	} else {
        	if ( imageExt(ext.trim())) {
        		
    			File file = new File(materFilePath+"/"+noti.getFlUpFileData().getOriginalFilename());
    			
    			String newFileName = noti.getMtriCustNo() + "_" +today.format(System.currentTimeMillis()) + "(1)";
    			try {
    	            // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
    	            if (!file.exists()) {
    	            	noti.getFlUpFileData().transferTo(file);
    	            	
    	            	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
    	        		boolean isRename = file.renameTo(newFile);
    	        		if(isRename) {
    	        			paramMap.put("SUCCESS", true);
    	        			noti.setAttflNm(newFileName+"."+ext);
    	    				noti.setAttflPath(materFilePath);
    	        		} else {
    	        			paramMap.put("SUCCESS", false);
    	        		}
    	            }
    	        } catch (IOException e) {
    	        	paramMap.put("SUCCESS", false);
    	        	e.printStackTrace();
    	        } catch (IllegalStateException ie) {
    	        	paramMap.put("SUCCESS", false);
    	        	ie.printStackTrace();
    	        }
    			
    			//----------------------파일업로드----------------------------------------------
    		    
    			if(paramMap.get("SUCCESS").equals(true)) {
    				noti.setFsttmRgsrId(noti.getMtriCustNo());
    				noti.setLsttmModfrId(noti.getMtriCustNo());
    				
    		    	int result = noticeService.updateNotice(noti);
    				
    				try {
    					if ( result > 0 ) {
    						paramMap.put("SUCCESS", true);
    					}else {
    						paramMap.put("SUCCESS", false);
    					}
    					
    				} catch (Exception e) {
    					throw e;
    				}
    			}
    			
    		} else {
    			paramMap.put("SUCCESS", false);
        		paramMap.put("resultMsg", "이미지 또는 pdf파일만 업로드가능합니다.");
    		}

    	}
		
    	return paramMap;
    }

    
    @PostMapping(value="/deleteNotice")
    public Map<String, Object> deleteNotice (@RequestBody Map<String,Object> dataParam) throws Exception{
    	int result = 0;
    	try {
    		result = noticeService.deleteNotice(dataParam);
    		if(result > 0) {
    			dataParam.put("SUCCESS", true);
    		}else {
    			dataParam.put("SUCCESS", false);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return dataParam;
    }
    
    @PostMapping(value="/selectNoticeMainList")
    public List<Notice> selectNoticeMainList (@RequestBody Map<String,Object> dataParam) throws Exception{
    	List<Notice> list = new ArrayList<Notice>();
    	try {
    		list = noticeService.selectNoticeMainList(dataParam);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
    // 확장자 확인
 	public Boolean imageExt(String ext) {
 		boolean	result = false;
 		
 		if (ext.equals("jpg")) {
 			result = true;
 		} else if (ext.equals("JPG")) {
 			result = true;
 		} else if (ext.equals("png")) {
 			result = true;
 		} else if (ext.equals("PNG")) {
 			result = true;
 		} else if (ext.equals("jpeg")) {
 			result = true;
 		} else if (ext.equals("JPEG")) {
 			result = true;
 		} else if (ext.equals("pdf")) {
 			result = true;
 		} else if (ext.equals("PDF")) {
 			result = true;
 		} else {
 			result = false;
 		}
 		
 		return result;
 	}
}
