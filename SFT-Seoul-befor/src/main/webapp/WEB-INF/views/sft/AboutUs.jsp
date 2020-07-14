<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../include/top.jsp"></c:import>
<c:import url="../include/nav.jsp"></c:import>
<c:import url="../include/mainStart.jsp"></c:import>
<div class="titleWrap inner"
	style="background-color: #40bbe4; width: 100%; padding-top: 10px; padding-bottom: 10px; text-align: center;">
	<h1 class="title" style="color: #fff;">SFT 서울지사</h1>
</div>
<div class="inner">
	<div class="sect">
		<div class="constart">
			<h3>SFT 서울지사소개</h3>
			<p>한달 50%~60% 고금리 P2P투자</p>
			<p>원금손실과 연체가능성이 있는 P2P 투자의 단점을 없앤 적금형 투자상품</p>
		</div>
	</div>
<!-- 
	<h1 class="contTitle">전경</h1>
	<img src="/resources/image/company01.jpg" class="bg_m" style="width: 50%;"/> -->

<!-- 
	<h1 class="contTitle">위치</h1>
	<div id="map" style="width:750px;height:350px; " class="bg_m" style="width: 50%;" ></div> -->
</div>

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=95a75d5f9d0e8f179eb813984a69d690"></script>

	<script>

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(37.570384, 126.933450), // 지도의 중심좌표
		        level: 3, // 지도의 확대 레벨
		        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
		    }; 

		// 지도를 생성한다 
		var map = new kakao.maps.Map(mapContainer, mapOption); 

		// 지도에 마커를 생성하고 표시한다
		var marker = new kakao.maps.Marker({
		    position: new kakao.maps.LatLng(37.570384, 126.933450), // 마커의 좌표
		    map: map // 마커를 표시할 지도 객체
		});

		// 마커 위에 표시할 인포윈도우를 생성한다
		var infowindow = new kakao.maps.InfoWindow({
		    content : '<div style="padding:5px; text-align: center;">FX시티</div>' // 인포윈도우에 표시할 내용
		}); 

		// 인포윈도우를 지도에 표시한다
		infowindow.open(map, marker);
	</script>
<c:import url="../include/footer.jsp"></c:import>