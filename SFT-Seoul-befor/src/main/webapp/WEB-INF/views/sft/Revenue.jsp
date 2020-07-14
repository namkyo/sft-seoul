<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../include/top.jsp"></c:import>
<c:import url="../include/nav.jsp"></c:import>
<c:import url="../include/mainStart.jsp"></c:import>



<script>
	//온로드
	$(document).ready(
	//메세지 받기
	function() {
		var msg = $.fn.request('msg');
		if (msg != 1) {
			alert(msg);
		}
	});
</script>



<div class="mainWeberInfo">
	<div class="titleWrap"
		style="background-color: #40bbe4; width: 100%; padding-top: 10px; padding-bottom: 10px; text-align: center;">
		<h1 class="title" style="color: #fff;">SFT 서울지사 만의 수익인증</h1>
	</div>
	<div class="inner">
		<ul>
			<c:forEach var="revenueList" items="${revenueList}"
				varStatus="status">
				<li style="background: #fff;">
				<a href="/sft?page=Revenue_detail&&num=${revenueList.num}">
					<img src="${revenueList.file_path}"
						style="width: 100%;" class="bg_m" />
						<h2>${revenueList.title}</h2>
				</a></li>
			</c:forEach>

		</ul>
			<c:if test="${admin eq 'Y'}">

			</c:if>
				<div class="srch sectIn" style="background: #FFF;">
					<a href="/sft?page=Revenue_create" class="btn srchBtn"
						style="float: right;">글쓰기</a>
				</div>


			<!-- 
			<h1 class="contTitle">2019-10-28 리딩결과</h1>
			<h3></h3>
			<img src="/resources/image/Revenue01.png" style="width: 100%;"
				class="bg_m" />
			<h1 class="contTitle">2019-12-30 리딩결과 (1)</h1>
			<h3></h3>
			<img src="/resources/image/Revenue02.png" style="width: 100%;"
				class="bg_m" />
			<h1 class="contTitle">2019-12-30 리딩결과 (2)</h1>
			<h3></h3>
			<img src="/resources/image/Revenue03.png" style="width: 100%;"
				class="bg_m" />
			<h1 class="contTitle">2019-12-30 리딩결과 (3)</h1>
			<h3></h3>
			<img src="/resources/image/Revenue04.png" style="width: 100%;"
				class="bg_m" />
				
				 -->
	</div>
</div>
<c:import url="../include/footer.jsp"></c:import>