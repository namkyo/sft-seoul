/*
 *  alliance 
 */ 
function check() {		
	var email 			= document.getElementById("EMAIL").value;
	var company_name 	= document.getElementById("COMPANY_NAME").value;
	var tel 			= document.getElementById("TEL").value;
	var custname 		= document.getElementById("CUSTNAME").value;
	var exptext 		= /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	
	if(email == "" || company_name == "" || tel == "" || custname == "") {
		alert("* 필수 값을 입력해주세요");
		return false;
	}
	if(exptext.test(email)==false){
		//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우			
		alert("이메일형식이 올바르지 않습니다.");
		document.getElementById("EMAIL").focus();
		return false;
	}
}
/*  문의하기  */
function allianceAction(){
	if(check()==false) return false;
	clud_frm.action="/support/wr_alliance";
	clud_frm.submit();
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