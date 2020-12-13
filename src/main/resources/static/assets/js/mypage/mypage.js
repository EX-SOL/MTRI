/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	$(".progressDiv").show();
	
	f_selectMyPage();
})

// 조회
function f_selectMyPage() {
	$(".progressDiv").show();
	var mtriCustNo = $("#s_mtriCustNo").val();
	
	var param = {"mtriCustNo" : mtriCustNo};
	$.ajax({
        method: "POST",
        url: "/mater/mypage/selectMypage",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log("response: ", response);
        	console.log("response11: ", response.custAthrNm);
            	$('#custAthrNm').val(response.custAthrNm);		// 직원유형
            	$('#custNm').val(response.custNm);				// 성명(업체명)
            	$('#mtriCustNo').val(response.mtriCustNo);		// 사용자 ID
            	$('#custTelno').val(response.custTelno);		// 연락처
            	$('#corpNm').val(response.corpNm);				// 법인명
            	$('#rpprNm').val(response.rpprNm);				// 대표자명
            	$('#rprsCrno').val(response.rprsCrno);			// 사업자등록번호
            	$('#deprNm').val(response.deprNm);				// 예금주
            	$('#trBankNm').val(response.trBankNm);			// 은행명
            	$('#bankActno').val(response.bankActno);		// 계좌번호
            	$('#korDptnm').val(response.korDptnm);			// 현장명
            	$('#wkscNm').val(response.wkscNm);				// 공구명
            	
            	$('input[name="custAthrNm"]').val(response.custAthrNm);		// 직원유형
            	$('input[name="custNm"]').val(response.custNm);				// 성명(업체명)
            	$('input[name="mtriCustNo"]').val(response.mtriCustNo);		// 사용자 ID
            	$('input[name="custTelno"]').val(response.custTelno);		// 연락처
            	$('input[name="corpNm"]').val(response.corpNm);				// 법인명
            	$('input[name="rpprNm"]').val(response.rpprNm);				// 대표자명
            	$('input[name="rprsCrno"]').val(response.rprsCrno);			// 사업자등록번호
            	$('input[name="deprNm"]').val(response.deprNm);				// 예금주
            	$('input[name="trBankNm"]').val(response.trBankNm);			// 은행명
            	$('input[name="bankActno"]').val(response.bankActno);		// 계좌번호
            	$('input[name="oldPswd"]').val(response.custPswd);			// 비밀번호
            	$('input[name="newPswd"]').val(response.custPswd);			// 비밀번호
            	$('input[name="korDptnm"]').val(response.korDptnm);			// 현장명
            	$('input[name="wkscNm"]').val(response.wkscNm);				// 공구명
            	
            	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("조회 실패");
        }
    });
}

// 수정시 비밀번호 확인
function f_goModify(){
	$("#pwCheckModal").show();
}

function f_modalClose(){
	$("#pswd").val("");
	$("#pwCheckModal").hide();
}

// 비밀번호 확인
function f_pswdCheck(){
	$(".progressDiv").show();

	var mtriCustNo = $("#s_mtriCustNo").val();
	var pswd = $("#pswd").val();
	
	if(pswd==null || pswd==""){
		f_showModal("비밀번호를 입력해주세요.");
		$(".progressDiv").hide();
		return;
	}
	var param = {"pswd":pswd, "mtriCustNo": mtriCustNo};
	$.ajax({
        method: "POST",
        url: "/mater/mypage/selectMyPassword",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	if(response.SUCCESS == true){
        		f_modalClose();
        		$("#detail-page").hide();
        		$("#modify-page").show();
        	} else {
        		f_showModal("비밀번호가 일치하지않습니다.");
        	}
        	
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("조회 실패");
        }
    });
}


// 수정페이지에서 뒤로가기
function f_goBack(){
	$("#detail-page").show();
	$("#modify-page").hide();
}

// 수정사항 저장시
function f_modifySave(){
	$(".progressDiv").show();
	
	var mtriCustNo = $("input[name='mtriCustNo']").val();
	var oldPswd = $("input[name='oldPswd']").val();
	var newPswd = $("input[name='newPswd']").val();
	var custTelno = $("input[name='custTelno']").val();
	var rpprNm = $("input[name='rpprNm']").val();
	var deprNm = $("input[name='deprNm']").val();
	var trBankNm = $("input[name='trBankNm']").val();
	var bankActno = $("input[name='bankActno']").val();
	var s_custAthrCd = $("#s_custAthrCd").val();
	
	if(oldPswd == null || oldPswd == ""){
		f_showModal("비밀번호를 입력해주세요.");
//		$("input[name='oldPswd']").focus();
		$(".progressDiv").hide();
		return;
	} else if (oldPswd.length < 8){
		f_showModal("비밀번호를 8자리 이상으로 입력해주세요.");
//		$("input[name='oldPswd']").focus();
		$(".progressDiv").hide();
		return;
	} else if (newPswd == null || newPswd == ""){
		f_showModal("비밀번호를 입력해주세요.");
//		$("input[name='newPswd']").focus();
		$(".progressDiv").hide();
		return;
	} else if (oldPswd != newPswd) {
		f_showModal("비밀번호가 일치하지않습니다.");
//		$("input[name='newPswd']").focus();
		$(".progressDiv").hide();
		return;
	} 
	
	if(custTelno == null || custTelno ==""){
		f_showModal("연락처를 입력해주세요.");
//		$("input[name='custTelno']").focus();
		$(".progressDiv").hide();
		return;
	}
	custTelno = f_hyphenReplaceEmpty(custTelno);

	// 일반사용자일 경우
	if(s_custAthrCd == "D"){
		if(rpprNm == null || rpprNm ==""){
			f_showModal("대표자명을 입력해주세요.");
//			$("input[name='rpprNm']").focus();
			$(".progressDiv").hide();
			return;
		}
		if(deprNm == null || deprNm ==""){
			f_showModal("예금주명을 입력해주세요.");
//			$("input[name='deprNm']").focus();
			$(".progressDiv").hide();
			return;
		}
		if(trBankNm == null || trBankNm ==""){
			f_showModal("은행명을 입력해주세요.");
//			$("input[name='trBankNm']").focus();
			$(".progressDiv").hide();
			return;
		}
		if(bankActno == null || bankActno ==""){
			f_showModal("계좌번호를 입력해주세요.");
//			$("input[name='bankActno']").focus();
			$(".progressDiv").hide();
			return;
		}
		bankActno = f_hyphenReplaceEmpty(bankActno);
	}
	
	var param = {"mtriCustNo": mtriCustNo
				, "oldPswd": oldPswd
				, "custTelno": custTelno
				, "rpprNm" : rpprNm
				, "deprNm" : deprNm
				, "trBankNm" : trBankNm
				, "bankActno" : bankActno}

	$.ajax({
        method: "POST",
        url: "/mater/mypage/updateMypage",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	if(response.SUCCESS == true){
        		f_showModal("정상적으로 수정 되었습니다.");
        		$("#detail-page").show();
        		$("#modify-page").hide();
        		f_selectMyPage();
        	} else {
        		f_showModal("수정 중 오류가 발생하였습니다.");
        	}
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("수정 중 오류가 발생하였습니다.");
        }
    });
}

function f_remove(){
	f_confirm("정말 탈퇴하시겠습니까?", "f_deleteUser();");
}

function f_deleteUser(){
	$(".progressDiv").show();
	var mtriCustNo = $("#s_mtriCustNo").val();
	var param = {"mtriCustNo" : mtriCustNo};
	
	$.ajax({
        method: "POST",
        url: "/mater/mypage/deleteMypage",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	if(response.SUCCESS == true){
        		f_showModal_func("정상적으로 탈퇴 되었습니다.", "f_goMaterPage()");
        		
        	} else {
        		f_showModal("탈퇴 중 오류가 발생하였습니다.");
        	}
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("탈퇴 중 오류가 발생하였습니다.");
        }
    });
}

function f_goMaterPage(){
	window.location = "/mater";
}
