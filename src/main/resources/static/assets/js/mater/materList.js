/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").hide();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
	
	f_selectMaterList();
})


function f_goMaterDetail(){
	window.location = "/mater/main/load-page?pageName=mater/materDetail";
}


function f_selectMaterList(){
	//청구기간
	var param = {"data":"111"};
	$.ajax({
        method: "POST",
        url: "/mater/mater/selectMaterList",
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(param),
        success: function (response, textStatus, jqXHR) {
        	console.log(response);
        	if(response.length > 0){
        		
        	} else {
        		f_showModal("조회 실패");
        	}
        },
        error: function (jqXHR, status, error) {
        	f_showModal("조회 실패");
        }
    });
	
}