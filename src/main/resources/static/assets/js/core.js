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
            console.error(jqXHR.responseJSON.message);
            f_showModal("로그아웃에 실패하였습니다.");
            
        }
    });
}

// 팝업창
function f_showModal(text){
	var htmlData = '';
	
	htmlData += '<div class="searchModal" id="modalView">';
	htmlData += '	<div class="search-modal-content">';
	htmlData += '		<div class="modalWrap">';
	htmlData += '			<h1 class="logo">';
	htmlData += '				<a href="javascript:f_goMain();" class="logoImg"></a>';
	htmlData += '			</h1>';
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

// confirm창
function f_confirm(text, func){
	var htmlData = '';
	$("#modalView").remove();
	htmlData += '<div class="searchModal" id="modalView">';
	htmlData += '	<div class="search-modal-content">';
	htmlData += '		<div class="modalWrap">';
	htmlData += '			<h1 class="logo">';
	htmlData += '				<a href="javascript:f_goMain();" class="logoImg"></a>';
	htmlData += '			</h1>';
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

// 팝업 닫기
function f_closeModal(){
	$(".searchModal").hide();
	$('body').attr("style", "overflow:auto;");
}

// APP 뒤로가기시 팝업 디자인
function f_historyGo(){
	var htmlData = '';
	var text = '앱을 종료하시겠습니까?';
	$("#modalView").remove();
	htmlData += '<div class="searchModal" id="modalView">';
	htmlData += '	<div class="search-modal-content">';
	htmlData += '		<div class="modalWrap">';
	htmlData += '			<h1 class="logo">';
	htmlData += '				<a href="javascript:f_goMain();" class="logoImg"></a>';
	htmlData += '			</h1>';
	htmlData += '		</div>';
	htmlData += '		<div class="modalText">';
	htmlData += '			<span>'+ text +'</span>';
	htmlData += '		</div>';
	htmlData += '		<div class="modalButton">';
	htmlData += '			<button style="margin-left: -130px;" onclick="javascript:f_closeModal();" class="btn-cancel"> 취 소 </button>';
	htmlData += '			<button style="margin-left: 0;" onclick="javascript:f_finishApp();" class="btn-confirm"> 확 인 </button>';
	htmlData += '		</div>';
	htmlData += '	</div>';
	htmlData += '</div>';
	
	$('body').append(htmlData);
	$('body').attr("style", "overflow:hidden;");
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

