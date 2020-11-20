/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").show();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	// 초기 데이터 설정
	var date = new Date();
	var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    $("input[name='d_mnpbAskYYMM']").val(year+"-"+month);
})

// 장비대금 등록
function f_save(){
	$(".progressDiv").show();
	var mnpbAskYYMM = $("input[name='d_mnpbAskYYMM']").val();
	var newMnpbAskYYMM = f_hyphenReplaceEmpty(mnpbAskYYMM);
	$("input[name='mnpbAskYYMM']").val(newMnpbAskYYMM);
	
	var askAmt = $("input[name='askAmt']").val();
	var newAskAmt = askAmt.replace(/,/gi, '');
	$("input[name='askAmt']").val(newAskAmt);
	
	var frmData = new FormData($('#materForm')[0]);
	
	$.ajax({
        method: "POST",
        enctype: 'multipart/form-data',
        url: "/mater/mater/insertMaterList",
        async: true,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: frmData,
        success: function (response, textStatus, jqXHR) {
        	if (response.SUCCESS == true){
        		f_showModal("등록되었습니다.");
        		
        		window.location = "/mater/main/load-page?pageName=mater/materList";
        	}else {
        		f_showModal("등록에 실패하였습니다.");
        	}
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("등록에 실패하였습니다.");
        }
    });
}