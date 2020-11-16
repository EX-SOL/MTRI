$(document).ready(function () {
	function datePicker() {

		if ( $('.date-picker').length) {

			$('.date-picker').datepicker(
				{
					autoClose: true
				}
			);
			$('.date-picker').each(function(){

				if( $(this).val() !== '' ){
					$(this).data('datepicker').selectDate(new Date($(this).val()));
				}
			})
		}
	}
	datePicker();
})

