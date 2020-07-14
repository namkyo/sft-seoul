
/*alert util*/
var alertUtil = {
	msg: function (msg, btnOk, focus) {
		$("body").prepend("<div class='alertDiv'></div>");
		$(".alertDiv").fadeIn(300).html("");
		
		var adHtml = "";
		adHtml += "<p>" + msg + "</p>";
		adHtml += "<div class='btnWrap'>";
		adHtml += "<a href='#!' class='btn blueBtn'>" + btnOk + "</a>";
		adHtml += "</div>";
		adHtml += "<a href='#!' class='alertClose'>";
		adHtml += "<img src='/resources/public/images/common/closeB.png' alt='닫기' />";
		adHtml += "</a>";
		
		$(".alertDiv").append(adHtml);
		$("#dimed").fadeIn(300);
		
		$(".alertDiv .blueBtn, .alertClose").click(function(){
			$(".alertDiv").fadeOut(300);
			$("#dimed").fadeOut(300);
			$(focus).focus();
		});
	}
}

/*msg util*/
var msgUtil = {
	// error massage
	error: function (input, msg) {
		$(".error").hide();
		$(".error").prev().css("borderColor","#d9d9d9");
		$(input).after("<p class='error'>" + msg + "</p>");
		$(input).next().show();
		$(input).focus().css("borderColor","red");

		if(input == "input[name='emailID']" || "input[name='emailDomain01']") {
			$(input).parent().css("borderColor","red");
		} 
		
	}
}

