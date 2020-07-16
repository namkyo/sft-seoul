<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../include/top.jsp"></c:import>
<c:import url="../include/nav.jsp"></c:import>

			
		<!--  로딩	
<img src="https://tistory2.daumcdn.net/tistory/1898109/skin/images/Spinner.gif" class="bg_m"  style="left:42%; position:  absolute;" id="loding_bar">
 -->
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

<div class="titleWrap"
		style="background-color: #40bbe4; width: 100%; padding-top: 10px; padding-bottom: 10px; text-align: center;">
		<h1 class="title" style="color: #fff;">SFT 공지사항</h1>
	</div>
<div class="inner">
	<h1 class="contTitle">SFT 공지사항</h1>
<!-- 
	<div class="srch sectIn">
		<form name="search_frm" id="search_frm" method="post">
			<input type="hidden" id="num" name="num" /> <input type="hidden"
				id="chnl" name="chnl" /> <input type="text" id="keyword"
				name="keyword" value="" title="검색어 입력"
				placeholder="제목 또는 내용을 입력해 주세요." maxlength="12" value=""
				onkeydown="if(event.keyCode==13) javascript:News_search();" /> <a
				href="javascript:News_search()" class="btn srchBtn">검색</a>
		</form>
	</div> -->

	<div id="ContentPlaceHolder1_UpdatePanel1" class="sect">

		<!-- 리스트구간 -->
		<ul class="boardList ntcList">
			<!-- 리스트 -->
			<c:forEach var="pressList" items="${pressList}">
				<li><span>${pressList.num}</span>
					<div>
						<h1>
							<a href="/sft?page=Press_detail&&num=${pressList.num}"><strong>${pressList.title}</strong></a>
						</h1>
					</div>
					<span class="file"></span> <span>${pressList.reg_date}</span></li>
			</c:forEach>


		</ul>
		<!-- 리스트구간 -->

		<c:if test="${admin eq 'Y'}">
		</c:if>
			<div class="srch sectIn" style="background: #FFF;">
				<a href="/sft?page=Press_create" class="btn srchBtn"
					style="float: right;">글쓰기</a>
			</div>


	</div>
	
	
	
	<!-- 리스트하단 -->
				<div id="ContentPlaceHolder1_up_morelist" style="position:relative;">
					
					<div id="div_morelist" style="display:none"> <b><a href='/news/news?searchWord=&curPage=1'> 1 </a></b><a href='/news/news?searchWord=&curPage=2'> 2 </a><a href='/news/news?searchWord=&curPage=3'> 3 </a><a href='/news/news?searchWord=&curPage=4'> 4 </a><a href='/news/news?searchWord=&curPage=5'> 5 </a><a href='/news/news?searchWord=&curPage=6'> 6 </a><a href='/news/news?searchWord=&curPage=7'> 7 </a><a href='/news/news?searchWord=&curPage=8'> 8 </a><a class="next1" href='/news/news?searchWord=&curPage=2'> 다음 </a></div>
					
					<div class="listMore"> 
						<p onClick="location.href='boardTy1a_write.asp';">글쓰기</p>
					</div>

<style>
.paging{text-align: center;}
.paging a{padding-right:2%;}
</style>
		<div class="pagination paging">
			<!-- paging -->


			<c:if test="${pageMaker.prev}">
				<b><a class="next1"
					href="/sft?page=Press&pageNum=${pageMaker.startPage-1 }">&laquo;</a></b>
			</c:if>

			<c:forEach begin="${pageMaker.startPage }"
				end="${pageMaker.endPage }" var="idx">
				<c:if test="${pageMaker.cri.page == idx}">
				<strong>
				</c:if>
				<li>
					<a href="/sft?page=Press&pageNum=${idx}">${idx}</a>
				</li>
				<c:if test="${pageMaker.cri.page == idx}">
				</strongs>
				</c:if>
			</c:forEach>

			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<b><a class="next1"
					href="/sft?page=Press&pageNum=${pageMaker.endPage+1} ">&raquo;</a>
				</b>
			</c:if>

			<!-- // paging -->
		</div>



	</div><!-- 리스트하단 --> 


</div>

<c:import url="../include/footer.jsp"></c:import>