package com.ex.mater.mater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        	
        } catch (IllegalStateException ie) {
        	
        }
		
		return paramMap;
    }
	
}
