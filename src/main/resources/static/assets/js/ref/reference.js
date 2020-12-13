/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	$(".progressDiv").show();
	
	// 초기 데이터 설정
    f_monthSetting("#sDate", "#eDate");
	f_selectReference();
})

//처음 진입 시 한달 기간두기 +1달
function f_dateSettingPlus(sDate, eDate){
	var date = new Date();
	$(sDate).val(getFormatDate(date));
	date.setMonth(date.getMonth()+1);
	$(eDate).val(getFormatDate(date));
}

// 자료실 조회
function f_selectReference(){
	$(".progressDiv").show();
	var sDate = $("#sDate").val();
	var eDate = $("#eDate").val();
	var sTitleData = $("#sTitleData").val();
	
	sDate = sDate.replace(/-/gi, '');
	eDate = eDate.replace(/-/gi, '');
	var param = {"sDate":sDate, "eDate":eDate, "sTitleData":sTitleData};
	
	$.ajax({
        method: "POST",
        url: "/mater/ref/selectReferenceList",
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
        			innerHTML += '	<td onclick="javascript:f_goReferenceDetail('+item.rfrmrBlbnSeq+');">'+item.rfrmrBlbnTitlNm+'</td>';
        			innerHTML += '	<td>'+item.fsttmRgstDttm+'</td>';
        			innerHTML += '</tr>';
        		});
        	}else {
        		innerHTML += '<tr>';
        		innerHTML += '	<td colspan="3">조회된 내역이 없습니다.</td>';
        		innerHTML += '</tr>';
        	}
        	
        	$("#referenceList").html(innerHTML);
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("조회 실패");
        }
    });
}

function f_goReferenceDetail(rfrmrBlbnSeq){
	window.location = "/mater/ref/selectReferenceDetail?rfrmrBlbnSeq="+rfrmrBlbnSeq;
}

function f_goReferenceCreate(){
	window.location = "/mater/main/load-page?pageName=ref/referenceCreate";
}
