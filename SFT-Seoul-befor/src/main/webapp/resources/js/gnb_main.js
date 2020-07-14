/*
 * 연혁 탭 나누기
 */
$(function() {
	$('ul.tab li a').click(function() {
		var activeTab = $(this).attr('data-tab');
		$('ul.tab li a').removeClass('current');		
		$('.tabcontent').removeClass('current');
		$(this).addClass('current');
		$('#' + activeTab).addClass('current');
	})
});


/*
 * 솔루션
 */
$(function() {
	$('ul.tab2 li a').click(function() {
		var activeTab = $(this).attr('data-tab');
		$('ul.tab2 li a').removeClass('current');		
		$('.tabcontent2').removeClass('current');
		$(this).addClass('current');
		$('#' + activeTab).addClass('current');
	})
});

/**글쓰기**/
function writeAction(){
	var frm = document.crud_frm;
	frm.method="post";
	if(frm.chnl.value == 1 || frm.chnl.value == 2){
		frm.action="/news/write";
		frm.submit();
	}else if(frm.chnl.value == 3 || frm.chnl.value == 4){
		frm.action="/support/write";
		frm.submit();
	}
}