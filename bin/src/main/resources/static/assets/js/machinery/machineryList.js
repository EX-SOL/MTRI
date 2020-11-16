/**
 * 
 */
$(document).ready(function(){
	$('.showToggleBtn').on('click',function () {
		$(this).toggleClass('on');
	})
	$(".progressDiv").hide();
	
	$("#menuDiv").load('/mater/main/load-page?pageName=menu');
	
})


function f_goMachineryDetail(){
	window.location = "/mater/main/load-page?pageName=machinery/machineryDetail";
}