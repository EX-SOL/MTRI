/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})

	$(".progressDiv").show();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	// 메인리스트
	f_selectMainList();
	f_selectNoticeList();
})

// 공지사항더보기
function f_goNotice(){
	window.location = "/mater/main/load-page?pageName=notice/notice";
}

// 자재대금 등록
function f_goMaterCreate(){
	window.location = "/mater/main/load-page?pageName=mater/materCreate";
}

// 자재대금 목록
function f_goMaterList(){
	window.location = "/mater/main/load-page?pageName=mater/materList";
}

// 공지사항 상세
function f_goNoticeDetail(){
	window.location = "/mater/main/load-page?pageName=notice/noticeDetail";
}

// 장비 목록
function f_goMachineryList(){
	window.location = "/mater/main/load-page?pageName=machinery/machineryList";
}

// 마이페이지 상세
function f_goMypage(){
	window.location = "/mater/main/load-page?pageName=mypage/mypage";
}

// 자료실 상세
function f_goReference(){
	window.location = "/mater/main/load-page?pageName=ref/reference";
}

// 메인 리스트 추출
function f_selectMainList(){
	var innerHTML = '';
	var param = {"custAthrCd": $("#custAthrCd").val()
			   , "mtriCustNo":$("#mtriCustNo").val()
			   , "blngDptcd": $("#blngDptcd").val()
			   , "wkscCd": $("#wkscCd").val()};
	$.ajax({
     method: "POST",
     url: "/mater/mater/selectMainList",
     contentType: 'application/json',
     dataType: 'json',
     data: JSON.stringify(param),
     success: function (response, textStatus, jqXHR) {
     	console.log(response);
     	var innerHTML = '';
     	var totalCnt = 0;
     	if(response.length > 0){
     		
     		$.each(response, function(key, item){
     			var mnpbNm;
    			if ( item.mnpbClssCd == "M" ){mnpbNm = "자재";}else{mnpbNm="장비";};
    			
     			innerHTML += "<li>";
     			if ( item.etcRmrk == null || item.etcRmrk == "" ){
     				innerHTML += "	<a href='javascript:f_goMaterDetail(\""+item.mnpbAskSqno+"\", \""+item.mnpbClssCd+"\", \""+item.mnpbAskYYMM+"\", \""+item.mtriCustNo+"\");'  class='itemLink'>";
     			} else {
     				innerHTML += "	<a style='background-color:#eee; border:1px solid;' href='javascript:f_goMaterDetail(\""+item.mnpbAskSqno+"\", \""+item.mnpbClssCd+"\", \""+item.mnpbAskYYMM+"\", \""+item.mtriCustNo+"\");'  class='itemLink'>";
				}
         		innerHTML += '		<div class="topGroup">';
         		innerHTML += '			<div class="nameBox">['+mnpbNm+'] '+item.korDptnm+' 현장</div>';
         		innerHTML += '			<div class="dateBox">'+item.mnpbAskYYMM+'</div>';
         		innerHTML += '			<div class="itemBox">';
         		innerHTML += '				<div class="topTxt">'+item.wkscNm+' 공구</div>';
         		innerHTML += '				<div class="bottomTxt">'+item.cntrtNm+'</div>';
         		innerHTML += '			</div>';
         		innerHTML += '		</div>';
         		innerHTML += '		<div class="bottomGroup">';
         		innerHTML += '			<span class="priceTxt">'+item.askAmt+'</span>';
         		innerHTML += '		</div>';
         		innerHTML += '	</a>';
         		innerHTML += '</li>';
     		});
     		totalCnt = response[0].total;
     	} else {
     		innerHTML += '<li>';
     		innerHTML += '	<a href="#" class="itemLink">';
 			innerHTML += '		<div class="topGroup">';
     		innerHTML += '			<div class="nameBox">조회된 데이터가 없습니다.</div>';
     		innerHTML += '		</div>';
     		innerHTML += '	</a>';
     		innerHTML += '</li>';
     	}
     	var custAthrCd = $("#custAthrCd").val();
     	
     	innerHTML += '<li>';
     	innerHTML += '	<div class="chargedBox">';
     	innerHTML += '		<div class="topTxt">'+ $("#custNm").val() +'님 청구 목록</div>';
     	innerHTML += '		<div class="chargeBtnBox">';
     	innerHTML += '			<a href="javascript:f_goMaterList();" class="chargeBtn">';
     	innerHTML += '				<i class="ic ic_charge"></i>';
     	innerHTML += '				<span class="txt">'+ totalCnt +'</span> <span class="unit">건</span>';
     	innerHTML += '			</a>';
     	innerHTML += '		</div>';
     	if(custAthrCd == 'D'){
     		innerHTML += '		<div class="regBtnBox"}>';
         	innerHTML += '			<a href="javascript:f_goMaterCreate();" class="regBtn">';
         	innerHTML += '				<i class="ic ic_reg"></i>';
         	innerHTML += '				<span class="txt">자재대금 등록</span>';
         	innerHTML += '			</a>';
         	innerHTML += '		</div>';
     	}
     	innerHTML += '	</div>';
     	innerHTML += '</li>';
     	$("#mainList").html(innerHTML);
     	$(".progressDiv").hide();
     },
     error: function (jqXHR, status, error) {
    	 $(".progressDiv").hide();
     	f_showModal("조회 실패");
     }
 });
}

function f_selectNoticeList(){
	var param = {"data" : "test"};
	$(".progressDiv").show();
	$.ajax({
        method: "POST",
        url: "/mater/notice/selectNoticeMainList",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	var innerHTML = '';
        	
        	if(response.length > 0){
        		$.each(response, function(key, item){
        			innerHTML += '<li style="height: 20px;">';
        			innerHTML += '	<a href="javascript:f_goNoticeDetail('+item.notcMtriSeq+');"';
        			innerHTML += '		<div class="subject">'+(key+1) + ". " +item.notcMtriTitlNm+'</div>';
        			innerHTML += '		<div class="date">'+item.fsttmRgstDttm+'</div>';
        			innerHTML += '	</a>';
        			innerHTML += '</li>';
        		});
        	}else {
        		innerHTML += '<li>';
        		innerHTML += '		<div class="subject">조회된 내역이 없습니다.</div>';
        		innerHTML += '</li>';
        	}
        	
        	$("#noticeList").html(innerHTML);
        	$(".progressDiv").hide();
        },
        error: function (jqXHR, status, error) {
        	$(".progressDiv").hide();
        	f_showModal("조회 실패");
        }
    });
}

//자재상세로 가기위한 데이터
function f_goMaterDetail(mnpbAskSqno, mnpbClssCd, mnpbAskYYMM, mtriCustNo){
	var day = mnpbAskYYMM.replace('.', '');
	window.location = "/mater/mater/selectMaterDetail?mtriCustNo="+mtriCustNo+"&mnpbAskYYMM="+day+"&mnpbAskSqno="+mnpbAskSqno+"&mnpbClssCd="+mnpbClssCd;
}

function f_goNoticeDetail(notcMtriSeq){
	window.location = "/mater/notice/selectNoticeDetail?notcMtriSeq="+notcMtriSeq;
}