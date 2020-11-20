package com.ex.mater.mater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
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

import com.ex.mater.user.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/mater")
public class MaterController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	MaterService materService;
	
    @Value("${app.global.file-path}")
    protected String filePath;
	
    // 자재대금 등록 및 파일 업로드
    @PostMapping(value = "/insertMaterList", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> insertMaterList(FileCommand fileData) throws Exception {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	SimpleDateFormat year = new SimpleDateFormat("yyyy");
    	SimpleDateFormat month = new SimpleDateFormat("MM");
    	SimpleDateFormat day = new SimpleDateFormat("dd");
    	// 파일업로드 경로 :: 기본경로/년/월/일
		String materFilePath  = filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis())+"/"+day.format(System.currentTimeMillis());
		
		if(fileData.getFlUpFileData() != null && !fileData.getFlUpFileData().getOriginalFilename().equals("") ) {
			fileData.setAttflNm(fileData.getFlUpFileData().getOriginalFilename());
			fileData.setAttflPath(materFilePath);
		} else {
			fileData.setAttflNm("");
			fileData.setAttflPath("");
		}
		
    	int result = materService.insertMaterList(fileData);
		
		try {
			if ( result > 0 ) {
				paramMap.put("SUCCESS", true);
			}else {
				paramMap.put("SUCCESS", false);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		//해당경로 없을 시 경로 생성 
		File dir1 = new File(filePath+"/"+year.format(System.currentTimeMillis()));
		File dir2 = new File(filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis()));
		File dir3 = new File(filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis())+"/"+day.format(System.currentTimeMillis()));
		if(!dir1.exists()) { dir1.mkdirs(); }
		if(!dir2.exists()) { dir2.mkdirs(); }
		if(!dir3.exists()) { dir3.mkdirs(); }
		
		File file = Paths.get(materFilePath, fileData.getFlUpFileData().getOriginalFilename()).toFile();
	    
		try {
            // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
            if (!file.exists()) {
            	fileData.getFlUpFileData().transferTo(file);
            } else {
            	
            }
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (IllegalStateException ie) {
        	ie.printStackTrace();
        }
		
		return paramMap;
    }
    
    // 자재대금 목록조회
    @PostMapping(value = "/selectMaterList")
    public List<FileCommand> selectMaterList(@RequestBody Map<String, Object> dataMap) throws Exception{
    	List<FileCommand> list = new ArrayList<FileCommand>();
    	
    	try {
    		list = materService.selectMaterList(dataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return list;
    }
	
    // 자재&장비 메인 목록 3건 조회
    @PostMapping(value = "/selectMainList")
    public List<FileCommand> selectMainList(@RequestBody Map<String, Object> dataMap) throws Exception {
		List<FileCommand> list = new ArrayList<>();
		try {
			list = materService.selectMainList(dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

    // 자재 상세
    @GetMapping(value = "/selectMaterDetail")
    public ModelAndView selectMaterDetail(String mtriCustNo, String mnpbAskYYMM, String mnpbAskSqno, String mnpbClssCd) throws Exception {
    	ModelAndView mav = new ModelAndView();
    	FileCommand fileCommand = new FileCommand();
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("mtriCustNo", mtriCustNo);
    	paramMap.put("mnpbAskYYMM", mnpbAskYYMM);
    	paramMap.put("mnpbAskSqno", mnpbAskSqno);
    	paramMap.put("mnpbClssCd", mnpbClssCd);
    	System.out.println("paramMap:" + paramMap);
    	fileCommand = materService.selectMaterDetail(paramMap);
    	if ("M".equals(fileCommand.getMnpbClssCd())) {
    		mav.setViewName("mater/materDetail");
    	}else {
    		mav.setViewName("machinery/machineryDetail");
    	}
    	mav.addObject("mnpbAskYYMM_v", fileCommand.getMnpbAskYYMM().substring(0, 4)+"년"+fileCommand.getMnpbAskYYMM().substring(4, 6)+"월");
    	mav.addObject("mnpbAskYYMM", fileCommand.getMnpbAskYYMM());
    	mav.addObject("fildClssCd", fileCommand.getFildClssCd());
    	mav.addObject("cntcWkscCd", fileCommand.getCntcWkscCd());
    	mav.addObject("cntrtCrprNm", fileCommand.getCntrtCrprNm());
    	mav.addObject("corpNm", fileCommand.getCorpNm());
    	mav.addObject("cntrtNm", fileCommand.getCntrtNm());
    	mav.addObject("custTelno", fileCommand.getCustTelno());
    	mav.addObject("mtriCustNo", fileCommand.getMtriCustNo());
    	mav.addObject("rpprNm", fileCommand.getRpprNm());
    	mav.addObject("deprNm", fileCommand.getDeprNm());
    	mav.addObject("trBankNm", fileCommand.getTrBankNm());
    	mav.addObject("bankActno", fileCommand.getBankActno());
    	mav.addObject("askAmt", fileCommand.getAskAmt());
    	mav.addObject("attflNm", fileCommand.getAttflNm());
    	mav.addObject("attflPath", fileCommand.getAttflPath());
    	mav.addObject("mnpbRgsrSeq", fileCommand.getMnpbRgsrSeq());
    	mav.addObject("mnpbStatCd", fileCommand.getMnpbStatCd());
    	mav.addObject("attflSeq", fileCommand.getAttflSeq());
    	mav.addObject("mnpbAskSqno", fileCommand.getMnpbAskSqno());
    	return mav;
    }
    
    // 파일 다운로드시 로직
    @GetMapping(value = "/downloadFile")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String attflPath, @RequestParam String attflNm) throws Exception {
    	logger.info("downloadAttfl() - attflPath: {}", attflPath);
    	logger.info("downloadAttfl() - attflNm: {}", attflNm);
    	
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
    
    // 자재대금 등록 및 파일 업로드
    @PostMapping(value = "/updateMaterList", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> updateMaterList(FileCommand fileData) throws Exception {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	System.out.println("fileData : " + fileData.getMnpbAskYYMM());
    	SimpleDateFormat year = new SimpleDateFormat("yyyy");
    	SimpleDateFormat month = new SimpleDateFormat("MM");
    	SimpleDateFormat day = new SimpleDateFormat("dd");
    	// 파일업로드 경로 :: 기본경로/년/월/일
		String materFilePath  = filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis())+"/"+day.format(System.currentTimeMillis());
		
		if(fileData.getFlUpFileData() != null && !fileData.getFlUpFileData().getOriginalFilename().equals("") ) {
			fileData.setAttflNm(fileData.getFlUpFileData().getOriginalFilename());
			fileData.setAttflPath(materFilePath);
		} else {
			fileData.setAttflNm("");
			fileData.setAttflPath("");
		}
		
    	int result = materService.updateMaterList(fileData);
		
		try {
			if ( result > 0 ) {
				paramMap.put("SUCCESS", true);
			}else {
				paramMap.put("SUCCESS", false);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		//해당경로 없을 시 경로 생성 
		File dir1 = new File(filePath+"/"+year.format(System.currentTimeMillis()));
		File dir2 = new File(filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis()));
		File dir3 = new File(filePath+"/"+year.format(System.currentTimeMillis())+"/"+month.format(System.currentTimeMillis())+"/"+day.format(System.currentTimeMillis()));
		if(!dir1.exists()) { dir1.mkdirs(); }
		if(!dir2.exists()) { dir2.mkdirs(); }
		if(!dir3.exists()) { dir3.mkdirs(); }
		
		File file = Paths.get(materFilePath, fileData.getFlUpFileData().getOriginalFilename()).toFile();
	    
		try {
            // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
            if (!file.exists()) {
            	fileData.getFlUpFileData().transferTo(file);
            } else {
            	
            }
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (IllegalStateException ie) {
        	ie.printStackTrace();
        }
		
		return paramMap;
    }
    
    @PostMapping(value="/deleteMater")
    public Map<String,Object> deleteMater(@RequestBody Map<String, Object> paramMap) throws Exception {
    	int result = 0;
    	try {
    		result = materService.deleteMater(paramMap);
    		if(result > 0) {
    			paramMap.put("SUCCESS", true);
    		}else {
    			paramMap.put("SUCCESS", false);
    		}
    	}catch (Exception e) {
    		throw e;
    	}
    	return paramMap;
	}
}
