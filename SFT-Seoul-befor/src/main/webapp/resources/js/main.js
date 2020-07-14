$(document).ready(function(){
	/*mainVisual*/
	$(".mainVisual ul").bxSlider({
		auto: true,
		autoControls : false,
		speed : 500,
		pause : 6000,
		pager : true,
		controls : true
	});

	/*mainWeberPdt*/
	$(".mainWeberPdt > .inner > ul").bxSlider({
		mode: 'fade',
		auto: true,
		autoControls : false,
		speed : 500,
		pause : 6000,
		pager : true,
		controls : true
	});

	/*mainNotice h2 text length*/
	$(".mainNotice div h2").each(function(e){
		var nLength = 30;
		if( $(this).text().length >= nLength ) {
			$(this).text($(this).text().substr(0, nLength) + '...');
		}
	});
	
	/*mainNotice p text length*/
	$(".mainNotice div p").each(function(e){
		var nLength = 40;
		if( $(this).text().length >= nLength ) {
			$(this).text($(this).text().substr(0, nLength) + '...');
		}
	});
	
	
	
});