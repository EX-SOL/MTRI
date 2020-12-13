/**
 * 
 */
$(document).ready(function(){
	
	// 처음 체크박스 모두 비동의로 세팅
	$("#check1_N").prop("checked", true);
	$("#check2_N").prop("checked", true);
	$("#check3_N").prop("checked", true);
	$("#check4_N").prop("checked", true);
	$("#check1_Y").prop("checked", false);
	$("#check2_Y").prop("checked", false);
	$("#check3_Y").prop("checked", false);
	$("#check4_Y").prop("checked", false);
	$("#allCheck").prop("checked", false);
	
	//모두 동의시
	$("#allCheck").on('click', function(){
		if( $("#allCheck").is(":checked") == true ){
			$("#check1_Y").prop("checked", true);
			$("#check2_Y").prop("checked", true);
			$("#check3_Y").prop("checked", true);
			$("#check4_Y").prop("checked", true);
			$("#check1_N").prop("checked", false);
			$("#check2_N").prop("checked", false);
			$("#check3_N").prop("checked", false);
			$("#check4_N").prop("checked", false);
		}
	});
	//선택1
	$("#check1_Y").on('click', function(){
		if( $("#check1_Y").is(":checked") == true ){
			$("#check1_N").prop("checked", false);
		}else{
			$("#check1_N").prop("checked", true);
		}
	});
	$("#check1_N").on('click', function(){
		if( $("#check1_N").is(":checked") == true ){
			$("#check1_Y").prop("checked", false);
			//모두동의 해제
			$("#allCheck").prop("checked", false);
		}else{
			$("#check1_Y").prop("checked", true);
		}
	});
	
	//선택2
	$("#check2_Y").on('click', function(){
		if( $("#check2_Y").is(":checked") == true ){
			$("#check2_N").prop("checked", false);
		}else{
			$("#check2_N").prop("checked", true);
		}
	});
	$("#check2_N").on('click', function(){
		if( $("#check2_N").is(":checked") == true ){
			$("#check2_Y").prop("checked", false);
			//모두동의 해제
			$("#allCheck").prop("checked", false);
		}else{
			$("#check2_Y").prop("checked", true);
		}
	});
	
	//선택3
	$("#check3_Y").on('click', function(){
		if( $("#check3_Y").is(":checked") == true ){
			$("#check3_N").prop("checked", false);
		}else{
			$("#check3_N").prop("checked", true);
		}
	});
	$("#check3_N").on('click', function(){
		if( $("#check3_N").is(":checked") == true ){
			$("#check3_Y").prop("checked", false);
			//모두동의 해제
			$("#allCheck").prop("checked", false);
		}else{
			$("#check3_Y").prop("checked", true);
		}
	});
	
	//선택4
	$("#check4_Y").on('click', function(){
		if( $("#check4_Y").is(":checked") == true ){
			$("#check4_N").prop("checked", false);
		}else{
			$("#check4_N").prop("checked", true);
		}
	});
	$("#check4_N").on('click', function(){
		if( $("#check4_N").is(":checked") == true ){
			$("#check4_Y").prop("checked", false);
			//모두동의 해제
			$("#allCheck").prop("checked", false);
		}else{
			$("#check4_Y").prop("checked", true);
		}
	});
	
})

// 로그인버튼 클릭시
function f_goLogin(){
	var id = $("#mtriCustNo").val();
	var pw = $("#custPswd").val();
	
	if(id == "" || id == null){
		f_showModal("아이디를 입력해주세요.");
		return;
	}
	
	if(pw == "" || pw == null){
		f_showModal("비밀번호를 입력해주세요.");
		return;
	}
	
	var param = {"mtriCustNo" : id, "custPswd" : pw};
	
	$.ajax({
        method: "POST",
        url: "/mater/user/login",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	if(response.SUCCESS == true){
        		window.location = "/mater/main/load-page?pageName=main/main";
        	} else {
        		f_showModal("로그인 실패");
        	}
        },
        error: function (jqXHR, status, error) {
        	f_showModal("로그인 실패");
        }
    });
	
}

// 회원가입
function f_goRegister(){
	window.location = "/mater/main/load-page?pageName=intro/registerAgree";
}

//개인정보제공동의 본인인증버튼 클릭시
function f_goCerti(){
	var check1 = $("#check1_Y").is(":checked");
	var check2 = $("#check2_Y").is(":checked");
	var check3 = $("#check3_Y").is(":checked");
	var check4 = $("#check4_Y").is(":checked");

	if(check1 != true || check2 != true || check4 != true){
		f_showModal("필수항목은 동의하셔야 가입이 가능합니다.");
		return;
	}
	if(check1 == true){check1="Y";}else{check1="N";};
	if(check2 == true){check2="Y";}else{check2="N";};
	if(check3 == true){check3="Y";}else{check3="N";};
	if(check4 == true){check4="Y";}else{check4="N";};
	window.location = "/mater/intro/checkplus_main?check1="+check1+"&check2="+check2+"&check3="+check3+"&check4="+check4;
}

//개인정보이용약관 동의
function f_modal1(){
	var agree_1_v = $("input[name='agree_1_v']").val();
	if( agree_1_v == "Y" ){
		$("input[name='agree_1_v']").val("N");
		$("#agree_1_icon").html('<i class="fas fa-sort-down"></i>');
		$("#agree_1").hide();
	} else {
		$("input[name='agree_1_v']").val("Y");
		$("#agree_1_icon").html('<i class="fas fa-sort-up"></i>');
		$("#agree_1").show();
	}
}

//개인정보 수집 및 이용에 동의
function f_modal2(){
	var agree_2_v = $("input[name='agree_2_v']").val();
	if( agree_2_v == "Y" ){
		$("input[name='agree_2_v']").val("N");
		$("#agree_2_icon").html('<i class="fas fa-sort-down"></i>');
		$("#agree_2").hide();
	} else {
		$("input[name='agree_2_v']").val("Y");
		$("#agree_2_icon").html('<i class="fas fa-sort-up"></i>');
		$("#agree_2").show();
	}
}

//아이디찾기
function f_goIdFind(){
	window.location = "/mater/main/load-page?pageName=intro/idFind";
}


//비밀번호찾기
function f_goPwFind(){
	window.location = "/mater/main/load-page?pageName=intro/pwFind";
}