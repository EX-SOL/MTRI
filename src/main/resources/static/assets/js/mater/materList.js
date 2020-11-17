/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").hide();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	// 초기 데이터 설정
	var date = new Date();
	var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    $("#eMonth").val(year+"-"+month);
    var prevMonth = parseInt(month)-1
    $("#sMonth").val(year+"-"+prevMonth);
    
    f_selectMaterList();
})


function f_goMaterDetail(mtriMnpbAskSqno, mtriCd, mrtiMnpbAskYYMM){
	var mtriCustNo = $("#mtriCustNo").val();
	//var param = {"mtriCustNo":mtriCustNo, "mrtiMnpbAskYYMM":mrtiMnpbAskYYMM, "mtriMnpbAskSqno":mtriMnpbAskSqno, "mtriCd":mtriCd};
	
	window.location = "/mater/mater/selectMaterDetail?mtriCustNo="+mtriCustNo+"&mrtiMnpbAskYYMM="+mrtiMnpbAskYYMM+"&mtriMnpbAskSqno="+mtriMnpbAskSqno+"&mtriCd="+mtriCd;
}


function f_selectMaterList(){
	var sMonth = $("#sMonth").val();
	var eMonth = $("#eMonth").val();
	
	sMonth = f_hyphenReplaceEmpty(sMonth);
	eMonth = f_hyphenReplaceEmpty(eMonth);

	//청구기간
	var param = {"sMonth": sMonth, 
				"eMonth": eMonth,
				"mtriCustNo":$("#mtriCustNo").val()};
	$.ajax({
        method: "POST",
        url: "/mater/mater/selectMaterList",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	var innerHTML = '';
        	
        	if(response.length > 0){
        		
        		$.each(response, function(key, item){
        			innerHTML += "<li onclick='javascript:f_goMaterDetail(\""+item.mtriMnpbAskSqno+"\", \""+item.mtriCd+"\", \""+item.mrtiMnpbAskYYMM+"\");'>";
            		innerHTML += '	<div class="topBox">';
            		innerHTML += '		<div class="nameTxt">'+item.fildClssCd+' 현장</div>';
            		innerHTML += '		<div class="priceBox">';
            		innerHTML += '			<span class="priceTxt">'+item.askAmt+'</span>';
            		innerHTML += '			<span class="unit">원</span>';
            		innerHTML += '		</div>';
            		innerHTML += '	</div>';
            		innerHTML += '	<div class="bottomBox">';
            		innerHTML += '		<div class="cellBox">';
            		innerHTML += '			<div class="subject">계약업체</div>';
            		innerHTML += '			<div class="con">'+item.cntrtCrprNm+'</div>';
            		innerHTML += '		</div>';
            		innerHTML += '		<div class="cellBox">';
            		innerHTML += '			<div class="subject">자재업체</div>';
            		innerHTML += '			<div class="con">'+item.mtriCd+'</div>';
            		innerHTML += '		</div>';
            		innerHTML += '		<div class="cellBox">';
            		innerHTML += '			<div class="subject">자재명</div>';
            		innerHTML += '			<div class="con">'+item.mtriNm+'</div>';
            		innerHTML += '		</div>';
            		innerHTML += '		<div class="cellBox">';
            		innerHTML += '			<div class="subject">예금주</div>';
            		innerHTML += '			<div class="con">'+item.deprNm+'</div>';
            		innerHTML += '		</div>';
            		innerHTML += '	</div>';
            		innerHTML += '</li>';
        		});
        		
        		
        	} else {
        		innerHTML += '<li>';
        		innerHTML += '	<div class="topBox">';
        		innerHTML += '		<div class="nameTxt">조회된 데이터가 없습니다.</div>';
        		innerHTML += '	</div>';
        		innerHTML += '</li>';
        	}
        	
        	$("#materListData").html(innerHTML);
        },
        error: function (jqXHR, status, error) {
        	f_showModal("조회 실패");
        }
    });
	
}