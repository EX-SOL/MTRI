package com.ex.mater.mater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
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
	
    @PostMapping(value = "/insertMaterList", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> insertMaterList(FileCommand fileData) throws Exception {
    	System.out.println("fileData : " + fileData);
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	SimpleDateFormat year = new SimpleDateFormat("yyyy");
    	SimpleDateFormat month = new SimpleDateFormat("MM");
    	SimpleDateFormat day = new SimpleDateFormat("dd");
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

    @GetMapping(value = "/selectMaterDetail")
    public ModelAndView selectMaterDetail(String mtriCustNo, String mrtiMnpbAskYYMM, String mtriMnpbAskSqno, String mtriCd) throws Exception {
    	ModelAndView mav = new ModelAndView();
    	FileCommand fileCommand = new FileCommand();
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("mtriCustNo", mtriCustNo);
    	paramMap.put("mrtiMnpbAskYYMM", mrtiMnpbAskYYMM);
    	paramMap.put("mtriMnpbAskSqno", mtriMnpbAskSqno);
    	paramMap.put("mtriCd", mtriCd);
    	System.out.println("paramMap:" + paramMap);
    	fileCommand = materService.selectMaterDetail(paramMap);
    	mav.setViewName("/mater/materDetail");
    	mav.addObject("mrtiMnpbAskYYMM", fileCommand.getMrtiMnpbAskYYMM().substring(0, 4)+"년"+fileCommand.getMrtiMnpbAskYYMM().substring(5, 6)+"월");
    	mav.addObject("fildClssCd", fileCommand.getFildClssCd());
    	mav.addObject("cntcWkscCd", fileCommand.getCntcWkscCd());
    	mav.addObject("cntrtCrprNm", fileCommand.getCntrtCrprNm());
    	mav.addObject("custNm", fileCommand.getCustNm());
    	mav.addObject("mtriNm", fileCommand.getMtriNm());
    	mav.addObject("custTelno", fileCommand.getCustTelno());
    	mav.addObject("mtriCustNo", fileCommand.getMtriCustNo());
    	mav.addObject("rpprNm", fileCommand.getRpprNm());
    	mav.addObject("deprNm", fileCommand.getDeprNm());
    	mav.addObject("trBankNm", fileCommand.getTrBankNm());
    	mav.addObject("bankActno", fileCommand.getBankActno());
    	mav.addObject("askAmt", fileCommand.getAskAmt());
    	mav.addObject("attflNm", fileCommand.getAttflNm());
    	return mav;
    }
}
