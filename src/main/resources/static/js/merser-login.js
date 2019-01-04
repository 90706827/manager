$(function(){
	$('body').keydown(function(e){
		if(e.which == 13){
			$('#login_btn').click();
		}
	})
})