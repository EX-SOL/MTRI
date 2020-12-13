/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');

	// 초기 데이터 설정
    f_monthSetting("#sDate", "#eDate");
	f_selectNotice();
})

// 공지사항 상세 조회 화면 이동
function goNoticeDetail(notcMtriSeq) {

	window.location = "/mater/notice/selectNoticeDetail?notcMtriSeq="+notcMtriSeq;
}

// 공지사항 조회
function f_selectNotice() {
	$(".progressDiv").show();
	var sDate = $("#sDate").val();
	var eDate = $("#eDate").val();
	var sTitleData = $("#sTitleData").val();
	
	sDate = sDate.replace(/-/gi, '');
	eDate = eDate.replace(/-/gi, '');
	var param = {"sDate":sDate, "eDate":eDate, "sTitleData":sTitleData, "notcMtriBltnAthrCd":""};
	
	$.ajax({
        method: "POST",
        url: "/mater/notice/selectNotice",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	var innerHTML = '';
        	
        	if(response.length > 0){
        		$.each(response, function(key, item){
        			innerHTML += '<tr>';
        			innerHTML += '	<td>'+ (key+1) +'</td>';
        			innerHTML += '	<td onclick="javascript:f_goNoticeDetail('+item.notcMtriSeq+');">'+item.notcMtriTitlNm+'</td>';
        			innerHTML += '	<td>'+item.fsttmRgstDttm+'</td>';
        			innerHTML += '</tr>';
        		});
        	}else {
        		innerHTML += '<tr>';
        		innerHTML += '	<td colspan="3">조회된 내역이 없습니다.</td>';
        		innerHTML += '</tr>';
        	}
        	
        	$("#noticeList").html(innerHTML);
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("조회 실패");
        }
    });
}


function f_goNoticeDetail(notcMtriSeq){
	window.location = "/mater/notice/selectNoticeDetail?notcMtriSeq="+notcMtriSeq;
}

function f_goNoticeCreate(){
	window.location = "/mater/main/load-page?pageName=notice/noticeCreate";
}
