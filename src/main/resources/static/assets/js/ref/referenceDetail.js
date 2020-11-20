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

function f_modify(){
	$("#modify-page").show();
	$("#detail-page").hide();
}

//수정상태에서 취소 시 상세페이지 표출
function f_modifyCancel(){
	$("#modify-page").hide();
	$("#detail-page").show();
}

//수정 적용
function f_goModifySave(){
	$(".progressDiv").show();
	var rfrmrBlbnTitlNm = $("#rfrmrBlbnTitlNm").val();
	var bltnStrtDates = $("#bltnStrtDates").val();
	var bltnEndDates = $("#bltnEndDates").val();
	var rfrmrBlbnCtnt = $("#rfrmrBlbnCtnt").val();
	var rfrmrBlbnSeq = $("#rfrmrBlbnSeq").val();
	
	if(rfrmrBlbnTitlNm == "" || rfrmrBlbnTitlNm == null){
		$(".progressDiv").hide();
		f_showModal("제목을 입력해주세요.");
		return;
	}
	if(bltnStrtDates > bltnEndDates){
		$(".progressDiv").hide();
		f_showModal("게시일을 다시 설정해주세요.");
		return;
	}
	if(rfrmrBlbnTitlNm == "" || rfrmrBlbnTitlNm == null){
		$(".progressDiv").hide();
		f_showModal("제목을 입력해주세요.");
		return;
	}
	if(rfrmrBlbnCtnt =="" || rfrmrBlbnCtnt == null){
		$(".progressDiv").hide();
		f_showModal("내용을 입력해주세요.");
		return;
	}
	
	bltnStrtDates = bltnStrtDates.replace(/\//gi, '');
	bltnEndDates = bltnEndDates.replace(/\//gi, '');
	
	$("#bltnStrtDates").val(bltnStrtDates);
	$("#bltnEndDates").val(bltnEndDates);
	var frmData = new FormData($('#referenceModifyForm')[0]);

	$.ajax({
        method: "POST",
        enctype: 'multipart/form-data',
        url: "/mater/ref/updateReference",
        async: true,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: frmData,
        success: function (response, textStatus, jqXHR) {
        	if (response.SUCCESS == true){
        		f_showModal("수정되었습니다.");
        		window.location = "/mater/ref/selectReferenceDetail?rfrmrBlbnSeq="+rfrmrBlbnSeq;
        	}else {
        		f_showModal("수정에 실패하였습니다.");
        	}
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("수정에 실패하였습니다.");
        }
    });
}


function f_remove(){
	f_confirm("정말 삭제하시겠습니까?", "f_deleteReference();");
}

function f_deleteReference(){
	$(".progressDiv").show();
	var rfrmrBlbnSeq = $("#rfrmrBlbnSeq").val();
	var mtriCustNo = $("input[name='mtriCustNo']").val();
	var param = {"rfrmrBlbnSeq" : rfrmrBlbnSeq, "lsttmModfrId":mtriCustNo}
	
	$.ajax({
        method: "POST",
        url: "/mater/ref/deleteReference",
        contentType: 'application/json',
        dataType: 'json',
        data : JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	if(response.SUCCESS == true){
        		f_showModal("삭제되었습니다.");
        		window.location = "/mater/main/load-page?pageName=ref/reference";
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