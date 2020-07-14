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
        buttonImage: "/resources/public/images/images/contents/cal.gif",
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

/*
*  숫자만 입력.. 단독으로 사용시.
*/
function onlyNumber(obj) {
	$(obj).keyup(function(){
		 $(this).val($(this).val().replace(/[^0-9]/g,""));
	}); 
}

/*************************************************************************
함수명 : rmChar
기 능 : 문자열의 모든 특정 문자 제거 처리 함수
인 수 : strString - 체크하려는 스트링
strChar - 삭제하고자 하는 문자
리턴값 :
**************************************************************************/
function rmChar(strString, strChar) {
	var i;
	var delChar = strChar;
	var strTmp = "";

	for(i=0;i<strString.length;i++){
		if(strString.charAt(i) != delChar) {
			strTmp += strString.charAt(i);
		}
	}

	return strTmp;
}

/**
 * 바이트 문자 입력가능 문자수 체크
 * 
 * @param id : tag id 
 * @param title : tag title
 * @param maxLength : 최대 입력가능 수 (byte)
 * @returns {Boolean}
 */
function maxLengthCheck(id, title, maxLength){
     var obj = $("#"+id);
     if(maxLength == null) {
         maxLength = obj.attr("maxLength") != null ? obj.attr("maxLength") : 1000;
     }
     
     if(Number(byteCheck(obj)) > Number(maxLength)) {
         alert(title + "이(가) 입력가능문자수를 초과하였습니다.\n영문 및 숫자 : " + parseInt(maxLength+10) + " \n한글, 한자, 기타 특수문자 : " + parseInt((maxLength+10)/2, 10) + "자 까지 가능합니다..");
         return false;
     } else {
         return true;
    }
}
 
/**
 * 바이트수 반환  
 * 
 * @param el : tag jquery object
 * @returns {Number}
 */
function byteCheck(el){
    var codeByte = 0;
    for (var idx = 0; idx < el.val().length; idx++) {
        var oneChar = escape(el.val().charAt(idx));
        if ( oneChar.length == 1 ) {
            codeByte ++;
        } else if (oneChar.indexOf("%u") != -1) {
            codeByte += 2;
        } else if (oneChar.indexOf("%") != -1) {
            codeByte ++;
        }
    }
    return codeByte;
}

/**
 * 전화번호 자동 hyphen
 * 휴대폰, 유선전화
 */
function inputHyphenPhone(obj,division) {

    var objNumber = obj.value.replace(/[^0-9]/g, "");
    var pn = "";
	
	if(objNumber.length == 0){
		alert("숫자만 입력하세요");   
		obj.value = "";
		return false;
	}
	
	if(division == 'hp'){
		if(objNumber.length < 11) {
			pn += objNumber.substr(0, 3);
			pn += "-";
			pn += objNumber.substr(3, 3);
			pn += "-";
			pn += objNumber.substr(6);

		} else{
			pn += objNumber.substr(0, 3);
			pn += "-";
			pn += objNumber.substr(3, 4);
			pn += "-";
			pn += objNumber.substr(7);
		}
	}else{
		if(objNumber.length < 4) {
			return objNumber;
		} else if(objNumber.length < 8) {
			pn += objNumber.substr(0, 3);
			pn += "-";
			pn += objNumber.substr(3,4);
			alert("pn : "+ pn);
		} else if(objNumber.length == 8) {
			pn += objNumber.substr(0, 4);
			pn += "-";
			pn += objNumber.substr(4, 4);
		} else if(objNumber.length == 12) {
			pn += objNumber.substr(0, 4);
			pn += "-";
			pn += objNumber.substr(4, 4);
			pn += "-";
			pn += objNumber.substr(8 , 4);
		} else {
			if(objNumber.substr(0, 2) == 02){
				
				if(objNumber.length == 9) {
					pn += objNumber.substr(0, 2);
					pn += "-";
					pn += objNumber.substr(2, 3);
					pn += "-";
					pn += objNumber.substr(5);
				}else{
					pn += objNumber.substr(0, 2);
					pn += "-";
					pn += objNumber.substr(2, 4);
					pn += "-";
					pn += objNumber.substr(6);
				}
			}else{
				if(objNumber.length == 10) {
					pn += objNumber.substr(0, 3);
					pn += "-";
					pn += objNumber.substr(3, 3);
					pn += "-";
					pn += objNumber.substr(6);
				}else{
					pn += objNumber.substr(0, 3);
					pn += "-";
					pn += objNumber.substr(3, 4);
					pn += "-";
					pn += objNumber.substr(7);
				}
			}
		}
	}
    
    obj.value = pn;
}

/**
 * 권한부여 체크박스 체크시 전체체크
 */
function addInfoChk(obj,nm){
	if($(obj).is(":checked")){
		$("."+nm).prop("checked",true);
		$("."+nm).val("Y");
	}else{
		$("."+nm).prop("checked",false);
		$("."+nm).val("N");
	}
}

/**
 * 아이디에 영어와 숫자만 
 */
function fcheckData(obj){	
	 var validchr ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	 var flag = false;
	 var strtmp1;
	 var strtmp2;	     

	 for(var i=0 ;obj.length > i;i++){
		 strtmp1 = obj.substring(i,i+1);
		 flag = false;
		 for (var j=0; validchr.length > j ;j++)
		 {
			 strtmp2 = validchr.substring(j,j+1)

			 if (strtmp2 == strtmp1)
			 {
				 flag = true;
			 }
		 }
		 if (flag == false)
		 {
			 break;
		 }			
	 }
	 return flag;
}

/**
 * 비밀번호 유효성검사
 */
function CehckPassWord(hUSER_ID, hPASSWORD, hRE_PASSWORD)
{
	if(hPASSWORD.length<10)
	{
		alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 10자리 이상 입력해주세요.");
		return false;
	}
	if(!hPASSWORD.match(/[a-zA-Z]/))
	{
		alert("비밀번호에는 영문이 포함되어야 합니다..");
		return false;
	}
	if(!hPASSWORD.match(/[!,@,#,$,%,^,&,*,?,_,~]/))
	{
		alert("비밀번호에는 특수문자가 포함되어야 합니다..");
		return false;
	}
	if(!hPASSWORD.match(/[0-9]/))
	{
		alert("비밀번호에는 숫자가 포함되어야 합니다.");
		return false;
	}    
	if(hPASSWORD != hRE_PASSWORD)
	{
		alert("입력하신 비밀번호와 비밀번호확인이 일치하지 않습니다");
		return false;
	}
	if(hUSER_ID.indexOf(hPASSWORD) > -1)
	{
		alert("비밀번호에 아이디를 사용할 수 없습니다.");
		return false;
	}		
	var SamePass_0 = 0; //동일문자 카운트
	var SamePass_1 = 0; //연속성(+) 카운드
	var SamePass_2 = 0; //연속성(-) 카운드

	var chr_pass_0;
	var chr_pass_1;
	var chr_pass_2;

	for(var i=0; i < hPASSWORD.length; i++)
	{
		chr_pass_0 = hPASSWORD.charAt(i);
		chr_pass_1 = hPASSWORD.charAt(i+1);
		
		//동일문자 카운트
		if(chr_pass_0 == chr_pass_1)
		{
			SamePass_0 = SamePass_0 + 1
		}
		chr_pass_2 = hPASSWORD.charAt(i+2);
		//연속성(+) 카운드
		if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == 1 && chr_pass_1.charCodeAt(0) - chr_pass_2.charCodeAt(0) == 1)
		{
			SamePass_1 = SamePass_1 + 1
		}			
		//연속성(-) 카운드
		if(chr_pass_0.charCodeAt(0) - chr_pass_1.charCodeAt(0) == -1 && chr_pass_1.charCodeAt(0) - chr_pass_2.charCodeAt(0) == -1)
		{
			SamePass_2 = SamePass_2 + 1
		}
	}
	if(SamePass_0 > 1)
	{
		alert("동일문자를 3번 이상 사용할 수 없습니다.");
		return false;
	}	   
	if(SamePass_1 > 1 || SamePass_2 > 1 )
	{
		alert("연속된 문자열(123 또는 321, abc, cba 등)을\n 3자 이상 사용 할 수 없습니다.");
		return false;
	}
 return true;
}


/*
 *  NEWS 
 */ 
/*  검색하기  */
function News_search(){
	search_frm.action="/news/news";
	search_frm.submit();
}
function News_Detail(no){
	search_frm.num.value = no;
	search_frm.action="/news/newsview";
	search_frm.submit();
}
function News_wr(){
	search_frm.chnl.value = "1";
	search_frm.action="/news/writeForm";
	search_frm.submit();
}
function News_delete(){
	var con = confirm("정말로 삭제하시겠습니까?");
	if(con == true){
		crud_frm.action = "/news/delete";
		crud_frm.submit();
	}
}
function News_editopen(){
	crud_frm.chnl.value = "2";
	crud_frm.action = "/news/writeForm";
	crud_frm.submit();
}

/*
 *  notice 
 */
/*  검색하기  */
function Notice_search(){
	search_frm.action="/support/notice";
	search_frm.submit();
}
function Notice_Detail(no){
	search_frm.num.value = no;
	search_frm.action="/support/noticeview";
	search_frm.submit();
}
function Notice_wr(){
	search_frm.chnl.value = "3";
	search_frm.action="/support/writeForm";
	search_frm.submit();
}
function Notice_delete(){
	var con = confirm("정말로 삭제하시겠습니까?");
	if(con == true){
		crud_frm.action = "/support/delete";
		crud_frm.submit();
	}
}
function Notice_editopen(){
	crud_frm.chnl.value = "4";
	crud_frm.action = "/support/writeForm";
	crud_frm.submit();
}

