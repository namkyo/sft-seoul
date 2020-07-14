<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../include/top.jsp"></c:import>
<c:import url="../include/nav.jsp"></c:import>
<c:import url="../include/mainStart.jsp"></c:import>
<div class="titleWrap inner"
	style="background-color: #5d56a3; width: 100%; padding-top: 10px; padding-bottom: 10px; text-align: center;">
	<h1 class="title" style="color: #fff;">GSBM 이용방법</h1>
</div>


<div class="inner">
 	<div class="tabs tabs3">
        <div >
          <ul>
            <li class=""><a href="/fx?page=Howtouse">회원가입 방법</a></li>
            <li class=""><a href="/fx?page=Howtouse2">입출금신청 방법</a></li>
			 <li class="active"><a href="/fx?page=Howtouse3">렌트거래 방법</a></li>
          </ul>
        </div>
     </div>
      
	<h1 class="contTitle">(3) 렌트거래 방법</h1>
	<div class="formWrap">
		<div class="sect">
		
			<h3>상단메뉴 투자하러가기 버튼 클릭</h3>
			
			
			<br> <img src="/resources/image/Howtouse09.png"
				style="width: 400px;" class="bg_m" /> <br>
			<p>렌트거래</p>
			<p>1. 로그인 후 메뉴바에서 FX 거래 메뉴 클릭</p>
			
			
			<br> <img src="/resources/image/Howtouse09.png"
				style="width: 400px;" class="bg_m" /> <br>
			<p>매수 신청</p>
			<p>1. 매수 / 매도 할 금액 설정</p>
			<p>2. 매수 / 매수신청 버튼 클릭(구간당 최대 lot은 400개)</p>
			
			<br> <img src="/resources/image/Howtouse09.png"
				style="width: 400px;" class="bg_m" /> <br>
			<p>손익분배계약서</p>
			<p>1. 손익분배계약서 "매수 / 매도 수익계약" 버튼 클릭</p>
			
			<br> <img src="/resources/image/Howtouse09.png"
				style="width: 400px;" class="bg_m" /> <br>
			<p>거래목록</p>
			<p>1. 거래내역조회 메뉴를 통해 거래 결과를 확인 할 수 있습니다.</p>
		</div>
	</div>
</div>
<c:import url="../include/footer.jsp"></c:import>