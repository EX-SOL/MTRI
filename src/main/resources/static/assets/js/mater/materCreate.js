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


function f_save(){
	
	var mrtiMnpbAskYYMM = $("select[name='mrtiMnpbAskYY']").val() + $("select[name='mrtiMnpbAskMM']").val();
	$("input[name='mrtiMnpbAskYYMM']").val(mrtiMnpbAskYYMM);
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
        	}else {
        		f_showModal("등록에 실패하였습니다.");
        	}
        	
        },
        error: function (jqXHR, status, error) {
        	f_showModal("등록에 실패하였습니다.");
        }
    });
}

