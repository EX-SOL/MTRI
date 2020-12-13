/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").show();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
})


function f_modify(){
	$("#modify-page").show();
	$("#detail-page").hide();
}

//수정상태에서 취소 시 상세페이지 표출
function f_modifyCancel(){
	//$("#modify-page").hide();
	//$("#detail-page").show();
	var notcMtriSeq = $("#notcMtriSeq").val();
	f_goNoticeDetailPage(notcMtriSeq);
	
}

function f_goModifySave(){
	$(".progressDiv").show();
	var notcMtriTitlNm = $("#notcMtriTitlNm").val();
	var bltnStrtDates = $("#bltnStrtDates").val();
	var bltnEndDates = $("#bltnEndDates").val();
	var notcMtriCtnt = $("#notcMtriCtnt").val();
	var notcMtriSeq = $("#notcMtriSeq").val();
	
	if(notcMtriTitlNm == "" || notcMtriTitlNm == null){
		$(".progressDiv").hide();
		f_showModal("제목을 입력해주세요.");
		return;
	}
	if(bltnStrtDates > bltnEndDates){
		$(".progressDiv").hide();
		f_showModal("게시일을 다시 설정해주세요.");
		return;
	}
	if(notcMtriCtnt =="" || notcMtriCtnt == null){
		$(".progressDiv").hide();
		f_showModal("내용을 입력해주세요.");
		return;
	}
	bltnStrtDates = bltnStrtDates.replace(/\//gi, '');
	bltnEndDates = bltnEndDates.replace(/\//gi, '');

	$("#bltnStrtDates").val(bltnStrtDates);
	$("#bltnEndDates").val(bltnEndDates);
	
	var frmData = new FormData($('#noticeModifyForm')[0]);
	
	$.ajax({
        method: "POST",
        enctype: 'multipart/form-data',
        url: "/mater/notice/updateNotice",
        async: true,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: frmData,
        success: function (response, textStatus, jqXHR) {
        	if (response.SUCCESS == true){
        		f_showModal_func("수정되었습니다.", "f_goNoticeDetailPage('"+notcMtriSeq+"')");
        	}else {
        		if(response.resultMsg != null){
        			f_showModal(response.resultMsg);
        		}else{
        			f_showModal("수정에 실패하였습니다.");
        		}
        	}
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("수정에 실패하였습니다.");
        }
    });
}

function f_goNoticePage(){
	window.location = "/mater/main/load-page?pageName=notice/notice";
}

function f_goNoticeDetailPage(notcMtriSeq){
	window.location = "/mater/notice/selectNoticeDetail?notcMtriSeq="+notcMtriSeq;
}

function f_remove(){
	f_confirm("정말 삭제하시겠습니까?", "f_deleteNotice();");
}

function f_deleteNotice(){
	$(".progressDiv").show();
	var notcMtriSeq = $("#notcMtriSeq").val();
	var mtriCustNo = $("input[name='mtriCustNo']").val();
	var param = {"notcMtriSeq" : notcMtriSeq, "lsttmModfrId":mtriCustNo}
	
	$.ajax({
        method: "POST",
        url: "/mater/notice/deleteNotice",
        contentType: 'application/json',
        dataType: 'json',
        data : JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	if(response.SUCCESS == true){
        		f_showModal_func("삭제되었습니다.", "f_goNoticePage()");
        	}else{
        		f_showModal("삭제하는 중 오류가 발생하였습니다.");
        	}
        	
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
            $(".progressDiv").hide();
            f_showModal("삭제하는 중 오류가 발생하였습니다.");
        }
    });
}