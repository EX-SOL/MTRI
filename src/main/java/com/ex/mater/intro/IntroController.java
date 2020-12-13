package com.ex.mater.intro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ex.util.AES128;


@RestController
@RequestMapping("/intro")
public class IntroController {

	@Resource
	IntroService introService;
	
	@GetMapping(value="/checkplus_main")
	public ModelAndView checkplus_main(HttpServletRequest request, String check1, String check2, String check3, String check4) throws Exception{
		HttpSession session = (HttpSession)request.getSession();
		session.setAttribute("check1", check1);
		session.setAttribute("check2", check2);
		session.setAttribute("check3", check3);
		session.setAttribute("check4", check4);
		ModelAndView mav = new ModelAndView();
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
	    
	    String sSiteCode = "G6369";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "5XC5Z8F33UL4";		// NICE로부터 부여받은 사이트 패스워드
	    
	    String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
	                                                    	// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
	    sRequestNumber = niceCheck.getRequestNO(sSiteCode);
	  	//session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
	  	
	   	String sAuthType = "M";      	// 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
	   	
	   	String popgubun 	= "N";		//Y : 취소버튼 있음 / N : 취소버튼 없음
		String customize 	= "Mobile";		//없으면 기본 웹페이지 / Mobile : 모바일페이지
		
		String sGender = ""; 			//없으면 기본 선택 값, 0 : 여자, 1 : 남자 
		
	    // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
	    //로컬
//		String sReturnUrl = "http://localhost:8085/mater/intro/checkplus_success";      // 성공시 이동될 URL
//		String sErrorUrl = "http://localhost:8085/mater/intro/checkplus_fail";          // 실패시 이동될 URL

	    //개발
	    String sReturnUrl = "http://175.214.44.153:8085/mater/intro/checkplus_success";      // 성공시 이동될 URL
	    String sErrorUrl = "http://175.214.44.153:8085/mater/intro/checkplus_fail";          // 실패시 이동될 URL

	    // 입력될 plain 데이타를 만든다.
	    String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
	                        "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
	                        "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
	                        "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
	                        "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
	                        "11:POPUP_GUBUN" + popgubun.getBytes().length + ":" + popgubun +
	                        "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize + 
							"6:GENDER" + sGender.getBytes().length + ":" + sGender;
	    
	    String sMessage = "";
	    String sEncData = "";
	    
	    int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
	    if( iReturn == 0 )
	    {
	        sEncData = niceCheck.getCipherData();
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "암호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -2)
	    {
	        sMessage = "암호화 처리오류입니다.";
	    }    
	    else if( iReturn == -3)
	    {
	        sMessage = "암호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
		
	    mav.addObject("sMessage", sMessage);
	    mav.addObject("sEncData", sEncData);
	    mav.setViewName("intro/registerCerti");
		return mav;
		
	}
	
	@PostMapping(value="/checkplus_success")
	public ModelAndView checkplus_success(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

	    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

	    String sSiteCode = "G6369";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "5XC5Z8F33UL4";		// NICE로부터 부여받은 사이트 패스워드

	    String sCipherTime = "";			// 복호화한 시간
	    String sRequestNumber = "";			// 요청 번호
	    String sResponseNumber = "";		// 인증 고유번호
	    String sAuthType = "";				// 인증 수단
	    String sName = "";					// 성명
	    String sDupInfo = "";				// 중복가입 확인값 (DI_64 byte)
	    String sConnInfo = "";				// 연계정보 확인값 (CI_88 byte)
	    String sBirthDate = "";				// 생년월일(YYYYMMDD)
	    String sGender = "";				// 성별
	    String sNationalInfo = "";			// 내/외국인정보 (개발가이드 참조)
		String sMobileNo = "";				// 휴대폰번호
		String sMobileCo = "";				// 통신사
	    String sMessage = "";
	    String sPlainData = "";
	    
	    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	    if( iReturn == 0 )
	    {
	        sPlainData = niceCheck.getPlainData();
	        sCipherTime = niceCheck.getCipherDateTime();
	        
	        // 데이타를 추출합니다.
	        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
	        
	        sRequestNumber  = (String)mapresult.get("REQ_SEQ");
	        sResponseNumber = (String)mapresult.get("RES_SEQ");
	        sAuthType		= (String)mapresult.get("AUTH_TYPE");
	        sName			= (String)mapresult.get("NAME");
			//sName			= (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
	        sBirthDate		= (String)mapresult.get("BIRTHDATE");
	        sGender			= (String)mapresult.get("GENDER");
	        sNationalInfo  	= (String)mapresult.get("NATIONALINFO");
	        sDupInfo		= (String)mapresult.get("DI");
	        sConnInfo		= (String)mapresult.get("CI");
	        sMobileNo		= (String)mapresult.get("MOBILE_NO");
	        sMobileCo		= (String)mapresult.get("MOBILE_CO");
	        
//	        String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
//	        if(!sRequestNumber.equals(session_sRequestNumber))
//	        {
//	            sMessage = "세션값이 다릅니다. 올바른 경로로 접근하시기 바랍니다.";
//	            sResponseNumber = "";
//	            sAuthType = "";
//	        }
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "복호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -4)
	    {
	        sMessage = "복호화 처리오류입니다.";
	    }    
	    else if( iReturn == -5)
	    {
	        sMessage = "복호화 해쉬 오류입니다.";
	    }    
	    else if( iReturn == -6)
	    {
	        sMessage = "복호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else if( iReturn == -12)
	    {
	        sMessage = "사이트 패스워드 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
	    
	    mav.addObject("sMessage", sMessage);
	    mav.addObject("sRequestNumber", sRequestNumber);
	    mav.addObject("sResponseNumber", sResponseNumber);
	    mav.addObject("sAuthType", sAuthType);
	    mav.addObject("sName", sName);
	    mav.addObject("sBirthDate", sBirthDate);
	    mav.addObject("sGender", sGender);
	    mav.addObject("sNationalInfo", sNationalInfo);
	    mav.addObject("sDupInfo", sDupInfo);
	    mav.addObject("sConnInfo", sConnInfo);
	    mav.addObject("sMobileNo", sMobileNo);
	    mav.addObject("sMobileCo", sMobileCo);
	    mav.setViewName("intro/registerContent");
		return mav;
		
	}
	
	public String requestReplace (String paramValue, String gubun) {
        String result = "";
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	result = paramValue;
        }
        return result;
  }

	@PostMapping(value="/checkplus_fail")
	public ModelAndView checkplus_fail(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		HttpSession session = (HttpSession)request.getSession();
		session.invalidate();
		mav.setViewName("intro/registerAgree");
		return mav;
	}
	
	
	@GetMapping(value="/idFind_Checkplus")
	public ModelAndView idFind_Checkplus(HttpServletRequest request, String userNm, String userPhoneNo) throws Exception{
		HttpSession session = (HttpSession)request.getSession();
		session.setAttribute("userNm", userNm);
		session.setAttribute("userPhoneNo", userPhoneNo);
		ModelAndView mav = new ModelAndView();
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
	    
	    String sSiteCode = "G6369";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "5XC5Z8F33UL4";		// NICE로부터 부여받은 사이트 패스워드
	    
	    String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
	                                                    	// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
	    sRequestNumber = niceCheck.getRequestNO(sSiteCode);
	  	//session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
	  	
	   	String sAuthType = "M";      	// 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
	   	
	   	String popgubun 	= "N";		//Y : 취소버튼 있음 / N : 취소버튼 없음
		String customize 	= "Mobile";		//없으면 기본 웹페이지 / Mobile : 모바일페이지
		
		String sGender = ""; 			//없으면 기본 선택 값, 0 : 여자, 1 : 남자 
		
	    // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
	    //로컬
//		String sReturnUrl = "http://localhost:8085/mater/intro/idFind_success";      // 성공시 이동될 URL
//		String sErrorUrl = "http://localhost:8085/mater/intro/idFind_fail";          // 실패시 이동될 URL

	    //개발
	    String sReturnUrl = "http://175.214.44.153:8085/mater/intro/idFind_success";      // 성공시 이동될 URL
	    String sErrorUrl = "http://175.214.44.153:8085/mater/intro/idFind_fail";          // 실패시 이동될 URL

	    // 입력될 plain 데이타를 만든다.
	    String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
	                        "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
	                        "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
	                        "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
	                        "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
	                        "11:POPUP_GUBUN" + popgubun.getBytes().length + ":" + popgubun +
	                        "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize + 
							"6:GENDER" + sGender.getBytes().length + ":" + sGender;
	    
	    String sMessage = "";
	    String sEncData = "";
	    
	    int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
	    if( iReturn == 0 )
	    {
	        sEncData = niceCheck.getCipherData();
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "암호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -2)
	    {
	        sMessage = "암호화 처리오류입니다.";
	    }    
	    else if( iReturn == -3)
	    {
	        sMessage = "암호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
		
	    mav.addObject("sMessage", sMessage);
	    mav.addObject("sEncData", sEncData);
	    mav.setViewName("intro/registerCerti");
		return mav;
		
	}
	
	@PostMapping(value="/idFind_success")
	public ModelAndView idFind_success(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

	    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

	    String sSiteCode = "G6369";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "5XC5Z8F33UL4";		// NICE로부터 부여받은 사이트 패스워드

	    String sCipherTime = "";			// 복호화한 시간
	    String sRequestNumber = "";			// 요청 번호
	    String sResponseNumber = "";		// 인증 고유번호
	    String sAuthType = "";				// 인증 수단
	    String sName = "";					// 성명
	    String sDupInfo = "";				// 중복가입 확인값 (DI_64 byte)
	    String sConnInfo = "";				// 연계정보 확인값 (CI_88 byte)
	    String sBirthDate = "";				// 생년월일(YYYYMMDD)
	    String sGender = "";				// 성별
	    String sNationalInfo = "";			// 내/외국인정보 (개발가이드 참조)
		String sMobileNo = "";				// 휴대폰번호
		String sMobileCo = "";				// 통신사
	    String sMessage = "";
	    String sPlainData = "";
	    
	    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	    if( iReturn == 0 )
	    {
	        sPlainData = niceCheck.getPlainData();
	        sCipherTime = niceCheck.getCipherDateTime();
	        
	        // 데이타를 추출합니다.
	        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
	        
	        sRequestNumber  = (String)mapresult.get("REQ_SEQ");
	        sResponseNumber = (String)mapresult.get("RES_SEQ");
	        sAuthType		= (String)mapresult.get("AUTH_TYPE");
	        sName			= (String)mapresult.get("NAME");
			//sName			= (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
	        sBirthDate		= (String)mapresult.get("BIRTHDATE");
	        sGender			= (String)mapresult.get("GENDER");
	        sNationalInfo  	= (String)mapresult.get("NATIONALINFO");
	        sDupInfo		= (String)mapresult.get("DI");
	        sConnInfo		= (String)mapresult.get("CI");
	        sMobileNo		= (String)mapresult.get("MOBILE_NO");
	        sMobileCo		= (String)mapresult.get("MOBILE_CO");
	        
//	        String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
//	        if(!sRequestNumber.equals(session_sRequestNumber))
//	        {
//	            sMessage = "세션값이 다릅니다. 올바른 경로로 접근하시기 바랍니다.";
//	            sResponseNumber = "";
//	            sAuthType = "";
//	        }
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "복호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -4)
	    {
	        sMessage = "복호화 처리오류입니다.";
	    }    
	    else if( iReturn == -5)
	    {
	        sMessage = "복호화 해쉬 오류입니다.";
	    }    
	    else if( iReturn == -6)
	    {
	        sMessage = "복호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else if( iReturn == -12)
	    {
	        sMessage = "사이트 패스워드 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
	    
	    mav.addObject("sMessage", sMessage);
	    mav.addObject("sRequestNumber", sRequestNumber);
	    mav.addObject("sResponseNumber", sResponseNumber);
	    mav.addObject("sAuthType", sAuthType);
	    mav.addObject("sName", sName);
	    mav.addObject("sBirthDate", sBirthDate);
	    mav.addObject("sGender", sGender);
	    mav.addObject("sNationalInfo", sNationalInfo);
	    mav.addObject("sDupInfo", sDupInfo);
	    mav.addObject("sConnInfo", sConnInfo);
	    mav.addObject("sMobileNo", sMobileNo);
	    mav.addObject("sMobileCo", sMobileCo);
	    mav.setViewName("intro/idFind");
	    
		return mav;
	}
	
	@PostMapping(value="/idFind_fail")
	public ModelAndView idFind_fail(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		HttpSession session = (HttpSession)request.getSession();
		session.invalidate();
		mav.setViewName("intro/idFind");
		return mav;
	}
	
	//아이디찾기
	@PostMapping(value="/selectIdFind")
	public List<Map<String,Object>> selectIdFind(@RequestBody Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		HttpSession session = (HttpSession)request.getSession();
		try {
			resultList = introService.selectIdFind(paramMap);
			
			if(paramMap.get("userId") == null &&resultList.size() > 0) {
				// 인증 성공으로 세션날리기
				session.invalidate();
			}else {
				System.out.println("==session ========");
			}
		}catch (Exception e) {
			throw e;
		}
		return resultList;
	}
	

	@GetMapping(value="/pwFind_Checkplus")
	public ModelAndView pwFind_Checkplus(HttpServletRequest request, String userId, String userNm, String userPhoneNo) throws Exception{
		HttpSession session = (HttpSession)request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("userNm", userNm);
		session.setAttribute("userPhoneNo", userPhoneNo);
		ModelAndView mav = new ModelAndView();
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
	    
	    String sSiteCode = "G6369";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "5XC5Z8F33UL4";		// NICE로부터 부여받은 사이트 패스워드
	    
	    String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
	                                                    	// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
	    sRequestNumber = niceCheck.getRequestNO(sSiteCode);
	  	//session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
	  	
	   	String sAuthType = "M";      	// 없으면 기본 선택화면, M: 핸드폰, C: 신용카드, X: 공인인증서
	   	
	   	String popgubun 	= "N";		//Y : 취소버튼 있음 / N : 취소버튼 없음
		String customize 	= "Mobile";		//없으면 기본 웹페이지 / Mobile : 모바일페이지
		
		String sGender = ""; 			//없으면 기본 선택 값, 0 : 여자, 1 : 남자 
		
	    // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
	    //로컬
//		String sReturnUrl = "http://localhost:8085/mater/intro/pwFind_success";      // 성공시 이동될 URL
//		String sErrorUrl = "http://localhost:8085/mater/intro/pwFind_fail";          // 실패시 이동될 URL

	    //개발
	    String sReturnUrl = "http://175.214.44.153:8085/mater/intro/pwFind_success";      // 성공시 이동될 URL
	    String sErrorUrl = "http://175.214.44.153:8085/mater/intro/pwFind_fail";          // 실패시 이동될 URL

	    // 입력될 plain 데이타를 만든다.
	    String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
	                        "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
	                        "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
	                        "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
	                        "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
	                        "11:POPUP_GUBUN" + popgubun.getBytes().length + ":" + popgubun +
	                        "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize + 
							"6:GENDER" + sGender.getBytes().length + ":" + sGender;
	    
	    String sMessage = "";
	    String sEncData = "";
	    
	    int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
	    if( iReturn == 0 )
	    {
	        sEncData = niceCheck.getCipherData();
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "암호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -2)
	    {
	        sMessage = "암호화 처리오류입니다.";
	    }    
	    else if( iReturn == -3)
	    {
	        sMessage = "암호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
		
	    mav.addObject("sMessage", sMessage);
	    mav.addObject("sEncData", sEncData);
	    mav.setViewName("intro/registerCerti");
		return mav;
		
	}
	
	@PostMapping(value="/pwFind_success")
	public ModelAndView pwFind_success(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

	    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

	    String sSiteCode = "G6369";			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = "5XC5Z8F33UL4";		// NICE로부터 부여받은 사이트 패스워드

	    String sCipherTime = "";			// 복호화한 시간
	    String sRequestNumber = "";			// 요청 번호
	    String sResponseNumber = "";		// 인증 고유번호
	    String sAuthType = "";				// 인증 수단
	    String sName = "";					// 성명
	    String sDupInfo = "";				// 중복가입 확인값 (DI_64 byte)
	    String sConnInfo = "";				// 연계정보 확인값 (CI_88 byte)
	    String sBirthDate = "";				// 생년월일(YYYYMMDD)
	    String sGender = "";				// 성별
	    String sNationalInfo = "";			// 내/외국인정보 (개발가이드 참조)
		String sMobileNo = "";				// 휴대폰번호
		String sMobileCo = "";				// 통신사
	    String sMessage = "";
	    String sPlainData = "";
	    
	    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	    if( iReturn == 0 )
	    {
	        sPlainData = niceCheck.getPlainData();
	        sCipherTime = niceCheck.getCipherDateTime();
	        
	        // 데이타를 추출합니다.
	        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
	        
	        sRequestNumber  = (String)mapresult.get("REQ_SEQ");
	        sResponseNumber = (String)mapresult.get("RES_SEQ");
	        sAuthType		= (String)mapresult.get("AUTH_TYPE");
	        sName			= (String)mapresult.get("NAME");
			//sName			= (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
	        sBirthDate		= (String)mapresult.get("BIRTHDATE");
	        sGender			= (String)mapresult.get("GENDER");
	        sNationalInfo  	= (String)mapresult.get("NATIONALINFO");
	        sDupInfo		= (String)mapresult.get("DI");
	        sConnInfo		= (String)mapresult.get("CI");
	        sMobileNo		= (String)mapresult.get("MOBILE_NO");
	        sMobileCo		= (String)mapresult.get("MOBILE_CO");
	        
//	        String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
//	        if(!sRequestNumber.equals(session_sRequestNumber))
//	        {
//	            sMessage = "세션값이 다릅니다. 올바른 경로로 접근하시기 바랍니다.";
//	            sResponseNumber = "";
//	            sAuthType = "";
//	        }
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "복호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -4)
	    {
	        sMessage = "복호화 처리오류입니다.";
	    }    
	    else if( iReturn == -5)
	    {
	        sMessage = "복호화 해쉬 오류입니다.";
	    }    
	    else if( iReturn == -6)
	    {
	        sMessage = "복호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else if( iReturn == -12)
	    {
	        sMessage = "사이트 패스워드 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
	    
	    mav.addObject("sMessage", sMessage);
	    mav.addObject("sRequestNumber", sRequestNumber);
	    mav.addObject("sResponseNumber", sResponseNumber);
	    mav.addObject("sAuthType", sAuthType);
	    mav.addObject("sName", sName);
	    mav.addObject("sBirthDate", sBirthDate);
	    mav.addObject("sGender", sGender);
	    mav.addObject("sNationalInfo", sNationalInfo);
	    mav.addObject("sDupInfo", sDupInfo);
	    mav.addObject("sConnInfo", sConnInfo);
	    mav.addObject("sMobileNo", sMobileNo);
	    mav.addObject("sMobileCo", sMobileCo);
	    mav.setViewName("intro/pwFind");
	    
		return mav;
	}
	
	@PostMapping(value="/pwFind_fail")
	public ModelAndView pwFind_fail(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		HttpSession session = (HttpSession)request.getSession();
		session.invalidate();
		mav.setViewName("intro/pwFind");
		return mav;
	}
	
	//아이디찾기
	@PostMapping(value="/updateNewPw")
	public Map<String,Object> updateNewPw(@RequestBody Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession)request.getSession();
		try {
			paramMap.put("pswd", strEncode((String)paramMap.get("newPswd")));
			int result = introService.updateNewPw(paramMap);
			
			if(result > 0) {
				paramMap.put("SUCCESS", true);
				// 인증 성공으로 세션날리기
				session.invalidate();
			}else {
				paramMap.put("SUCCESS", false);
			}
		}catch (Exception e) {
			throw e;
		}
		return paramMap;
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

	@PostMapping(value="/goBackMain")
	public String goBackMain( HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession)request.getSession();
		try {
			System.out.println("session clean");
			// 인증 성공으로 세션날리기
			session.invalidate();
		}catch (Exception e) {
			throw e;
		}
		return "";
	}
}
