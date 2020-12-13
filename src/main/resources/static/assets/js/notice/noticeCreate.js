/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").hide();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	// 처음 진입 시 한달 기간 세팅
	f_dateSettingPlus("#bltnStrtDates", "#bltnEndDates");
})

//처음 진입 시 한달 기간두기 +1달
function f_dateSettingPlus(sDate, eDate){
	var date = new Date();
	$(sDate).val(getFormatDate(date));
	date.setMonth(date.getMonth()+1);
	$(eDate).val(getFormatDate(date));
}

function f_noticeSave(){
	$(".progressDiv").show();
	
	var notcMtriTitlNm = $("#notcMtriTitlNm").val();
	var bltnStrtDates = $("#bltnStrtDates").val();
	var bltnEndDates = $("#bltnEndDates").val();
	var notcMtriCtnt = $("#notcMtriCtnt").val();
	
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
	
	var frmData = new FormData($('#noticeForm')[0]);
	
	$.ajax({
        method: "POST",
        enctype: 'multipart/form-data',
        url: "/mater/notice/insertNotice",
        async: true,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: frmData,
        success: function (response, textStatus, jqXHR) {
        	if (response.SUCCESS == true){
        		f_showModal_func("등록되었습니다.", "f_goNoticePage()");
        	}else {
        		if(response.resultMsg != null){
        			f_showModal(response.resultMsg);
        		}else{
        			f_showModal("등록에 실패하였습니다.");
        		}
        	}
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("등록에 실패하였습니다.");
        }
    });
}

function f_goNoticePage(){
	window.location = "/mater/main/load-page?pageName=notice/notice";
}