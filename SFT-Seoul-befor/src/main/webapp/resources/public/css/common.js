$(document).ready(function(){

	/*gnb open/close*/
    $(".gDepth1").mouseenter(function(){
		$("#dimed").fadeIn(300);
		$(".gDepth2").stop().animate({height: 260}, 300);
		$("#gnbBg").stop().animate({height: 260}, 300);
		
	}).mouseleave(function(){
		$("#dimed").fadeOut(300);
		$(".gDepth2").stop().animate({height: 0}, 300);
		$("#gnbBg").stop().animate({height: 0}, 300);
	});
	
	/*gnb tabkey focusin/focusout*/
	$(".gDepth1 > li").focusin(function(){
		$("#dimed").css("display","block");
		$(".gDepth2").stop().animate({height: 260}, 300);
		$("#gnbBg").stop().animate({height: 260}, 300);
	}).focusout(function(){
		$("#dimed").css("display","none");
		$(".gDepth2").stop().animate({height: 0}, 300);
		$("#gnbBg").stop().animate({height: 0}, 300);
	});

	/*mGnb open*/
	$("#mMenu").click(function(){
		$("#mGnb").css("display","block");
		$("#mGnb").animate({right: 0}, 300);
		$("#wrap").animate({left: "-80%"}, 300);
		$("#dimed").fadeIn(300);
	});

	/*mGnb menu*/
	$(".mDepth2 > li > a").click(function(){
		$(".mDepth2 > li > a").removeClass("active");
		$(".mDepth3 li").removeClass("active");
		var mDepth3 = $(this).siblings("ul").css("display");
		if( mDepth3 == "block" ) {
			$(this).siblings("ul").slideUp(300);
		} else {
			$(this).addClass("active");
			$(".mDepth3").slideUp(300);
			$(this).siblings("ul").slideDown(300);
			$(this).siblings("ul").find("li").addClass("active");
		}
	});
	
	/*mGnb*/
	var hasDepth3 = $(".mDepth3").size();
	if(hasDepth3 > 0) {
		$(".mDepth3").siblings("a").addClass("plus");
		$(".mDepth3").siblings("a").attr("href", "#!");
	}

	/*siteMap*/
	var hasDepth4 = $("#siteMapWrap .depth4").size();
	if(hasDepth4 > 0) {
		$("#siteMapWrap .depth4").siblings("a").addClass("plus");
		$("#siteMapWrap .depth4").siblings("a").attr("href", "#!");
	}

	$("#siteMapWrap .depth3 > li > a.plus").click(function(){
		var dpDepth4 = $(this).siblings("ul").css("display");
		if(dpDepth4 == "block") {
			$(this).removeClass("active");
			$("#siteMapWrap .depth4").slideUp(300);
		} else {
			$(this).addClass("active");
			$("#siteMapWrap .depth4").slideUp(300);
			$(this).siblings("ul").slideDown(300);
		}
	});		

	$("#siteMap").click(function(){
		$("#dimed").fadeIn(300);
		$("#siteMapWrap").fadeIn(300);
	});

	$(".siteMapClose").click(function(){
		$("#dimed").fadeOut(300);
		$("#siteMapWrap").fadeOut(300);
	});
	
	/*family toggle*/
	$("#family > a").click(function(){
		var fList = $("#family ul").css("display");
		if( fList == "block" ) {
			$(this).removeClass("active");
			$(this).siblings("ul").slideUp(300);
		} else {
			$(this).addClass("active");
			$(this).siblings("ul").slideDown(300);
		}
		return false;
	});
	
	/*go top*/
	$("#top").click(function(){
		$("html, body").animate({scrollTop:0}, 500);
	});

	$("#dimed").click(function(){
		mGnbClose();
		$(".alertDiv").fadeOut(300);
	});
	
	/*nav toggle*/
	$("#nav .depth1 > li:last-child > a").click(function(){
		var navDepth2 = $("#nav .depth2").css("display");
		if(navDepth2 == "block") {
			$(this).removeClass("navActive");
			$("#nav .depth2").slideUp(300);
		} else {
			$(this).addClass("navActive");
			$("#nav .depth2").slideDown(300);
		}
		return false;
	});

	$('html').click(function(e) {
        if($(e.target).hasClass("navAcrtive")) {
        }else{
			$("#nav .depth1 > li:last-child > a").removeClass("navActive");
			$("#nav .depth2").slideUp(300);
        }
    });

	/*top3*/
	$(".top3Wrap > ul").bxSlider({
		auto: true,
		autoControls : false,
		speed : 500,
		pause : 6000,
		pager : true,
		controls : true,
		minSlides : 3,
		moveSlides : 3,
		adaptiveHeight : true
	});

	/*notice*/
	$(".notice h1").click(function(){
		var notiList = $(".notice ul").css("display");
		if(notiList == "block") {
			$(".notice").removeClass("active");
			$(".notice ul").slideUp(300);
		} else {
			$(".notice").addClass("active");
			$(".notice ul").slideDown(300);
		}
	});
	
	/*toggle*/
	$(".toggleList dt a").click(function(e){
		e.preventDefault();
		$(".toggleList dl").removeClass("active");
		var faqDd = $(this).parent().siblings("dd").css("display");
		if( faqDd == "block" ) {
			$(this).parent().siblings("dd").slideUp(300);
		} else {
			$(this).parent().parent().addClass("active");
			$(".toggleList dd").slideUp(300);
			$(this).parent().siblings("dd").slideDown(300);
		}
	});
	
	/*tabs li click*/
	$(".pdtTabs li a").click(function(e){
		e.preventDefault();
		$(".pdtTabs li").removeClass("active");
		$(this).parent().addClass("active");
	});

	/*tabs li click*/
	$(".pdtTerm li a").click(function(e){
		e.preventDefault();
		$(".pdtTerm li").removeClass("active");
		$(this).parent().addClass("active");
	});
	
	/*pdtType li click*/
	$(".pdtType li").click(function(){
		$(".pdtType li").removeClass("active");
		$(this).addClass("active");
	});

	/*pdtSubCtgr li click*/
	$(".pdtSubCtgr > ul > li").click(function(e){
		e.preventDefault();
		$(".pdtSubCtgr > ul > li").removeClass("active");
		$(this).addClass("active");
	});

	/*jquery ui selectbox*/
	$( ".select" ).selectmenu();

	/*datepicker ui*/
	$( ".datepicker" ).datepicker({
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        dayNames: ['일','월','화','수','목','금','토'],
        dayNamesShort: ['일','월','화','수','목','금','토'],
        dayNamesMin: ['일','월','화','수','목','금','토'],
		showOn: "button",
		buttonImageOnly: true,
        buttonImage: "/e5/etn/images/contents/cal.gif",
        dateFormat: "yy-mm-dd"
	});
	
	var pdtTabsSize = $(".tabs4.pdtTabs li").size();
		
	if( pdtTabsSize == 2 ) {
		$(".tabs4.pdtTabs li").css("width","50%");
	} if( pdtTabsSize == 3 ) {
		$(".tabs4.pdtTabs li").css("width","33.3%");
	} else if ( pdtTabsSize == 4 ) {
		$(".tabs4.pdtTabs li").css("width","25%");
	} else if ( pdtTabsSize == 5 ) {
		$(".tabs4.pdtTabs li").css("width","33.3%");
	}

});


$(window).scroll(function(){
	var sct = $(window).scrollTop();
	var sct2 = $(document).height();
	
	/*nav position*/
	if(sct <= 345) {
		$("#nav").removeClass("active");
	} else {
		$("#nav").addClass("active");
	}
	
	/*top button position*/
	if(sct >= 100) {
		$("#top").fadeIn(300);
		$("#top").css("bottom","50px");
		if(sct >= (sct2 - 1000)) {
			$("#top").css("bottom","258px");
		}
	} else {
		$("#top").hide();
	}
});

/*mGnb close*/
function mGnbClose() {
	$("#mGnb").animate({right: "-80%"}, 300);
	$("#mGnb").fadeOut(300);
	$("#wrap").animate({left: 0}, 300);
	$("#dimed").fadeOut(300);
	$(".mDepth2 > li > a").removeClass("active");
	$(".mDepth3").slideUp(300);
}

/*mGnb display*/
$( window ).resize(function() {
	var winWidth = $( window ).width();
	if(winWidth >= 1024) {
		mGnbClose();
	}
});

/*product list type*/
function pdtType(type){
	$(".pdtItem").attr("class", "pdtItem pdt" + type);
};

/*product detail tab*/
function detailTabs(id) {	
	$(".tabCont").removeClass("active");
	$(id).addClass("active");
}



