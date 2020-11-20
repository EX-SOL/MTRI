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


//첨부파일 다운로드 
function f_downloadFile() {
	var attflNm = $('#attflNm').val();
	var attflPath = $('#attflPath').val();
	var param = '?attflPath='+attflPath+'&attflNm='+attflNm;
	if( attflNm != "" && attflPath != "" ){
		location.href = "/mater/mater/downloadFile"+param;
	}
}