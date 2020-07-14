<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../include/top.jsp"></c:import>
<c:import url="../include/nav.jsp"></c:import>
<c:import url="../include/mainStart.jsp"></c:import>

	<!--  로딩	
<img src="https://tistory2.daumcdn.net/tistory/1898109/skin/images/Spinner.gif" class="bg_m"  style="left:42%; position:  absolute;" id="loding_bar">
 -->
<div class="titleWrap"
		style="background-color: #40bbe4; width: 100%; padding-top: 10px; padding-bottom: 10px; text-align: center;">
		<h1 class="title" style="color: #fff;">SFT 회원소통방</h1>
	</div>
<div class="inner">
	<h1 class="contTitle">SFT 회원소통방</h1>
	<div class="sect boardDetail">
		<div class="detailTit">
			<div>
				<h1>${pressDetail.title}</h1>
			</div>
			<span>${pressDetail.txusrnm}</span>
			<br>
			<span style="float: right;">시간 : ${pressDetail.reg_date}</span>
		</div>
		<div class="detailBody">
			<br>
			
                <textarea disabled rows="30" cols="20" id="content"  name="content"  placeholder="내용을 입력해 주세요."><c:out value="${pressDetail.content}" escapeXml="false" /></textarea>
				
		</div>
		<div class="btnWrap">
			<a href="/sft?page=Press" class="btn blueBtn">목록</a> 
			
			<c:if test="${admin eq 'Y'}">
			<a href="/sft?page=Press_delete&num=${pressDetail.num}"
				class="btn blueBtn">제거</a>
				</c:if>
		</div>

	</div>


	<!--<div class="sect navTbl">
			<ul>
				<li>
					<p>다음</p>
					<p>데이터가존재하지 않습니다.</a></p>
				</li>

				<li>
					<p>이전</p>
					<p>데이터가존재하지 않습니다.</a></p>
				</li>


			</ul>
		</div>-->
</div>
</div>

<c:import url="../include/footer.jsp"></c:import>