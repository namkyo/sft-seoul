<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="./include/top.jsp"></c:import>
<c:import url="./include/nav.jsp"></c:import>
<c:import url="./include/mainStart.jsp"></c:import>

<script type="text/javascript">
	$(document).ready(function() {
		var filter = "win16|win32|win64|mac|macintel";
		if (navigator.platform) {
			if (filter.indexOf(navigator.platform.toLowerCase()) < 0) { //mobile 
				//alert('mobile 접속');
				var str='<div class="quickMenus" style="font-family:font04; font-size:17px; text-align: center; line-height: 120%;">';
				str += '<table><colgroup><col width="30%" /><col width="40%" /><col width="30%"/></colgroup><tr>';
				str += '<td style="width:1024px; background-color: #6367cb;"><div> ';
				str += '<a href="http://sft-network.cf?refid=서울지사" onfocus="this.blur()" rel="nofollow" style="text-decoration: none; color:#FFFFFF;" target="_blank" title="투자받기" style="color: #ffcc00;"><img src="/resources/image/mainlogo.png" style="width: 100px;"/><p>SFT</p><p>투자하기</p></a>';
				str += '</div></td>';
				str += '<td style="background-color: #464749;"><div  > ';
				str += '<a href="/sft?page=Inverstment" target="_blank"   onfocus="this.blur()" rel="nofollow" style="text-decoration: none; color:#FFFFFF;" title="이용방법" style="color: #ffcc00;"><img src="/resources/image/mainlogo.png" style="width: 100px;"/><p>SFT</p><p>이용방법</p></a>';
				str += '</div></td>';
				str += '<td style="background-color: #376891; height: 70px;"><div> ';
				str += '<a href="https://open.kakao.com/o/sOKpvDjc" onfocus="this.blur()" rel="nofollow" style="text-decoration: none; color:#FFFFFF;" target="_blank" title="P2P 투자 상담" style="color: #ffcc00;"><img src="/resources/image/mainlogo.png" style="width: 100px;"/><p>P2P</p><p>투자문의</p></a>';
				str += '</div></td>';
				str += '</tr></div>';
				$('#app_popup')[0].innerHTML = str;
			} else { //pc 
				//alert('pc 접속');
				var str='<div class="quickMenus" style="font-family:font04; font-size:17px; text-align: center; line-height: 120%;">';
				str += '<table><colgroup><col width="30%" /><col width="40%" /><col width="30%"/></colgroup><tr>';
				str += '<td style="width:1024px; background-color: #6367cb;"><div> ';
				str += '<a href="http://sft-network.cf?refid=서울지사" onfocus="this.blur()" rel="nofollow" style="text-decoration: none; color:#FFFFFF;" target="_blank" title="투자받기" style="color: #ffcc00;"><img src="/resources/image/mainlogo.png" style="width: 100px;"/><p>SFT</p><p>투자하기</p></a>';
				str += '</div></td>';
				str += '<td style="background-color: #464749;"><div  > ';
				str += '<a href="/sft?page=Inverstment" target="_blank"   onfocus="this.blur()" rel="nofollow" style="text-decoration: none; color:#FFFFFF;" title="이용방법" style="color: #ffcc00;"><img src="/resources/image/mainlogo.png" style="width: 100px;"/><p>SFT</p><p>이용방법</p></a>';
				str += '</div></td>';
				str += '<td style="background-color: #376891; height: 70px;"><div> ';
				str += '<a href="https://open.kakao.com/o/sOKpvDjc" onfocus="this.blur()" rel="nofollow" style="text-decoration: none; color:#FFFFFF;" target="_blank" title="P2P 투자 상담" style="color: #ffcc00;"><img src="/resources/image/mainlogo.png" style="width: 100px;"/><p>P2P</p><p>투자문의</p></a>';
				str += '</div></td>';
				str += '</tr></div>';
				$('#app_popup')[0].innerHTML = str;
			}
		}
	});
</script>
<div id="app_popup">
</div>

<!-- end: .mainVisual -->
<div class="titleWrap inner"
	style="background-color: #40bbe4; width: 100%; padding-top: 10px; padding-bottom: 10px; text-align: center;">
	<h1 class="title" style="color: #fff;">SFT - 서울지사</h1>
	<p class="subTitle" style="color: #fff;">동/영/상 가/이/드</p>
</div>

<div class="quickMenu inner"  style="font-family: 'font02';">

<!-- 
	<ul>
		<li><div>
				<h3 style="letter-spacing: 5px;">회원가입편</h3>
			</div> 
			<iframe width="100%" height="250" src="https://www.youtube.com/embed/F_PzKiM-fz8" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				</li>
		<li>
			<div>
				<h3 style="letter-spacing: 5px;">투자가이드편</h3>
			</div> 
			<iframe width="100%" height="250" src="https://www.youtube.com/embed/YTW96neyYCE" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</li>
		<li>
			<div>
				<h2 style="letter-spacing: 5px;">매매가이드편</h2>
			</div><iframe width="100%" height="250" src="https://www.youtube.com/embed/AmpJUxH-FoA" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</li>
		<li>
			<div>
				<h2 style="letter-spacing: 5px;">유튜버</h2>
			</div> <iframe width="100%" height="250"
				src="https://www.youtube.com/embed/RfpGUX9Egm4" frameborder="0"
				allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
				allowfullscreen></iframe>
		</li>

	</ul>
	 -->
</div>



<!--  
<div class="titleWrap inner" style="background-color: f3f3f5; width:100%; padding-top: 10px; padding-bottom: 10px; text-align: center;">
          <h1 class="title" style="color: #000;">관련페이지</h1>
        </div>
        

 <div class="quickMenu inner">
	<ul>
		<li><a href="https://blog.naver.com/john7367" target="_blank"> <span></span>
				<div>
					<h2>블로그</h2>
				</div>
		</a></li>
		<li><a
			href="https://www.instagram.com/fxcity_jongro/?hl=ko" target="_blank">
				<span></span>
				<div>
					<h2>인스타그램</h2>
				</div>
		</a></li>
		<li><a
			href="https://facebook.com/GSBM-종로점-108794157443831" target="_blank">
				<span></span>
				<div>
					<h2>페이스북 </h2>
				</div>
		</a></li>
		<li><a
			href="https://www.youtube.com/channel/UCJccXDx_q4Ptt68n0ALXuHg?view_as=subscriber" target="_blank">
				<span></span>
				<div>
					<h2>YOUTUBE</h2>
				</div>
		</a></li>
	</ul>
</div>
-->
      <!-- end: .quickMenu -->      


<div class="mainBoard inner">
	<div class="mainNotice">
		<h1 class="title">
			SFT 서울지사<a href="/">더보기</a>
		</h1>
		<div>
			<a href="/sft?page=Howtouse">
				<h2>이용안내</h2>
				<p>이용 방법에 대한 궁금하신 점이 있으시면</p>
                 <p>바로가기을 누르시면 상세히</p> 
                 <p>알려드리도록 하겠습니다.</p> 
                 
                 
                 <span>바로가기</span>
			</a>
		</div>
		<div>
			<a href="/sft?page=Press">
				<h2>주요공지사항</h2>
				<p>주요 변경 사항이나 또는 긴급공지</p>
				<p>, 기타 점검 사항을</p> 
				<p>확인 하시려면 클릭하여 주시기 바랍니다.</p> 
				
				<span>바로가기</span>
			</a>
		</div>
	</div>
	<!-- end: .mainNotice -->

	<div class="mainEvent inner">
		<img
			src="/resources/image/mainlogo.png" alt="" />
			<div>
				<h1>고객센터</h1>
				<p>- 고객상담시간 09:00 ~ 05:00<br>
				- 거래가능시간 07:20 ~ 05:00</p>
			</div>
		
	</div>



	<!-- end: .mainEvent -->
</div>
<!-- end: .mainBoard -->

<c:import url="./include/footer.jsp"></c:import>