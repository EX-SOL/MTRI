/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").show();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	// 초기 데이터 설정
	var date = new Date();
	var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    $("input[name='d_mnpbAskYYMM']").val(year+"-"+month);
    
    f_fildData();	//현장데이터
    f_wkscData();	//공구데이터
})

// 장비대금 등록
function f_save(){
	$(".progressDiv").show();
	var mnpbAskYYMM = $("input[name='d_mnpbAskYYMM']").val();
	var newMnpbAskYYMM = f_hyphenReplaceEmpty(mnpbAskYYMM);
	$("input[name='mnpbAskYYMM']").val(newMnpbAskYYMM);
	
	var askAmt = $("input[name='askAmt']").val();
	var newAskAmt = askAmt.replace(/,/gi, '');
	$("input[name='askAmt']").val(newAskAmt);
	
	var cntrtCrprNm = $("#cntrtCrprNm").val();
	if (cntrtCrprNm == "" || cntrtCrprNm == null ){
		$(".progressDiv").hide();
		f_showModal("계약업체명을 선택해주세요.");
		return;
	}

	var cntrtNm = $("input[name='cntrtNm']").val();
	if (cntrtNm == "" || cntrtNm == null ){
		$(".progressDiv").hide();
		f_showModal("장비명을 입력해주세요.");
		return;
	}

	var askAmt = $("input[name='askAmt']").val();
	if (askAmt == "" || askAmt == null ){
		$(".progressDiv").hide();
		f_showModal("청구금액을 입력해주세요.");
		return;
	}
	
	var fileName = $("#fileName").text();
	if (fileName == "" || fileName == null ){
		$(".progressDiv").hide();
		f_showModal("세금계산서는 필수입니다.");
		return;
	}
	
	var frmData = new FormData($('#materForm')[0]);
	
	$.ajax({
        method: "POST",
        enctype: 'multipart/form-data',
        url: "/mater/mater/insertMaterList",
        async: true,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: frmData,
        success: function (response, textStatus, jqXHR) {
        	if (response.SUCCESS == true){
        		f_showModal_func("등록되었습니다.", "f_goMaterListPage()");
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

//업체명 세팅
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