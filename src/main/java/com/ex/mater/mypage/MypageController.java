package com.ex.mater.mypage;

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
public class MypageController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	MypageService materService;
	
    @PostMapping(value = "/insertMaterList")
    public Map<String, Object> insertMaterList(Map<String, Object> paramMap) throws Exception {
    	int result = 0;
    	//int result = materService.insertMaterList(paramMap);
		
		try {
			if ( result > 0 ) {
				paramMap.put("SUCCESS", true);
			}else {
				paramMap.put("SUCCESS", false);
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		
		return paramMap;
    }
	
}
