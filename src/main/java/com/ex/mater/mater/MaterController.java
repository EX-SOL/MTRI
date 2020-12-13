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

import com.ex.util.AES128;

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
    	paramMap.put("SUCCESS", true);
    	
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
		
		File file = Paths.get(materFilePath, fileData.getFlUpFileData().getOriginalFilename()).toFile();
		
		String newFileName = fileData.getMtriCustNo() + "_" +today.format(System.currentTimeMillis()) +"(1)";
    	String ext = fileData.getFlUpFileData().getOriginalFilename().substring(fileData.getFlUpFileData().getOriginalFilename().lastIndexOf(".") + 1);
    	// jpg png jpeg pdf일 경우에만 등록
    	if ( imageExt(ext.trim())) {
    		try {
                // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
                if (!file.exists()) {
                	fileData.getFlUpFileData().transferTo(file);
                	
                	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
            		boolean isRename = file.renameTo(newFile);
            		if(isRename) {
            			paramMap.put("SUCCESS", true);
            			fileData.setAttflNm(newFileName+"."+ext);
        				fileData.setAttflPath(materFilePath);
            		} else {
            			paramMap.put("SUCCESS", false);
            		}
                }
            } catch (Exception e) {
            	paramMap.put("SUCCESS", false);
            	throw e;
            } 
    		//----------------------파일업로드----------------------------------------------
    		
    		if(paramMap.get("SUCCESS").equals(true)) {
    			if(fileData.getFlUpFileData() == null && fileData.getFlUpFileData().getOriginalFilename().equals("") ) {
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
    		}
    	}else {
    		paramMap.put("SUCCESS", false);
    		paramMap.put("resultMsg", "이미지 또는 pdf파일만 업로드가능합니다.");
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

    	fileCommand = materService.selectMaterDetail(paramMap);
    	if ("M".equals(fileCommand.getMnpbClssCd())) {
    		mav.setViewName("mater/materDetail");
    	}else {
    		mav.setViewName("machinery/machineryDetail");
    	}
    	
    	mav.addObject("mnpbAskYYMM_v", fileCommand.getMnpbAskYYMM().substring(0, 4)+"년"+fileCommand.getMnpbAskYYMM().substring(4, 6)+"월");
    	mav.addObject("mnpbAskYYMM", fileCommand.getMnpbAskYYMM());
    	mav.addObject("blngDptcd", fileCommand.getBlngDptcd());
    	mav.addObject("wkscCd", fileCommand.getWkscCd());
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
    	mav.addObject("korDptnm", fileCommand.getKorDptnm());
    	mav.addObject("wkscNm", fileCommand.getWkscNm());
    	mav.addObject("rprsCrno", fileCommand.getRprsCrno());
    	mav.addObject("cntrtCrprSeq", fileCommand.getCntrtCrprSeq());
    	mav.addObject("etcRmrk", fileCommand.getEtcRmrk());
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
    	} else if(attflType.equals(".pdf")) {
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
    	
    	System.out.println("mimeType : " + mimeType);
    	System.out.println("fileNm : " + fileNm);
    	
    	
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
    	paramMap.put("SUCCESS", true);
    	
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
		String ext = fileData.getFlUpFileData().getOriginalFilename().substring(fileData.getFlUpFileData().getOriginalFilename().lastIndexOf(".") + 1);
		if ( imageExt(ext.trim())) {
			File file = Paths.get(materFilePath, fileData.getFlUpFileData().getOriginalFilename()).toFile();
		    System.out.println("file : " + file);
			String newFileName = fileData.getMtriCustNo() + "_" +today.format(System.currentTimeMillis()) +"(1)";
			try {
	            // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
	            if (!file.exists()) {
	            	fileData.getFlUpFileData().transferTo(file);
	            	
	            	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
	        		boolean isRename = file.renameTo(newFile);
	        		if(isRename) {
	        			paramMap.put("SUCCESS", true);
	        			fileData.setAttflNm(newFileName+"."+ext);
	    				fileData.setAttflPath(materFilePath);
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
			
			System.out.println("SUCCESS > " + paramMap.get("SUCCESS"));
			if(paramMap.get("SUCCESS").equals(true)) {
				if(fileData.getFlUpFileData() == null && fileData.getFlUpFileData().getOriginalFilename().equals("") ) {
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
			}
		} else {
			paramMap.put("SUCCESS", false);
    		paramMap.put("resultMsg", "이미지 또는 pdf파일만 업로드가능합니다.");
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

    //현장명 목록조회
    @PostMapping(value="/selectFildData")
    public List<Map<String,Object>> selectFildData() throws Exception {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	try {
    		list = materService.selectFildData();
    	}catch (Exception e) {
    		throw e;
    	}
    	return list;
    }
    
    //공구명 목록조회
    @PostMapping(value="/selectWkscData")
    public List<Map<String,Object>> selectWkscData(@RequestBody Map<String, Object> param) throws Exception {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	try {
    		list = materService.selectWkscData(param);
    	}catch (Exception e) {
    		throw e;
    	}
    	return list;
    }

    //계약업체명 목록조회
    @PostMapping(value="/selectCntrtCrprList")
    public List<Map<String,Object>> selectCntrtCrprList(@RequestBody Map<String, Object> param) throws Exception {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	try {
    		list = materService.selectCntrtCrprList(param);
    	}catch (Exception e) {
    		throw e;
    	}
    	return list;
    }
    
    // 자재&장비 메인 목록 3건 조회
    @PostMapping(value = "/selectAdminList")
    public List<FileCommand> selectAdminList(@RequestBody Map<String, Object> dataMap) throws Exception {
		List<FileCommand> list = new ArrayList<>();
		try {
			list = materService.selectAdminList(dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
    
    @PostMapping(value = "/createAdmin")
    public Map<String, Object> createAdmin(@RequestBody Map<String, Object> dataMap) throws Exception {
    	Map<String, Object> newMap = new HashMap<String, Object>();
    	int result = 0;
    	try {
    		
    		dataMap.put("custPswd", strEncode((String)dataMap.get("custPswd")));
    		result = materService.createAdmin(dataMap);
    		if(result > 0) {
    			newMap.put("SUCCESS", true);
    		}else {
    			newMap.put("SUCCESS", false);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return newMap;
    }
    
    // AES128.java에서 encode한 결과 리턴
	public String strEncode(String str) throws Exception{
		String resultStr = "";
		try {
			resultStr = AES128.encrypt(str, "7dR9fJu21Zsn7Tgf", "5gjWs76fT3V8emK1");
		} catch (Exception e) {
			throw e;
		}
		return resultStr;
	}
	
	// 비밀번호 decode
	public String strDecode(String str) throws Exception{
		String resultStr = "";
		try {
			resultStr = AES128.decrypt(str, "7dR9fJu21Zsn7Tgf", "5gjWs76fT3V8emK1");
		} catch (Exception e) {
			throw e;
		}
		return resultStr;
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
