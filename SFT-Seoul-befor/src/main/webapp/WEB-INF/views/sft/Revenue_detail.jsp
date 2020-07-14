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
		<h1 class="title" style="color: #fff;">수익 올리기</h1>
	</div>
    <div class="inner">
      <h1 class="contTitle">SFT 서울지사 만의 수익인증</h1>
      <div class="sect boardDetail">
		<div class="detailTit">
			<div>
				<h1>${revenueDetail.title}</h1>
			</div>
			<span>${revenueDetail.reg_date}</span>
		</div>
		<div class="detailBody" >
		
		<c:forEach var="revenueDetailFiles" items="${revenueDetailFiles}"
				varStatus="status">

				<img src="${revenueDetailFiles.file_path}" class="bg_m">
			</c:forEach>
			
			<br>
			
                <textarea disabled rows="20" cols="20" id="content"  name="content"  placeholder="내용을 입력해 주세요."><c:out value="${revenueDetail.content}" escapeXml="false" /></textarea>
				
			<br>
			
		</div>
		<div class="btnWrap">
			<a href="/sft?page=Revenue" class="btn blueBtn">목록</a> 
			<c:if test="${admin eq 'Y'}">
			<a href="/sft?page=Revenue_delete&num=${revenueDetail.num}" class="btn blueBtn">제거</a>
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
    </div></div>

<c:import url="../include/footer.jsp"></c:import>