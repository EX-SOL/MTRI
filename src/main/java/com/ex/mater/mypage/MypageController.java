package com.ex.mater.mypage;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.util.AES128;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/mypage")
public class MypageController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	MypageService mypageService;
	
	// 마이페이지 정보 조회
    @PostMapping(value = "/selectMypage")
    public Mypage selectMypage(@RequestBody Map<String, Object> paramMap) throws Exception {
    	Mypage rtnMap = new Mypage();

    	try {
    		rtnMap = mypageService.selectMypage(paramMap);
    		rtnMap.setCustPswd(strDecode((String)rtnMap.getCustPswd()));
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
		return rtnMap;
    }
	
    // 마이페이지 수정하기 전 비밀번호 확인
    @PostMapping(value = "/selectMyPassword")
    public Map<String,Object> selectMyPassword(@RequestBody Map<String, Object> paramMap) throws Exception {
    	Map<String, Object> newParamMap;
    	try {
    		newParamMap = mypageService.selectMyPassword(paramMap);
    				
    		if(paramMap.get("pswd").equals(strDecode((String)newParamMap.get("USER_PSWD")))) {
    			paramMap.put("SUCCESS", true);
    		} else {
    			paramMap.put("SUCCESS", false);
    		}
    	} catch (Exception ex) {
    		paramMap.put("SUCCESS", false);
    		ex.printStackTrace();
    	}
    	return paramMap;
    }
    
    
    @PostMapping(value = "/updateMypage")
    public Map<String, Object> updateMypage(@RequestBody Map<String, Object> paramMap) throws Exception {
    	System.out.println("paramMap : " + paramMap);
		
		try {
			paramMap.put("custPswd", strEncode((String)paramMap.get("oldPswd")));
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
    
    @PostMapping(value = "/deleteMypage")
    public Map<String, Object> deleteMypage(@RequestBody Map<String, Object> paramMap) throws Exception {
    	
    	try {
    		int result = mypageService.deleteMypage(paramMap);
    		
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

}
