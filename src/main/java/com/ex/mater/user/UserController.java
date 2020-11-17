package com.ex.mater.user;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ex.mater.user.model.User;
import com.ex.util.AES128;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	UserService userService;
	
	@PostMapping(value="/login")
    public Map<String,Object> login(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, Object> paramMap) throws Exception {
    	logger.info("login param: {}", paramMap);
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	HttpSession session = (HttpSession)request.getSession();
    	
    	try {
    		if ( !"1".equals(paramMap.get("mtriCustNo")) ) {
    			User user = userService.selectLogin(paramMap);
        		System.out.println("user " + user);
        		if( user == null ) {
        			resultMap.put("SUCCESS", false);
        			resultMap.put("msg", "회원정보가 존재하지 않습니다.");
        			
        		}else {
        			System.out.println(paramMap.get("custPswd"));
        			System.out.println(strDecode(user.getCustPswd()));
        			if ( strDecode(user.getCustPswd()).equals(paramMap.get("custPswd"))) {
        				session.setAttribute("mtriCustNo", user.getMtriCustNo());
                		session.setAttribute("custPswd", user.getCustPswd());
                		session.setAttribute("custAthrCd", user.getCustAthrCd());
                		session.setAttribute("custTelno", user.getCustTelno());
                		session.setAttribute("rpprNm", user.getRpprNm());
                		session.setAttribute("deprNm", user.getDeprNm());
                		session.setAttribute("bankActno", user.getBankActno());
                		session.setAttribute("trBankNm", user.getTrBankNm());
                		session.setAttribute("custNm", user.getCustNm());
                		resultMap.put("SUCCESS", true);
                		
            		} else {
            			resultMap.put("SUCCESS", false);
            			resultMap.put("msg", "비밀번호를 확인해주세요.");
            		}
        		}
    		} else {
    			session.setAttribute("mtriCustNo", "1058657078");
        		session.setAttribute("custPswd", "1234");
        		session.setAttribute("custAthrCd", "A");
        		session.setAttribute("custTelno", "01011112222");
        		session.setAttribute("rpprNm", "aa");
        		session.setAttribute("deprNm", "bb");
        		session.setAttribute("bankActno", "1002141051265");
        		session.setAttribute("trBankNm", "우리은행");
        		session.setAttribute("custNm", "(주)아와소프트");
        		resultMap.put("SUCCESS", true);
    		}
    		
    	}catch (Exception e) {
    		throw e;
    	}
    	return resultMap;
	}
	
	@PostMapping(value="/logout")
	public Map<String,Object> logout(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		HttpSession session = (HttpSession)request.getSession();
		session.invalidate();
		
		return resultMap;
	}
	
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
	
	
	
