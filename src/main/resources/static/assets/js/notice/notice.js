/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');

	selectNotice();
})

// 공지사항 상세 조회 화면 이동
function goNoticeDetail(notcMtriSeq) {

	//window.location = "/mater/main/load-page?pageName=notice/noticeDetail?notcMtriSeq="+notcMtriSeq;
	//window.location = "/mater/main/load-page?pageName=notice/noticeDetail";
	window.location = "/mater/notice/selectNoticeDetail?notcMtriSeq="+notcMtriSeq;
}

// 공지사항 조회
function selectNotice() {

	console.log('sDate : ', $('#sDate').val());
	console.log('eDate : ', $('#eDate').val());
	
	var sDate = $('#sDate').val();
	var eDate = $('#eDate').val();
	
	if(sDate > eDate) {
		f_showModal('종료일은 시작일 이후로 가능합니다.');
		return;
	}

	var param = {
		"notcMtriTitlNm": $('#notcMtriTitlNm').val(),
		"bltnStrtDates": $('#sDate').val().replace(/\//g, ''),
		"bltnEndDates": $('#eDate').val().replace(/\//g, ''),
		"notcMtriTitlNm": $('#notcMtriTitlNm').val()
	};
	console.log("param : ", param);
	$.ajax({
        method: "POST",
        url: "/mater/notice/selectNotice",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {

        	
    	    var trtd = "";
    	    console.log("Pre trtd : ", trtd);
        	$("#noticeList").empty();

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

            	$.each(response, function(key, item){
    	    		
    	    		//등록날짜 
    	    		var dateRgst = new Date(item.fsttmRgstDttm);
    				var yyRgst = dateRgst.getFullYear();
    				var mmRgst = dateRgst.getMonth()+1;
    				var ddRgst = dateRgst.getDate();
    				if(mmRgst<10){mmRgst='0'+mmRgst}
    				if(ddRgst<10){ddRgst='0'+ddRgst}
    				var rgstDate = yyRgst+'-'+mmRgst+'-'+ddRgst;
    				
    				//N 게시물 표시를 위한 비교 날짜 
    				var dateNew = new Date();
    				dateNew.setDate(dateNew.getDate()-5);
    				var yyNew = dateNew.getFullYear();
    				var mmNew = dateNew.getMonth()+1;
    				var ddNew = dateNew.getDate();
    				if(mmNew<10){mmNew='0'+mmNew}
    				if(ddNew<10){ddNew='0'+ddNew}
    				var compareNewDate = yyNew+'-'+mmNew+'-'+ddNew;
    				
    				trtd += '<tr id="notice-list-tr-'+item.notcMtriSeq+'" onClick="goNoticeDetail(\''+item.notcMtriSeq+'\')">';
    				trtd += '	<td style="width: 15%;">'+(key+1)+'</td>';
    				
    				if(item.notcMtriImptYN == 'Y') {
    					trtd += '	<td style="width: 55%; text-align: left; padding-left: 7px;">';
    					trtd += '	<span style="color: #ff0000;">[중요] </span>';
    					trtd += '	<span>'+item.notcMtriTitlNm+'</span>';
    				} else {
    					trtd += '	<td style="width: 55%; text-align: left; padding-left: 7px;">';
    					trtd += '	<span style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">'+item.notcMtriTitlNm+'</span>';
    				}
    				
    				if(compareNewDate <= rgstDate) {
    					trtd += '	<img src="../assets/img/icon_new.png" style="width: 8%; margin-left: 10px;">';
    				}
    				
    				trtd += '	</td>';
    				trtd += '	<td style="width: 30%;">'+rgstDate+'</td>';
    				trtd += '</tr>';
    				
    	        });
        	} else {
        		f_showModal("조회된 값이 없습니다.");
    	    	trtd += '<tr>';
    			trtd += '	<td style="width: 15%;" colspan="3">목록이 없습니다.</td>';
    			trtd += '</tr>';
        	}

    	    $("#noticeList").append(trtd);
        },
        error: function (jqXHR, status, error) {
        	f_showModal("조회 실패");
        }
    });
}


function f_goNoticeDetail(){
	window.location = "/mater/notice/load-page?pageName=notice/noticeDetail";
}

function f_goNoticeCreate(){
	window.location = "/mater/notice/load-page?pageName=notice/noticeCreate";
}
