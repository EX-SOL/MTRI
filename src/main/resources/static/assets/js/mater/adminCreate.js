/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").show();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	f_selectAdmin();
	f_fildData();
})


// 관리자 목록 가져오기
function f_selectAdmin() {
	
	var sWkscNm = $("#sWkscNm").val();
	var param = {"sWkscNm" : sWkscNm};
	
	$.ajax({
        method: "POST",
        url: "/mater/mater/selectAdminList",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	var innerHTML = '';
        	
        	if(response.length > 0){
        		$.each(response, function(key, item){
        			var korDptnm = item.korDptnm;
        			if (korDptnm == null || korDptnm == ""){korDptnm = "";};
        			var wkscNm = item.wkscNm;
        			if (wkscNm == null || wkscNm == ""){wkscNm = "";};

        			innerHTML += '<tr>';
        			innerHTML += '	<td>'+(key+1)+'</td>';
        			innerHTML += '	<td>'+item.mtriCustNo+'</td>';
        			innerHTML += '	<td>'+korDptnm+'</td>';
        			innerHTML += '	<td>'+wkscNm+'</td>';
        			innerHTML += '	<td>'+item.custAthrCd+'</td>';
        			innerHTML += '	<td>'+item.acitLockYn+'</td>';
        			innerHTML += '</tr>';
        		});
        		
        	} else {
        		innerHTML += '<tr>';
    			innerHTML += '	<td colspan="6">조회된 목록이 없습니다.</td>';
    			innerHTML += '</tr>';
        	}
        	
        	$("#materList").html(innerHTML);
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("조회 실패");
        }
    });
}

//관리자등록
function f_showModalAdmin(){
	$("#adminCreateModal").show();
	$('body').attr("style", "overflow:hidden;");
}

//팝업 종료
function f_modalClose(){
	$("#adminCreateModal").hide();
	$('body').attr("style", "overflow:auto;");
	
	//data clean
	$("#mtriCustNoYn").val("N");
	$("#newMtriCustNo").val("");
	$("#custPswd").val("");
	$("#custNm").val("");
	$("#newMtriCustNo").attr("readonly", false);
}

//id중복체크
function f_idCheck(){
	var mtriCustNo = $("#newMtriCustNo").val();
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
        		//$("#newMtriCustNo").focus();
        		$("#mtriCustNoYn").val("N");
        	} else {
        		f_showModal("사용가능한 아이디입니다.");
        		$("#newMtriCustNo").attr("readonly", true);
        		$("#mtriCustNoYn").val("Y");
        	}
        },
        error: function (jqXHR, status, error) {
        	f_showModal("중복확인 실패");
        }
    });
}

//관리자등록
function f_goAdminCreate(){
	var mtriCustNoYn = $("#mtriCustNoYn").val();
	if(mtriCustNoYn != "Y"){
		f_showModal("아이디 중복확인을 해주세요.");
		$(".progressDiv").hide();
		return;
	}
	
	var custPswd = $("#custPswd").val();
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
	}
	
	var custNm = $("#custNm").val();
	if(custNm == "" || custNm == null){
		f_showModal("성명을 입력해주세요.");
//		$("#custNm").focus();
		$(".progressDiv").hide();
		return;
	}
	
	var newMtriCustNo = $("#newMtriCustNo").val();
	var mtriCustNo = $("#mtriCustNo").val();
	var blngDptcd = $("select[name='blngDptcd']").val();
	var wkscCd = $("select[name='wkscCd']").val();
	var custAthrCd = $("select[name='custAthrCd']").val();
	//관리자일 경우 현장/공구 빈값
	if(custAthrCd == "A"){
		blngDptcd = "";
		wkscCd ="";
	}
	
	var param = {"mtriCustNo" : mtriCustNo
				, "newMtriCustNo" : newMtriCustNo
				, "custPswd" : custPswd
				, "custNm" : custNm
				, "blngDptcd" : blngDptcd
				, "wkscCd" : wkscCd
				, "custAthrCd" : custAthrCd}
	$.ajax({
		method: "POST",
		url: "/mater/mater/createAdmin",
		contentType: 'application/json',
		dataType: 'json',
		data: JSON.stringify(param),
		success: function (response, textStatus, jqXHR) {
			if(response.SUCCESS == true){
				f_showModal("등록되었습니다.");
				f_modalClose();
				f_selectAdmin();
			}
		},
		error: function (jqXHR, status, error) {
			f_showModal("중복확인 실패");
		}
	});
}

//관리자일경우 현장/공구 선택 X
function f_changeAthrCd(){
	var custAthrCd = $("select[name='custAthrCd']").val();
	if(custAthrCd == "A"){
		$("#blng-page").hide();
		$("#wksc-page").hide();
	} else {
		$("#blng-page").show();
		$("#wksc-page").show();
		
	}
}