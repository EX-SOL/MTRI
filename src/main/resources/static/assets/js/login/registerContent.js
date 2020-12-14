/**
 * 
 */
$(document).ready(function(){
})

//id중복체크
function f_idCheck(){
	var mtriCustNo = $("#mtriCustNo").val();
	if(mtriCustNo == "" || mtriCustNo == null){
		f_showModal("아이디를 입력해주세요.");
		return;
	}
	var param = { "mtriCustNo" : mtriCustNo };
	$.ajax({
        method: "POST",
        url: "/mater/user/idCheck",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	var result = parseInt(response.CNT)

        	if( result > 0 ){
        		f_showModal("존재하는 아이디입니다. 다시 입력해주세요.");
        		//$("#mtriCustNo").focus();
        		$("#mtriCustNoYn").val("N");
        	} else {
        		f_showModal("사용가능한 아이디입니다.");
        		$("#mtriCustNo").attr("readonly", true);
        		$("#mtriCustNoYn").val("Y");
        	}
        },
        error: function (jqXHR, status, error) {
        	f_showModal("중복확인 실패");
        }
    });
}

//사업자등록번호 중복체크
function f_rprsCrnoCheck(){
	var rprsCrno = $("#rprsCrno").val();
	if(rprsCrno == "" || rprsCrno == null){
		f_showModal("사업자등록번호를 입력해주세요.");
		return;
	}
	
	rprsCrno = f_hyphenReplaceEmpty(rprsCrno);
	$("#rprsCrno").val(rprsCrno);
	
	var param = { "rprsCrno" : rprsCrno };
	$.ajax({
		method: "POST",
		url: "/mater/user/rprsCrnoCheck",
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(param),
		success: function (response, textStatus, jqXHR) {
			var result = parseInt(response.CNT)
			
			if( result > 0 ){
				f_showModal("이미 등록된 사업자등록번호입니다. 다시 입력해주세요.");
				//$("#rprsCrno").focus();
				$("#rprsCrnoYn").val("N");
			} else {
				f_showModal("사용가능한 사업자등록번호입니다.");
				$("#rprsCrno").attr("readonly", true);
				$("#rprsCrnoYn").val("Y");
			}
		},
		error: function (jqXHR, status, error) {
			f_showModal("중복확인 실패");
		}
	});
}

// 사용자등록
function f_saveData(){
	$(".progressDiv").show();
	//사용자id
	var mtriCustNoYn = $("#mtriCustNoYn").val();
	if(mtriCustNoYn != "Y"){
		f_showModal("사용자ID 중복확인을 해주세요.");
		$(".progressDiv").hide();
		return;
	}
	
	//비밀번호
	var custPswd = $("#custPswd").val();
	var custPswd2 = $("#custPswd2").val();
	if(custPswd == "" || custPswd == null){
		f_showModal("비밀번호를 입력해주세요.");
//		$("#custPswd").focus();
		$(".progressDiv").hide();
		return;
	}else if(custPswd.length < 8){
		f_showModal("비밀번호는 8자리 이상입니다.");
//		$("#custPswd").focus();
		$(".progressDiv").hide();
		return;
	}else if( custPswd != custPswd2 ){
		f_showModal("비밀번호와 비밀번호 확인이 맞지않습니다.");
//		$("#custPswd2").focus();
		$(".progressDiv").hide();
		return;
	}

	//법인명
	var corpNm = $("#corpNm").val();
	if(corpNm == "" || corpNm == null){
		f_showModal("법인명을 입력해주세요.");
//		$("#corpNm").focus();
		$(".progressDiv").hide();
		return;
	}
	
	//대표자명
	var rpprNm = $("#rpprNm").val();
	if(rpprNm == "" || rpprNm == null){
		f_showModal("대표자명을 입력해주세요.");
//		$("#rpprNm").focus();
		$(".progressDiv").hide();
		return;
	}
	
	//사업자등록증
	var rprsCrno = $("#rprsCrno").val();
	if(rprsCrno == "" || rprsCrno == null){
		f_showModal("사업자등록번호를 입력해주세요.");
//		$("#rprsCrno").focus();
		$(".progressDiv").hide();
		return;
	}else if(rprsCrno.length>10){
		f_showModal("사업자등록번호가 10자리를 초과했습니다.");
		$(".progressDiv").hide();
		return;
	}
	
	var file1 = $("#fileName").text();
	if(file1 == "" || file1 == null){
		f_showModal("사업자등록증 첨부파일은 필수입니다.");
		$(".progressDiv").hide();
		return;
	}
	
	//장비등록증
	/*var mtriEqpmClssNm = $("#mtriEqpmClssNm").val();
	if(mtriEqpmClssNm == "" || mtriEqpmClssNm == null){
		f_showModal("장비명을 입력해주세요.");
		$(".progressDiv").hide();
		return;
	}
	var mnpbRgsrSeqYn = $("#mnpbRgsrSeqYn").val();
	if(mnpbRgsrSeqYn != "Y"){
		f_showModal("장비등록번호 중복확인을 해주세요.");
		$(".progressDiv").hide();
		return;
	}
	var file2 = $("#fileName2").text();
	if(file2 == "" || file2 == null){
		f_showModal("장비등록증 첨부파일은 필수입니다.");
		$(".progressDiv").hide();
		return;
	}*/
	
	//예금주
	//은행명
	//계좌번호
	var deprNm = $("#deprNm").val();
	var trBankNm = $("#trBankNm").val();
	var bankActno = $("#bankActno").val();
	
	if( deprNm == "" || deprNm == null ){
		f_showModal("예금주명을 입력해주세요.");
//		$("#deprNm").focus();
		$(".progressDiv").hide();
		return;
	}
	if( trBankNm == "" || trBankNm == null ){
		f_showModal("은행명을 입력해주세요.");
//		$("#trBankNm").focus();
		$(".progressDiv").hide();
		return;
	}
	if( bankActno == "" || bankActno == null ){
		f_showModal("계좌번호를 입력해주세요.");
//		$("#bankActno").focus();
		$(".progressDiv").hide();
		return;
	}
	bankActno = f_hyphenReplaceEmpty(bankActno);
	$("#bankActno").val(bankActno);
	
	var file3 = $("#fileName3").text();
	if(file3 == "" || file3 == null){
		f_showModal("계약서 첨부파일은 필수입니다.");
		$(".progressDiv").hide();
		return;
	}
	
	var frmData = new FormData($("#userForm")[0]);
	
	$.ajax({
        method: "POST",
        enctype: 'multipart/form-data',
        url: "/mater/user/insertRegister",
        async: true,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: frmData,
        success: function (response, textStatus, jqXHR) {
        	if (response.SUCCESS == true){
        		f_showModal_func("가입되었습니다.", "f_goMainPage()");
        	}else {
        		if(response.resultMsg != null){
        			f_showModal(response.resultMsg);
        		}else{
        			f_showModal("가입에 실패하였습니다.");
        		}
        	}
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("가입에 실패하였습니다.");
        }
    });
}

function f_goMainPage(){
	window.location = "/mater";
}

// 초기화작업
function f_resetData(){
	$("#mtriCustNo").val("");
	$("#mtriCustNo").attr("readonly", false);
	$("#mtriCustNoYn").val("N");
	
	$("#custPswd").val("");
	$("#custPswd2").val("");
	$("#corpNm").val("");
	$("#rpprNm").val("");
	$("#rprsCrno").val("");
	$("#mnpbRgsrSeq").val("");
	$("#deprNm").val("");
	$("#trBankNm").val("");
	$("#bankActno").val("");

	$("#flUpFileData").val("");
	$("#fileName").text("");
	
	$("#flUpFileData2").val("");
	$("#fileName2").text("");
	
	$("#flUpFileData3").val("");
	$("#fileName3").text("");
}