<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="../include/top.jsp"></c:import>
<c:import url="../include/nav.jsp"></c:import>
<c:import url="../include/mainStart.jsp"></c:import>
<script>
	$(document).ready(function() {
		$('#send').click(function() {
			var clud_frm = $('#clud_frm');
			clud_frm.submit();
		});
	});
</script>
<div class="titleWrap"
		style="background-color: #40bbe4; width: 100%; padding-top: 10px; padding-bottom: 10px; text-align: center;">
		<h1 class="title" style="color: #fff;">SFT 회원소통방</h1>
	</div>
 <div class="inner">

	<form class="clud_frm" name="clud_frm"  id="clud_frm" method="post" action="/sft?page=Press_insert">
        <h1 class="contTitle">SFT 회원소통방</h1>
        <div class="formWrap">
          <div class="sect">
            <h3>정보 입력</h3>
            <div class="inputWrap">

			   <div style="clear:both;">
                <input type="text" name="title" id="title" placeholder="글제목" />
              </div>
              <div style="float:right;">
                <input type="text" name="txusrnm" id="txusrnm"  value="관리자" placeholder="글쓴이" />
              </div>
              <div class="ipw_100">
                <textarea rows="10" cols="20" id="content"  name="content"  placeholder="내용을 입력해 주세요."></textarea>
              </div>
              
            </div>
          </div>
          <div class="btnWrap"> 
				<a  id="send" class="btn blueBtn" style="cursor: pointer;]">보내기</a> 
				<a id="cancle" class="btn gLineBtn" href="/sft?page=Press&sft=admin">취 소</a>
		  </div>
        </div>


	</form>

      </div></div>

<c:import url="../include/footer.jsp"></c:import>