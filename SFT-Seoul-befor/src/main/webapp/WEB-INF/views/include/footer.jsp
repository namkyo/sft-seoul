	<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- // footer -->
</div>
<div class="talkfloat on moff" id="talkFloat">
    <div class="talk_header">
        <a href="" class="btn toplink"><span>TOP</span>
        </a>
        <div class="titset">
            <h3 class="hd3">상담지원</h3>
            <span class="call">031-932-6982</span>
        </div>
        <a href="javascript:void(0)" class="btn ctr">
        
        <!--  <h4 style="color: #fff; text-align: center;margin-top: 6px;">닫기</h4> -->
       <span></span>
        
        </a>
    </div>
    <div class="talkbody">
        <div class="talk_iconset">
            <a href="https://open.kakao.com/o/sVtn6slc" target="_blank"class="btn">
                <span class="ico ty1"></span>
                <span class="stxt">투자문의</span>
            </a>
            <a href="https://open.kakao.com/o/gtTUp3Zb" target="_blank" class="btn">
                <span class="ico ty2"></span>
                <span class="stxt">회원소통방</span>
            </a>
            <a href="http://www.sft-network.cf?refid=ca001"target="_blank"  class="btn">
                <span class="ico ty3"></span>
                <span class="stxt">사이트로가기</span>
            </a>					
        </div>
    </div>
</div>


		<div id="footer">

	        <div class="inner">
	        <div id="company">
		        <div class="inner">
			        <h1><img src="/resources/image/mainlogo.png" style="float:left;" /></h1>
			        <div style="float:right;">
				        <p>주소 : 서울시 구로구 디지털로30길 31 코오롱디지털 타워빌란트</p>
				        <p>상호 : 주식회사 동인그룹  |  대표 : 최병록</p>
				        <p>사업자등록번호 : 242-88-01799 | 고객센터 : 0319326982</p>
				        <p>Copyright ⓒ SFT-SINGAPORE FINANCE TRADE. All rights reserved</p>
			        </div>
		        </div>
	        </div>
		</div>
		<!-- end: #footer -->

				<!-- start: #siteMapwrap -->
		<div id="siteMapWrap">
			<ul class="depth1">
			
					<li><a href="/sft?page=AboutUs">SFT서울지사소개</a></li>
					<li><a href="/sft?page=Press">공지사항</a></li>
					<li><a href="/sft?page=Howtouse">기간별수익</a></li>
					<li><a href="/sft?page=Inverstment">이용방법</a></li>
					<li><a onclick="btn_confirm('https://open.kakao.com/o/sVtn6slc');"   target=”_blank”>투자문의</a></li>
					<li><a onclick="btn_confirm('https://open.kakao.com/o/gtTUp3Zb');"  target=”_blank”> 회원소통방</a></li>
					<li><a onclick="btn_confirm('http://www.sft-network.cf?refid=ca001');" target=”_blank”>사이트로가기</a></li>
		</ul>	
			<a href="#" class="siteMapClose"><img src="/resources/images/common/closeB.png" alt="닫기" /></a>
		</div>
		<!-- end: #siteMapwrap -->

    
  </div>
  <!-- end: #wrap --> 
  
  	<!-- start: #mGnb -->
	<div id="mGnb">
		<h1><img src="/resources/image/mainlogo.png" alt="" /></h1>
	<ul class="mDepth1">
					<li><a href="/sft?page=AboutUs">SFT서울지사소개</a></li>
					<li><a href="/sft?page=Press">공지사항</a></li>
					<li><a href="/sft?page=Howtouse">기간별수익</a></li>
					<li><a href="/sft?page=Inverstment">이용방법</a></li>
					<li><a onclick="btn_confirm('https://open.kakao.com/o/sVtn6slc');"   target=”_blank”>투자문의</a></li>
					<li><a onclick="btn_confirm('https://open.kakao.com/o/gtTUp3Zb');"  target=”_blank”> 회원소통방</a></li>
					<li><a onclick="btn_confirm('http://www.sft-network.cf?refid=ca001');" target=”_blank”>사이트로가기</a></li>
	</ul>

	<ul class="mUtil">
			<li><a href="/">SFT 서울지사</a></li>
		</ul>
		<a href="javascript:mGnbClose();" class="mGnbClose">모바일 메뉴 닫기</a>
	</div>
	<!-- end: #mGnb -->
<script>
var gnbMobileLink = function() {

	var _target = $('nav .navlist > li > a');
	$('nav .navlist .dp2').removeAttr('style');
	_target.on("click", function(e) {
		var _this = $(this);
		var _thispt = _this.parent();
		if(_thispt.hasClass("on")) {
			_thispt.removeClass("on");
			_thispt.find(".dp2").slideUp();
		} else {
			$('nav .navlist > li').removeClass("on");
			_thispt.addClass("on");
			$('nav .navlist .dp2').slideUp();
			_thispt.find(".dp2").slideDown();
		}
	});
};
var gnbInit = function() {
	var _target = $('nav .navlist > li > a');
	$('nav').removeClass("on");
	_target.parent().each(function(index, element) {
		$(element).find(".dp2").removeAttr('style');
		var dp2OtherWd = parseInt($(element).find(".dp2").outerWidth());
		$(element).find(".dp2").css("margin-left", -(parseInt(dp2OtherWd / 2)));
	});

	_target.parent().hover(
		function() {
			var _this = $(this);
			var _thispt = _this.parent();

			$('nav .navlist > li').removeClass("on");
			_this.addClass("on");
		},
		function() {
			var _this = $(this);
			var _thispt = _this.parent();
			_this.removeClass("on");
		}
	);
};


function gnbResponse(e) {

	var winWH = $(window).width();
	var natTargetOrigin = $('nav .navlist > li > a');
	if(winWH < 1280) {
		natTargetOrigin.off("click");
		gnbMobileLink(e);
		natTargetOrigin.parent().off("mouseenter");
		natTargetOrigin.parent().each(function(index, element) {
		 	$(element).find(".dp2").parents("li").addClass("act");
		});
		//$("#talkFloat").removeClass("on");
		//$("#talkFloat").addClass("mb");
		
	} else {
		//gnbInit();
		natTargetOrigin.off("click");
		natTargetOrigin.parent().removeClass("act");
		//$("#talkFloat").addClass("on");
	}
	
	if(winWH < 768) { 
		$("#talkFloat").addClass("on");
		$("#talkFloat").addClass("moff");
	} else { 
		//$("#talkFloat").addClass("on");
		$("#talkFloat").removeClass("moff");
		$("#talkFloat").removeClass("mon");
	} 
}

$('#talkFloat .btn.ctr').on("click", function(e) {
	e.preventDefault();
	var _targetOrigin = $("#talkFloat");
	if(_targetOrigin.hasClass("on")) {
			_targetOrigin.removeClass("on");
			$("#talkFloat").removeClass("moff");
			$("#talkFloat").addClass("mon");
			$('container').off("touchmove");
	} else {
		_targetOrigin.addClass("on");
			$("#talkFloat").addClass("moff");
			$("#talkFloat").removeClass("mon");
		

		$('container').on("touchmove", function(e) {
			e.preventDefault();
		});
	}
});

$('#talkFloat .btn.toplink').on("click", function(e) {
	e.preventDefault();
	var body = $("html, body");
	body.stop().animate({scrollTop:0});
});


$(window).resize(function() {
	gnbResponse();
});

</script>
</body>
</html>
<!-- footer //  -->