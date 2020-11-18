/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").hide();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
})

// 장비대금 등록
function f_save(){
	var mnpbAskYYMM = $("select[name='mrtiMnpbAskYY']").val() + $("select[name='mrtiMnpbAskMM']").val();
	$("input[name='mnpbAskYYMM']").val(mnpbAskYYMM);
	
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
        	
        },
        error: function (jqXHR, status, error) {
        	f_showModal("등록에 실패하였습니다.");
        }
    });
}