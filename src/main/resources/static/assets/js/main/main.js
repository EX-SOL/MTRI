/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	// 메인리스트
	f_selectMainList();
})

function f_goNotice(){
	window.location = "/mater/main/load-page?pageName=notice/notice";
}


function f_goMaterCreate(){
	window.location = "/mater/main/load-page?pageName=mater/materCreate";
}

function f_goMaterList(){
	window.location = "/mater/main/load-page?pageName=mater/materList";
}

function f_goNoticeDetail(){
	window.location = "/mater/main/load-page?pageName=notice/noticeDetail";
}

function f_goMachineryList(){
	window.location = "/mater/main/load-page?pageName=machinery/machineryList";
}

function f_goMypage(){
	window.location = "/mater/main/load-page?pageName=mypage/mypage";
}

function f_selectMainList(){
	var innerHTML = '';
	var param = {"bankActno": $("#bankActno").val()
			   , "mtriCustNo":$("#mtriCustNo").val()};
	$.ajax({
     method: "POST",
     url: "/mater/mater/selectMainList",
     contentType: 'application/json',
     dataType: 'json',
     data: JSON.stringify(param),
     success: function (response, textStatus, jqXHR) {
     	console.log(response);
     	var innerHTML = '';
     	
     	if(response.length > 0){
     		
     		$.each(response, function(key, item){
     			innerHTML += '<li>';
         		innerHTML += '	<a href="javascript:f_goMaterList();" class="itemLink">';
         		innerHTML += '		<div class="topGroup">';
         		innerHTML += '			<div class="nameBox">'+item.fildClssCd+' 현장</div>';
         		innerHTML += '			<div class="dateBox">'+item.mrtiMnpbAskYYMM+'</div>';
         		innerHTML += '			<div class="itemBox">';
         		innerHTML += '				<div class="topTxt">'+item.cntcWkscCd+' 공구</div>';
         		innerHTML += '				<div class="bottomTxt">'+item.mtriNm+'</div>';
         		innerHTML += '			</div>';
         		innerHTML += '		</div>';
         		innerHTML += '		<div class="bottomGroup">';
         		innerHTML += '			<span class="priceTxt">'+item.askAmt+'</span>';
         		innerHTML += '		</div>';
         		innerHTML += '	</a>';
         		innerHTML += '</li>';
     		});
     	} else {
     		innerHTML += '<li>';
     		innerHTML += '	<a href="#" class="itemLink">';
 			innerHTML += '		<div class="topGroup">';
     		innerHTML += '			<div class="nameBox">조회된 데이터가 없습니다.</div>';
     		innerHTML += '		</div>';
     		innerHTML += '	</a>';
     		innerHTML += '</li>';
     	}
     	
     	innerHTML += '<li>';
     	innerHTML += '	<div class="chargedBox">';
     	innerHTML += '		<div class="topTxt">'+response[0].custNm+'님 청구 목록</div>';
     	innerHTML += '		<div class="chargeBtnBox">';
     	innerHTML += '			<a href="javascript:f_goMachineryList();" class="chargeBtn">';
     	innerHTML += '				<i class="ic ic_charge"></i>';
     	innerHTML += '				<span class="txt">'+response[0].total+'</span> <span class="unit">건</span>';
     	innerHTML += '			</a>';
     	innerHTML += '		</div>';
     	innerHTML += '	</div>';
     	innerHTML += '</li>';
     	
     	$("#mainList").html(innerHTML);
     },
     error: function (jqXHR, status, error) {
     	f_showModal("조회 실패");
     }
 });
	
}