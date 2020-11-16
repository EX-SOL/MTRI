/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
})

function f_goNotice(){
	
	window.location = "/mater/main/load-page?pageName=notice/notice";
}


function f_goMaterCreate(){
	window.location = "/mater/main/load-page?pageName=mater/materCreate";
}

function f_goMaterList(){
	window.location = "/mater/main/load-page?pageName=mater/materList";
}

function f_goNoticeDetail(){
	window.location = "/mater/main/load-page?pageName=notice/noticeDetail";
}

function f_goMachineryList(){
	window.location = "/mater/main/load-page?pageName=machinery/machineryList";
}

function f_goMypage(){
	window.location = "/mater/main/load-page?pageName=mypage/mypage";
}