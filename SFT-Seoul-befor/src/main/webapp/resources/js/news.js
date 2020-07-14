/*
 *  NEWS 
 */ 
/*  검색하기  */
function News_search(){
	search_frm.action="/news/news";
	search_frm.submit();
}
function News_Detail(no){
	search_frm.num.value = no;
	search_frm.action="/news/newsview";
	search_frm.submit();
}
function News_wr(){
	search_frm.chnl.value = "1";
	search_frm.action="/news/writeForm";
	search_frm.submit();
}
function News_delete(){
	var con = confirm("정말로 삭제하시겠습니까?");
	if(con == true){
		crud_frm.action = "/news/delete";
		crud_frm.submit();
	}
}
function News_editopen(){
	crud_frm.chnl.value = "2";
	crud_frm.action = "/news/writeForm";
	crud_frm.submit();
}