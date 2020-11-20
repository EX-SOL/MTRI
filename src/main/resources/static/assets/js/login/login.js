/**
 * 
 */
$(document).ready(function(){
})

function f_goLogin(){
	var id = $("#mtriCustNo").val();
	var pw = $("#custPswd").val();
	
	if(id == "" || id == null){
		f_showModal("아이디를 입력해주세요.");
		return;
	}
	
	if(pw == "" || pw == null){
		f_showModal("비밀번호를 입력해주세요.");
		return;
	}
	
	var param = {"mtriCustNo" : id, "custPswd" : pw};
	
	$.ajax({
        method: "POST",
        url: "/mater/user/login",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	if(response.SUCCESS == true){
        		window.location = "/mater/main/load-page?pageName=main/main";
        	} else {
        		f_showModal("로그인 실패");
        	}
        },
        error: function (jqXHR, status, error) {
        	f_showModal("로그인 실패");
        }
    });
	
}

function f_showModal(text){
	var htmlData = '';
	
	htmlData += '<div class="alertModal">';
	htmlData += '	<div class="alert-modal-content">';
	htmlData += '		<div class="modalWrap">';
	htmlData += '			<div class="title">';
	htmlData += '				<img src="assets/img/img_header_logo.png" alt="">';
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

function f_closeModal(){
	$(".alertModal").hide();
	$('body').attr("style", "overflow:auto;");
}


function f_goRegister(){
	alert("회원가입 고");
	
	
}