/**
 * 
 */
$(document).ready(function(){
	
	var userNm = $("#userNm").val();
	var userPhoneNo = $("#userPhoneNo").val();
	
	var sName = $("#sName").val();
	var sMobileNo = $("#sMobileNo").val();
	
	var userId = $("#userId").val();

	if(sName != null && sName != ""){
		if(userNm != "" && userPhoneNo != ""){
			if (userNm == sName && userPhoneNo == sMobileNo){
				var param = {"userNm" : userNm, "userPhoneNo" : userPhoneNo, "userId" : userId};
				
				$.ajax({
			        method: "POST",
			        url: "/mater/intro/selectIdFind",
			        contentType: 'application/json',
			        dataType: 'json',
			        data: JSON.stringify(param),
			        success: function (response, textStatus, jqXHR) {
			        	console.log(response);
			        	var innerHTML = '';
			        	if(response.length > 0){
			        		var sUserId = response[0].MTRI_CUST_NO;
			        		if( sUserId == userId ){
			        			$("#showPw-page").show();
			        			$("#pwBody-page").hide();
		        			}else{
				        		f_showModal_func("가입정보가 존재하지않습니다.", "f_goBackMain()");
		        			}
			        	} else {
			        		f_showModal_func("가입정보가 존재하지않습니다.", "f_goBackMain()");
			        	}
			        	
			        },
			        error: function (jqXHR, status, error) {
			        	f_showModal_func("조회 도중 오류가 발생하였습니다.", "f_goBackMain()");
			        }
			    });
			} else{
				f_showModal_func("입력하신 정보와 인증한 정보가 일치하지않습니다.", "f_goBackMain()");
				return;
			}
		}
	}
	
})

//본인인증 띄우러가기
function f_goCertiPopup(){
	var userId = $("#userId").val();
	var userNm = $("#userNm").val();
	var userPhoneNo = $("#userPhoneNo").val();
	
	if(userId == "" || userId == null){
		f_showModal("아이디를 입력해주세요.");
		return;
	}
	if(userNm == "" || userNm == null){
		f_showModal("이름을 입력해주세요.");
		return;
	}
	if(userPhoneNo == "" || userPhoneNo == null ){
		f_showModal("전화번호를 입력해주세요.");
		return;
	}
	
	userPhoneNo = f_hyphenReplaceEmpty(userPhoneNo);
	window.location = "/mater/intro/pwFind_Checkplus?userId="+userId+"&userNm="+userNm+"&userPhoneNo="+userPhoneNo;
}

// 비밀번호 저장
function f_pswdSave(){
	$(".progressDiv").show();
	
	var userId = $("#userId").val();
	var pswd = $("#pswd").val();
	var pswd2 = $("#newPswd").val();
	
	if(pswd == "" || pswd2 == ""){
		//$("#pswd").focus();
		$(".progressDiv").hide();
		f_showModal("비밀번호를 입력해주세요.");
		return;
	} else if(pswd.length < 8){
		//$("#pswd").focus();
		$(".progressDiv").hide();
		f_showModal("비밀번호는 8자리 이상입니다.");
		return;
	} else if( pswd != pswd2 ){
		//$("#pswd2").focus();
		$(".progressDiv").hide();
		f_showModal("비밀번호와 비밀번호 확인이 맞지않습니다.");
		return;
	}
	
	var param = {"userId" : userId, "newPswd":pswd};
	
	$.ajax({
        method: "POST",
        url: "/mater/intro/updateNewPw",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	var innerHTML = '';
        	if(response.SUCCESS == true){
        		f_showModal_func("비밀번호가 수정되었습니다.", "f_goBackMain()");
        	} else {
        		f_showModal("비밀번호 저장하는 도중 오류가 발생하였습니다.");
        	}
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("수정 오류");
        }
    });
}

//메인가기전 세션 털고가기
function f_goBackMain(){
	$.ajax({
        method: "POST",
        url: "/mater/intro/goBackMain",
        contentType: 'application/json',
        dataType: 'json',
        success: function (response, textStatus, jqXHR) {
        	window.location = "/mater";
        },
        error: function (jqXHR, status, error) {
        	window.location = "/mater";
        }
    });
}