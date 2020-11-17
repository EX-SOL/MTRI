package com.ex.mater.mypage;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.mater.mater.FileCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/mypage")
public class MypageController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	MypageService mypageService;
	
    @PostMapping(value = "/selectMypage")
    public List<Mypage> selectMypage(@RequestBody Map<String, Object> paramMap) throws Exception {
    	List<Mypage> rtnMap = new ArrayList<Mypage>();

    	try {
    		rtnMap = mypageService.selectMypage(paramMap);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
		return rtnMap;
    }
	
    
    @PostMapping(value = "/updateMypage")
    public Map<String, Object> insertMaterList(@RequestBody Map<String, Object> paramMap) throws Exception {
    	System.out.println("paramMap : " + paramMap);
    	
    	SimpleDateFormat year = new SimpleDateFormat("yyyy");
    	SimpleDateFormat month = new SimpleDateFormat("MM");
    	SimpleDateFormat day = new SimpleDateFormat("dd");
		
		
		try {
	    	int result = mypageService.updateMypage(paramMap);

	    	if ( result > 0 ) {
				paramMap.put("SUCCESS", true);
			}else {
				paramMap.put("SUCCESS", false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paramMap;
    }

}
