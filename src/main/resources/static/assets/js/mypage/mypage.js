/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	SelectMyPage();
})

// 수정
function UpdateMyPage() {
	console.log("수정");
	var param = {
		"custPswd": $('#custPswd').val(),
		"custEmailAddr": $('#custEmailAddr').val(),
		"custTelno": $('#custTelno').val(),
		"mtriCustNo": $('#mtriCustNo').val()
	}

	console.log("update Param : ", param);
	
	$.ajax({
        method: "POST",
        url: "/mater/mypage/updateMypage",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	if(response.SUCCESS == true){
        		f_showModal("정상적으로 수정 되었습니다.");
        	} else {
        		f_showModal("변경 실패");
        	}
        },
        error: function (jqXHR, status, error) {
        	f_showModal("변경 실패");
        }
    });

}

// 조회
function SelectMyPage() {
	var param = {"mtriCustNo":"1058657078"};
	console.log("param : ", param);
	$.ajax({
        method: "POST",
        url: "/mater/mypage/selectMypage",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log("Response : ", response);
        	console.log("Response.length : ", response.length);
        	if(response.length > 0){
            	$('#custAthrNm').val(response[0]["custAthrNm"]);		// 직원유형
            	$('#custNm').val(response[0]["custNm"]);				// 성명(업체명)
            	$('#mtriCustNo').val(response[0]["mtriCustNo"]);		// 사용자 ID
            	$('#custPswd').val(response[0]["custPswd"]);			// 비밀번호
            	$('#custPswdCnfm').val(response[0]["custPswd"]);		// 비밀번호 확인
            	$('#custEmailAddr').val(response[0]["custEmailAddr"]);	// 이메일
            	$('#custTelno').val(response[0]["custTelno"]);			// 연락처
            	$('#fsttmRgstDttm').val(response[0]["fsttmRgstDttm"]);	// 가입일자
        	} else {
        		f_showModal("조회 실패");
        	}
        },
        error: function (jqXHR, status, error) {
        	f_showModal("조회 실패");
        }
    });
}