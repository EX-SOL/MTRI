/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").hide();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	f_fildData();	//현장데이터
})

//수정버튼클릭했을경우
function f_modify(){
	$("#modify-page").show();
	$("#detail-page").hide();
	
	//현장
	var blngDptcd = $("#blngDptcd").val();
	$("select[name='blngDptcd']").val(blngDptcd).attr("selected", "selected");
	
	f_wkscData();	//공구데이터
	//공구
	var wkscCd = $("#wkscCd").val();
	$("select[name='wkscCd']").val(wkscCd).attr("selected", "selected");
	//장비등록번호
//	var mnpbRgsrSeq = $("#mnpbRgsrSeq").val();
//	$("select[name='mnpbRgsrSeq']").val(mnpbRgsrSeq).attr("selected", "selected");
	
}

//수정상태에서 취소 시 상세페이지 표출
function f_modifyCancel(){
//	$("#modify-page").hide();
//	$("#detail-page").show();
	var mtriCustNo = $("input[name='mtriCustNo']").val();
	var mnpbAskSqno = $("input[name='mnpbAskSqno']").val();
	var mnpbClssCd = $("input[name='mnpbClssCd']").val();
	var mnpbAskYYMM = $("input[name='mnpbAskYYMM']").val();
	f_goModifyPage(mtriCustNo, mnpbAskYYMM, mnpbAskSqno, mnpbClssCd);
	
}

//수정된 내용 반영하기 위한 작업
function f_goModifySave(){
	$(".progressDiv").show();

	var cntrtNm = $("input[name='cntrtNm']").val();
	if(cntrtNm == null || cntrtNm == "" ){
		$(".progressDiv").hide();
		f_showModal("자재/장비명을 입력해주세요.");
		return;
	}
	
	var askAmt = $("input[name='askAmt']").val();
	var newAskAmt = askAmt.replace(/,/gi, '');
	$("input[name='askAmt']").val(newAskAmt);
	if(newAskAmt == null || newAskAmt == "" ){
		$(".progressDiv").hide();
		f_showModal("청구금액을 입력해주세요.");
		return;
	}
	
	var etcRmrk = $("input[name='etcRmrk']").val();
	if(etcRmrk == null || etcRmrk == ""){
		$(".progressDiv").hide();
		f_showModal("수정사유를 입력해주세요.");
		return;
	}
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
        		f_showModal_func("수정되었습니다.", "f_goModifyPage('"+mtriCustNo+"', '"+mnpbAskYYMM+"', '"+mnpbAskSqno+"', '"+mnpbClssCd+"')" );
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

function f_goModifyPage(mtriCustNo, mnpbAskYYMM, mnpbAskSqno, mnpbClssCd){
	window.location = "/mater/mater/selectMaterDetail?mtriCustNo="+mtriCustNo+"&mnpbAskYYMM="+mnpbAskYYMM+"&mnpbAskSqno="+mnpbAskSqno+"&mnpbClssCd="+mnpbClssCd;
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
        		f_showModal_func("삭제되었습니다.", "f_goMaterListPage()");
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

function f_goMaterListPage(){
	window.location = "/mater/main/load-page?pageName=mater/materList";
}


//계약업체 조회팝업
function f_cntrtCrprNmPopup(){
	$("#crprNmPopup").show();
	$('body').attr("style", "overflow:hidden;");
	
	f_crprNmData();
}

// 팝업 종료
function f_modalClose(){
	$("#crprNmPopup").hide();
	$('body').attr("style", "overflow:auto;");
}

//계약업체 목록 조회
function f_crprNmData(){
	$(".progressDiv").show();
	var sCntrtCrprNm = $("#sCntrtCrprNm").val();
	var param = {"sCntrtCrprNm" : sCntrtCrprNm};
	
	$.ajax({
        method: "POST",
        url: "/mater/mater/selectCntrtCrprList",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log("response: ", response);
        	var innerHTML = '';
        	if( response.length > 0 ){
        		$.each(response, function(key, item){
            		innerHTML += '<li style="padding: 9px 13px 9px 10px;">';
            		innerHTML += '	<input type="hidden" value="'+item.CNTRT_CRPR_NM+'" name="'+item.CNTRT_CRPR_SEQ+'_NM">';
            		innerHTML += '	<input type="radio" value="'+item.CNTRT_CRPR_SEQ+'" name="cntrtCrprData" style="margin-right:5px;">';
            		innerHTML += 	item.CNTRT_CRPR_NM + " ( " + item.RPPR_NM + " ) ";
            		innerHTML += '</li>';
            	});
        	}else {
        		innerHTML += '<li class="listLi">데이터가 없습니다.</li>';
        	}
        	
        	$("#cntrtCrprList").html(innerHTML);
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("조회 실패");
        }
    });
}

// 업체명 세팅
function f_cntrtCheck(){
	$(".progressDiv").show();
	
	var cntrtCrprSeq = $("input[name='cntrtCrprData']:checked").val();
	var cntrtCrprNm = $("input[name='"+cntrtCrprSeq+"_NM']").val();
	
	if(cntrtCrprSeq == "" || cntrtCrprSeq == null){
		$(".progressDiv").hide();
		f_showModal("업체명을 선택해주세요.");
		return;
	} else {
		$("#cntrtCrprNm").val(cntrtCrprNm);
		$("#cntrtCrprSeq").val(cntrtCrprSeq);
		$(".progressDiv").hide();
		$("#crprNmPopup").hide();
		$('body').attr("style", "overflow:auto;");
	}
	
}