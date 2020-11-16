/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	f_selectNotice();
})


function f_selectNotice(){
	var innerHTML = '';
	innerHTML += '<tr>';
	innerHTML += '	<td>1</td>';
	innerHTML += '	<td onclick="javascript:f_goNoticeDetail();">자재관리 관련 공지사항</td>';
	innerHTML += '	<td>2020-10-30</td>';
	innerHTML += '</tr>';
	innerHTML += '<tr>';
	innerHTML += '	<td>2</td>';
	innerHTML += '	<td onclick="javascript:f_goNoticeDetail();">공지사항</td>';
	innerHTML += '	<td>2020-10-29</td>';
	innerHTML += '</tr>';
	$("#noticeList").html(innerHTML);
}

function f_goNoticeDetail(){
	window.location = "/mater/main/load-page?pageName=notice/noticeDetail";
}

function f_goNoticeCreate(){
	window.location = "/mater/main/load-page?pageName=notice/noticeCreate";
}
