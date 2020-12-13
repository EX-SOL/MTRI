/**
 * 
 */
$(document).ready(function(){
	
	var userNm = $("#userNm").val();
	var userPhoneNo = $("#userPhoneNo").val();
	
	var sName = $("#sName").val();
	var sMobileNo = $("#sMobileNo").val();
	
	if(userNm != "" && userPhoneNo != ""){
		if (userNm == sName && userPhoneNo == sMobileNo){
			var param = {"userNm" : userNm, "userPhoneNo" : userPhoneNo};
			
			$.ajax({
		        method: "POST",
		        url: "/mater/intro/selectIdFind",
		        contentType: 'application/json',
		        dataType: 'json',
		        data: JSON.stringify(param),
		        success: function (response, textStatus, jqXHR) {
		        	console.log(response);
		        	var innerHTML = '';
		        	if(response.length > 0){
		        		
		        		$.each(response, function(key, item){
		        			innerHTML += '<div class="row">';
			        		innerHTML += '	<div class="subject">아이디는</div>';
			        		innerHTML += '	<div class="con" style="width:140px;margin-right:5px;">';
			        		innerHTML += '		<input type="text" value="'+item.MTRI_CUST_NO+'" readonly>';
			        		innerHTML += '	</div>';
			        		innerHTML += '	<div class="subject">입니다.</div>';
			        		innerHTML += '</div>';
		        		});
		        		$("#idList").html(innerHTML);
			        	
			        	$("#showId-page").show();
						$("#idBody-page").hide();
		        		
		        	} else {
		        		f_showModal_func("가입정보가 존재하지않습니다.", "f_goBackMain()");
		        	}
		        },
		        error: function (jqXHR, status, error) {
		        	f_showModal_func("조회 도중 오류가 발생하였습니다.", "f_goBackMain()");
		        }
		    });
			
		} else{
			f_showModal_func("입력하신 정보와 인증한 정보가 일치하지않습니다.", "f_goBackMain()");
			return;
		}
	} else {
	}
	
});



//본인인증 띄우러가기
function f_goCertiPopup(){
	var userNm = $("#userNm").val();
	var userPhoneNo = $("#userPhoneNo").val();
	
	if(userNm == "" || userNm == null){
		f_showModal("이름을 입력해주세요.");
		return;
	}
	if(userPhoneNo == "" || userPhoneNo == null ){
		f_showModal("전화번호를 입력해주세요.");
		return;
	}
	
	userPhoneNo = f_hyphenReplaceEmpty(userPhoneNo);
	window.location = "/mater/intro/idFind_Checkplus?userNm="+userNm+"&userPhoneNo="+userPhoneNo;
}

//메인가기전 세션 털고가기
function f_goBackMain(){
	$.ajax({
        method: "POST",
        url: "/mater/intro/goBackMain",
        contentType: 'application/json',
        dataType: 'json',
        success: function (response, textStatus, jqXHR) {
        	window.location = "/mater";
        	$("#checkPlus").remove();
        },
        error: function (jqXHR, status, error) {
        	window.location = "/mater";
        	$("#checkPlus").remove();
        }
    });
}