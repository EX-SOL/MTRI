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

//수정버튼클릭했을경우
function f_modify(){
	$("#modify-page").show();
	$("#detail-page").hide();
	
	//현장
	var fildClssCd = $("#fildClssCd").val();
	$("select[name='fildClssCd']").val(fildClssCd).attr("selected", "selected");
	//공구
	var cntcWkscCd = $("#cntcWkscCd").val();
	$("select[name='cntcWkscCd']").val(cntcWkscCd).attr("selected", "selected");
	//장비등록번호
	var mnpbRgsrSeq = $("#mnpbRgsrSeq").val();
	$("select[name='mnpbRgsrSeq']").val(mnpbRgsrSeq).attr("selected", "selected");
	
}

//수정상태에서 취소 시 상세페이지 표출
function f_modifyCancel(){
	$("#modify-page").hide();
	$("#detail-page").show();
}

//수정된 내용 반영하기 위한 작업
function f_goModifySave(){
	$(".progressDiv").show();
	var askAmt = $("input[name='askAmt']").val();
	var newAskAmt = askAmt.replace(/,/gi, '');
	$("input[name='askAmt']").val(newAskAmt);
	
	var frmData = new FormData($('#materModifyForm')[0]);
	
	var mtriCustNo = $("input[name='mtriCustNo']").val();
	var mnpbAskSqno = $("input[name='mnpbAskSqno']").val();
	var mnpbClssCd = $("input[name='mnpbClssCd']").val();
	var mnpbAskYYMM = $("input[name='mnpbAskYYMM']").val();
	$.ajax({
        method: "POST",
        enctype: 'multipart/form-data',
        url: "/mater/mater/updateMaterList",
        async: true,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: frmData,
        success: function (response, textStatus, jqXHR) {
        	if (response.SUCCESS == true){
        		f_showModal("수정되었습니다.");
        		window.location = "/mater/mater/selectMaterDetail?mtriCustNo="+mtriCustNo+"&mnpbAskYYMM="+mnpbAskYYMM+"&mnpbAskSqno="+mnpbAskSqno+"&mnpbClssCd="+mnpbClssCd;

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
	f_confirm("정말 삭제하시겠습니까?", "f_deleteMater();");
}

function f_deleteMater(){
	$(".progressDiv").show();
	var mnpbAskYYMM = $("input[name='mnpbAskYYMM']").val();
	var mtriCustNo = $("input[name='mtriCustNo']").val();
	var mnpbAskSqno = $("input[name='mnpbAskSqno']").val();
	var mnpbClssCd = $("input[name='mnpbClssCd']").val();
	mnpbAskYYMM = mnpbAskYYMM.substring(0, 4) + "" + mnpbAskYYMM.substring(5, 7);
	var param = {"mnpbAskYYMM" : mnpbAskYYMM, "mtriCustNo" : mtriCustNo, "mnpbAskSqno" : mnpbAskSqno, "mnpbClssCd" : mnpbClssCd};
	
	$.ajax({
        method: "POST",
        url: "/mater/mater/deleteMater",
        contentType: 'application/json',
        dataType: 'json',
        data : JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	if(response.SUCCESS == true){
        		f_showModal("삭제되었습니다.");
        		window.location = "/mater/main/load-page?pageName=mater/materList";
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