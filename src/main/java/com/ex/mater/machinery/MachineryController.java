package com.ex.mater.machinery;

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

import com.ex.mater.mater.FileCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/machinery")
public class MachineryController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	MachineryService machineryService;
	
	@Value("${app.global.file-path}")
    protected String filePath;
	
	// 장비대금 등록 및 파일 업로드
    @PostMapping(value = "/insertMachineryList", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
		
    	int result = machineryService.insertMachineryList(fileData);
		
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
   
}
