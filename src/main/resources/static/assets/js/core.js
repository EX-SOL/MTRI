'use strict';

$(document).ready(function() {
	
	$('.mn-btnrow a').on('click', function (evt) {
		var pageName =  $(this).data('path').concat('/').concat($(this).data('page'));
	    window.location = "/mater/main/load-page?pageName="+pageName;
	    $("#menuDiv").hide();
		$("#bodymain").attr('style', 'overflow:auto;');
	
	});

    $('.progressDiv').hide();
    
    $("input:text[numberOnly]").on("keyup", function() {
		// 숫자만 입력하기 위해 문자 제거
	    $(this).val($(this).val().replace(/[^0-9^,]/g,""));
	    // ,가 있다면 제거하고
	    $(this).val($(this).val().replace(/[^\d]+/g, ""));
	    // 새로 3자리마다 , 입력
	    $(this).val($(this).val().replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,"));
	});
});

// 메뉴바 오픈
function f_goMenu(){
	window.location = "#";
	$("#menuDiv").show();
	$("#main-wrapper").hide();
	$('body').attr("style", "overflow:hidden;");
}

// 메뉴바 닫기
function f_menuOff(){
	$("#menuDiv").hide();
	$("#main-wrapper").show();
	$('body').attr("style", "overflow:auto;");
}

function f_goMain(){
	$('.progressDiv').show();
	window.location = "/mater/main/load-page?pageName=main/main";
}


//달력 표출
function f_dateClick(id){
    $("#"+id).datepicker();
    $("#"+id).focus();
}

function f_goLogout(){
	localStorage.removeItem("mtriCustNo");
	localStorage.removeItem("custPswd");
	localStorage.removeItem("custAthrCd");
	localStorage.removeItem("custTelno");
	localStorage.removeItem("rpprNm");
	localStorage.removeItem("deprNm");
	localStorage.removeItem("bankActno");
	localStorage.removeItem("trBankNm");
	window.location = "/mater";
	$.ajax({
        method: "POST",
        url: "/mater/user/logout",
        contentType: 'application/json',
        dataType: 'json',
        success: function (response, textStatus, jqXHR) {
    		window.location = "/mater";
        },
        error: function (jqXHR, status, error) {
            f_showModal("로그아웃에 실패하였습니다.");
            
        }
    });
}


// APP 종료
function f_finishApp() {
	window.android.goAppFinish();
}

//해당 문자열을 공백으로 제거
function f_hyphenReplaceEmpty(str){
	var newStr = str.replace(/-/gi, '');
	
	return newStr;
}

// 해당문자열 하이픈 추가
function f_hyphenAdd(str){
	if( str != null && str.length > 0 ) {
		var newStr = "";
		if ( str.length == 11 ){
			newStr = str.substr(0,3) + "-" + str.substr(3,4) + "-" + str.substr(7,4);
		} else if (str.length == 10) {
			newStr = str.substr(0,2) + "-" + str.substr(2,4) + "-" + str.substr(6,4);
		} else if (str.length == 8){
			newStr = str.substr(0,4) + "-" + str.substr(4,2) + "-" + str.substr(6,2);
		}
	}
	return newStr;
}


// 마스킹 처리
function f_masking(kind, str){
	var newStr = "";
	if(str != "" && str != null){
		if(kind == "phone"){
			if(str.length == 13) {
				for(var i=0; i<str.length; i++){
					if( i > 8 ){
						newStr += "*"
					} else {
						newStr += str[i];
					}
				}
			} else if (str.length == 12) {
				for(var i=0; i<str.length; i++){
					if( i > 7 ){
						newStr += "*"
					} else {
						newStr += str[i];
					}
				}
			} else if (str.length == 11){
				for(var i=0; i<str.length; i++){
					if( i > 6 ){
						newStr += "*"
					} else {
						newStr += str[i];
					}
				}
			}
		} else if (kind == "birth") {
			
		} else if (kind == "addr") {
			
		} else if (kind == "name") {
			newStr = str.replace(str.charAt(1), "*");
			
		} else if (kind == "pinNo") {
			for(var i=0; i<str.length; i++){
				if( i > 11 ){
					newStr += "*"
				} else {
					newStr += str[i];
				}
			}
		}
	}
	return newStr;
}

// modal 띄우기
function f_showModal(text){
	var htmlData = '';
	
	htmlData += '<div class="alertModal">';
	htmlData += '	<div class="alert-modal-content">';
	htmlData += '		<div class="modalWrap">';
	htmlData += '			<div class="title">';
	htmlData += '				<img src="../assets/img/img_header_logo.png" alt="">';
	htmlData += '				<span>자재·장비대금 지킴이</span>';
	htmlData += '			</div>';
	htmlData += '		</div>';
	htmlData += '		<div class="modalText">';
	htmlData += '			<span>'+ text +'</span>';
	htmlData += '		</div>';
	htmlData += '		<div class="modalButton">';
	htmlData += '			<button onclick="javascript:f_closeModal();" class="btn-confirm"> 확 인 </button>';
	htmlData += '		</div>';
	htmlData += '	</div>';
	htmlData += '</div>';
	$('body').append(htmlData);
	$('body').attr("style", "overflow:hidden;");
}

// modal 띄우기
function f_confirm(text, func){
	var htmlData = '';
	
	htmlData += '<div class="alertModal">';
	htmlData += '	<div class="alert-modal-content">';
	htmlData += '		<div class="modalWrap">';
	htmlData += '			<div class="title">';
	htmlData += '				<img src="../assets/img/img_header_logo.png" alt="">';
	htmlData += '				<span>자재·장비대금 지킴이</span>';
	htmlData += '			</div>';
	htmlData += '		</div>';
	htmlData += '		<div class="modalText">';
	htmlData += '			<span>'+ text +'</span>';
	htmlData += '		</div>';
	htmlData += '		<div class="modalButton">';
	htmlData += '			<button style="margin-left: -130px;" onclick="javascript:f_closeModal();" class="btn-cancel"> 취 소 </button>';
	htmlData += '			<button style="margin-left: 0;" onclick="'+func+'" class="btn-confirm"> 확 인 </button>';
	htmlData += '		</div>';
	htmlData += '	</div>';
	htmlData += '</div>';
	$('body').append(htmlData);
	$('body').attr("style", "overflow:hidden;");
}

// modal 닫기
function f_closeModal(){
	$(".alertModal").hide();
	$('body').attr("style", "overflow:auto;");
}

// 앱 메인에서 뒤로가기시 종료
function f_historyGo(){
	var htmlData = '';
	var text = '앱을 종료하시겠습니까?';
	htmlData += '<div class="alertModal">';
	htmlData += '	<div class="alert-modal-content">';
	htmlData += '		<div class="modalWrap">';
	htmlData += '			<div class="title">';
	htmlData += '				<img src="../assets/img/img_header_logo.png" alt="">';
	htmlData += '				<span>자재·장비대금 지킴이</span>';
	htmlData += '			</div>';
	htmlData += '		</div>';
	htmlData += '		<div class="modalText">';
	htmlData += '			<span>'+ text +'</span>';
	htmlData += '		</div>';
	htmlData += '		<div class="modalButtonConfirm">';
	htmlData += '			<button onclick="javascript:f_closeModal();" class="btn-cancel"> 취 소 </button>';
	htmlData += '			<button onclick="javascript:f_finishApp();" class="btn-confirm"> 확 인 </button>';
	htmlData += '		</div>';
	htmlData += '	</div>';
	htmlData += '</div>';
	$('body').append(htmlData);
	$('body').attr("style", "overflow:hidden;");
}


// 첨부파일 선택 시 파일명 표출
function f_imgChange(){
	var fileValue = $("#flUpFileData").val().split("\\");
	var fileName = fileValue[fileValue.length-1]; // 파일명
	$("#fileName").text(fileName);
}


//처음 진입 시 한달 기간두기
function f_dateSetting(sDate, eDate){
	var date = new Date();
	$(eDate).val(getFormatDate(date));
	date.setMonth(date.getMonth()-1);
	$(sDate).val(getFormatDate(date));
}

function getFormatDate(date){
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '/' + month + '/' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}

//첨부파일 다운로드 
function f_downloadFile() {
	var attflNm = $('#attflNm').val();
	var attflPath = $('#attflPath').val();
	var param = '?attflPath='+attflPath+'&attflNm='+attflNm;
	if( attflNm != "" && attflPath != "" ){
		location.href = "/mater/mater/downloadFile"+param;
	}
}

