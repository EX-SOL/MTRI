package com.ex.mater.ref;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ref")
public class ReferenceController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	ReferenceService referenceService;
	
    @Value("${app.global.file-path}")
    protected String filePath;
	
    @PostMapping(value = "/selectReferenceList")
    public List<Reference> selectReferenceList(@RequestBody Map<String, Object> dataMap) throws Exception{
    	List<Reference> list = new ArrayList<Reference>();
    	try {
    		list = referenceService.selectReferenceList(dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    @PostMapping(value = "/insertReference", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> insertReference(Reference refData) throws Exception {
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
		
    	String ext = refData.getFlUpFileData().getOriginalFilename().substring(refData.getFlUpFileData().getOriginalFilename().lastIndexOf(".") + 1);
    	if ( imageExt(ext.trim())) {
    		if(refData.getFlUpFileData() == null || refData.getFlUpFileData().getOriginalFilename().equals("") ) {
				refData.setAttflNm("");
				refData.setAttflPath("");
				paramMap.put("SUCCESS", true);
			} else {
				File file = new File(materFilePath+"/"+refData.getFlUpFileData().getOriginalFilename());
				
				String newFileName = refData.getMtriCustNo() + "_" +today.format(System.currentTimeMillis()) +"(1)";
				try {
		            // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
		            if (!file.exists()) {
		            	refData.getFlUpFileData().transferTo(file);
		            	
		            	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
		        		boolean isRename = file.renameTo(newFile);
		        		if(isRename) {
		        			paramMap.put("SUCCESS", true);
		        			refData.setAttflNm(newFileName+"."+ext);
		    				refData.setAttflPath(materFilePath);
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
			}
			
			//----------------------파일업로드----------------------------------------------
				    
			if(paramMap.get("SUCCESS").equals(true)) {
				
				refData.setFsttmRgsrId(refData.getMtriCustNo());
				refData.setLsttmModfrId(refData.getMtriCustNo());
				
				int result = referenceService.insertReference(refData);
				
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
    
    @GetMapping(value="selectReferenceDetail")
    public ModelAndView selectReferenceDetail (@RequestParam String rfrmrBlbnSeq) throws Exception{
    	ModelAndView mav = new ModelAndView();
    	Reference reference = new Reference();
    	try {
    		reference = referenceService.selectReferenceDetail(rfrmrBlbnSeq);
    		mav.addObject("rfrmrBlbnTitlNm", reference.getRfrmrBlbnTitlNm());
    		mav.addObject("bltnStrtDates", reference.getBltnStrtDates());
    		mav.addObject("bltnEndDates", reference.getBltnEndDates());
    		mav.addObject("rfrmrBlbnCtnt", reference.getRfrmrBlbnCtnt());
    		mav.addObject("attflPath", reference.getAttflPath());
    		mav.addObject("attflNm", reference.getAttflNm());
    		mav.addObject("mtriCustNo", reference.getMtriCustNo());
    		mav.addObject("rfrmrBlbnSeq", reference.getRfrmrBlbnSeq());
    		mav.addObject("attflSeq", reference.getAttflSeq());
    		mav.addObject("fsttmRgsrId", reference.getFsttmRgsrId());
    		mav.setViewName("ref/referenceDetail");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return mav;
    }
    
    @PostMapping(value = "/updateReference", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> updateReference(Reference refData) throws Exception {
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
		
    	String ext = refData.getFlUpFileData().getOriginalFilename().substring(refData.getFlUpFileData().getOriginalFilename().lastIndexOf(".") + 1);
    	if ( imageExt(ext.trim())) {
    		if(refData.getFlUpFileData() == null || refData.getFlUpFileData().getOriginalFilename().equals("") ) {
				refData.setAttflNm("");
				refData.setAttflPath("");
				
				paramMap.put("SUCCESS", true);
			} else {
				File file = new File(materFilePath+"/"+refData.getFlUpFileData().getOriginalFilename());
				
				String newFileName = refData.getMtriCustNo() + "_" +today.format(System.currentTimeMillis())+"(1)";
				try {
		            // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
		            if (!file.exists()) {
		            	refData.getFlUpFileData().transferTo(file);
		            	
		            	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
		        		boolean isRename = file.renameTo(newFile);
		        		if(isRename) {
		        			paramMap.put("SUCCESS", true);
		        			refData.setAttflNm(newFileName+"."+ext);
		    				refData.setAttflPath(materFilePath);
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
			}
			
			//----------------------파일업로드----------------------------------------------
			
			
			if(paramMap.get("SUCCESS").equals(true)) {
				refData.setFsttmRgsrId(refData.getMtriCustNo());
				refData.setLsttmModfrId(refData.getMtriCustNo());
				
				int result = referenceService.updateReference(refData);
				
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
    
    
    @PostMapping(value="/deleteReference")
    public Map<String, Object> deleteReference (@RequestBody Map<String,Object> dataParam) throws Exception{
    	int result = 0;
    	try {
    		result = referenceService.deleteReference(dataParam);
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
