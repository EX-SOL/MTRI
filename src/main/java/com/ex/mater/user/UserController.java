package com.ex.mater.user;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
	
	@Value("${app.global.file-path}")
    protected String filePath;
	
	// 로그인
	@PostMapping(value="/login")
    public Map<String,Object> login(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, Object> paramMap) throws Exception {
    	logger.info("login param: {}", paramMap);
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	HttpSession session = (HttpSession)request.getSession();
    	
    	try {
    			User user = userService.selectLogin(paramMap);

    			if( user == null ) {
        			resultMap.put("SUCCESS", false);
        			resultMap.put("msg", "회원정보가 존재하지 않습니다.");
        			
        		}else {
        			if ( strDecode(user.getCustPswd()).equals(paramMap.get("custPswd"))) {
        				session.setAttribute("mtriCustNo", user.getMtriCustNo()); //사용자id
        				session.setAttribute("custNm", user.getCustNm());		  //사용자명
                		session.setAttribute("custAthrCd", user.getCustAthrCd()); //사용자권한
                		session.setAttribute("custTelno", user.getCustTelno());	  //사용자연락처
                		session.setAttribute("rpprNm", user.getRpprNm());	      //대표자명
                		session.setAttribute("deprNm", user.getDeprNm());		  //예금주명
                		session.setAttribute("bankActno", user.getBankActno());	  //계좌번호
                		session.setAttribute("trBankNm", user.getTrBankNm());	  //은행명
                		session.setAttribute("corpNm", user.getCorpNm());	      //법인명
                		session.setAttribute("rprsCrno", user.getRprsCrno());	  //사업자등록번호
                		session.setAttribute("blngDptcd", user.getBlngDptcd());	  //소속부서코드
                		session.setAttribute("wkscCd", user.getWkscCd());	  	  //소속공구코드
                		resultMap.put("SUCCESS", true);
                		
            		} else {
            			resultMap.put("SUCCESS", false);
            			resultMap.put("msg", "비밀번호를 확인해주세요.");
            		}
        		}
    		
    	}catch (Exception e) {
    		throw e;
    	}
    	return resultMap;
	}

	// 아이디중복체크
	@PostMapping(value="/idCheck")
	public Map<String,Object> idCheck(@RequestBody Map<String, Object> paramMap) throws Exception {
		logger.info("idCheck param: {}", paramMap);
		Map<String,Object> newResultMap = new HashMap<String, Object>();
		try {
			newResultMap = userService.selectIdCheck(paramMap);

		}catch (Exception e) {
			throw e;
		}
		return newResultMap;
	}

	// 사업자등록번호중복체크
	@PostMapping(value="/rprsCrnoCheck")
	public Map<String,Object> rprsCrnoCheck(@RequestBody Map<String, Object> paramMap) throws Exception {
		logger.info("rprsCrnoCheck param: {}", paramMap);
		Map<String,Object> newResultMap = new HashMap<String, Object>();
		try {
			newResultMap = userService.selectRprsCrnoCheck(paramMap);
		}catch (Exception e) {
			throw e;
		}
		return newResultMap;
	}
	
	// 로그아웃
	@PostMapping(value="/logout")
	public Map<String,Object> logout(HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		HttpSession session = (HttpSession)request.getSession();
		// 로그아웃시 세션날리기
		session.invalidate();
		
		return resultMap;
	}
	
	// 회원가입
	@PostMapping(value = "/insertRegister", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> insertRegister(HttpServletRequest request, User user) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("SUCCESS", true);
		HttpSession session = (HttpSession)request.getSession();
		// 회원가입동의내용 적용
		user.setCheck1((String)session.getAttribute("check1"));
		user.setCheck2((String)session.getAttribute("check2"));
		user.setCheck3((String)session.getAttribute("check3"));
		user.setCheck4((String)session.getAttribute("check4"));
		
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
		
		String ext1 = user.getFlUpFileData().getOriginalFilename().substring(user.getFlUpFileData().getOriginalFilename().lastIndexOf(".") + 1);
		String ext2 = user.getFlUpFileData3().getOriginalFilename().substring(user.getFlUpFileData3().getOriginalFilename().lastIndexOf(".") + 1);
		
		if ( imageExt(ext1.trim()) && imageExt(ext2.trim())) {
			File file1 = Paths.get(materFilePath, user.getFlUpFileData().getOriginalFilename()).toFile();
			//File file2 = Paths.get(materFilePath, user.getFlUpFileData2().getOriginalFilename()).toFile();
			File file3 = Paths.get(materFilePath, user.getFlUpFileData3().getOriginalFilename()).toFile();

			String newFileName = "";
			String ext = "";
			try {
	            // TODO: 파일경로(filePath) 유효성 처리 추가 - 필요시
				
	            if (!file1.exists()) {
	            	user.getFlUpFileData().transferTo(file1);
	            	
	            	newFileName = user.getMtriCustNo() + "_" +today.format(System.currentTimeMillis()) +"(1)";
	            	ext = user.getFlUpFileData().getOriginalFilename().substring(user.getFlUpFileData().getOriginalFilename().lastIndexOf(".") + 1);
	            	
	            	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
	        		boolean isRename = file1.renameTo(newFile);
	        		if(isRename) {
	        			paramMap.put("SUCCESS", true);
	        			user.setRprsCrnoAttflNm(newFileName+"."+ext);
	        			user.setRprsCrnoAttflPath(materFilePath);
	        		} else {
	        			paramMap.put("SUCCESS", false);
	        		}
	            }
	            
//		            if (!file2.exists()) {
//		            	user.getFlUpFileData2().transferTo(file2);
//		            	
//		            	newFileName = user.getMtriCustNo() + "_" +today.format(System.currentTimeMillis()) +"(2)";
//		            	ext = user.getFlUpFileData2().getOriginalFilename().substring(user.getFlUpFileData2().getOriginalFilename().lastIndexOf(".") + 1);
	//
//		            	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
//		        		boolean isRename = file2.renameTo(newFile);
//		        		if(isRename) {
//		        			paramMap.put("SUCCESS", true);
//		        			user.setMnpbRgsrSeqAttflNm(newFileName+"."+ext);
//		        			user.setMnpbRgsrSeqAttflPath(materFilePath);
//		        		} else {
//		        			paramMap.put("SUCCESS", false);
//		        		}
//		            	
//		            }
	            
	            if (!file3.exists()) {
	            	user.getFlUpFileData3().transferTo(file3);
	            	
	            	newFileName = user.getMtriCustNo() + "_" +today.format(System.currentTimeMillis()) +"(3)";
	            	ext = user.getFlUpFileData3().getOriginalFilename().substring(user.getFlUpFileData3().getOriginalFilename().lastIndexOf(".") + 1);
	            	
	            	File newFile = new File(materFilePath+"/"+newFileName+"."+ext);
	        		boolean isRename = file3.renameTo(newFile);
	        		if(isRename) {
	        			paramMap.put("SUCCESS", true);
	        			user.setContractAttflNm(newFileName+"."+ext);
	        			user.setContractAttflPath(materFilePath);
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
				if(user.getFlUpFileData() == null && user.getFlUpFileData().getOriginalFilename().equals("") ) {
					user.setRprsCrnoAttflNm("");
					user.setRprsCrnoAttflPath("");
				}
				
//					if(user.getFlUpFileData2() == null && user.getFlUpFileData2().getOriginalFilename().equals("") ) {
//						user.setMnpbRgsrSeqAttflNm("");
//						user.setMnpbRgsrSeqAttflPath("");
//					}
				
				if(user.getFlUpFileData3() == null && user.getFlUpFileData3().getOriginalFilename().equals("") ) {
					user.setContractAttflNm("");
					user.setContractAttflPath("");
				}
				
				//비밀번호 암호화
				user.setCustPswd(strEncode(user.getCustPswd()));
		    	
				int result = userService.insertRegister(user);

				try {
					if ( result < 1 ) {
						paramMap.put("SUCCESS", false);
						session.invalidate();
						System.out.println("session clear====");
					}
				} catch (Exception e) {
					throw e;
				}
				
				if( paramMap.get("SUCCESS").equals(false) ) {
					// 오류시  INSERT 데이터 삭제
					userService.deleteRegister(user);
				}
			}
			
		} else {
			paramMap.put("SUCCESS", false);
    		paramMap.put("resultMsg", "이미지 또는 pdf파일만 업로드가능합니다.");
		}
		
		return paramMap;
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
	
	
	
