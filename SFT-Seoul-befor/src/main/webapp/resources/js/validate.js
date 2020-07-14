var valobj = window.valobj || {}

$(document).ready(function () {
    $(valobj.email2).selectmenu({
        change: function (event, ui) {
            var selectVal = $(valobj.email2).val();
            if (selectVal == "") {
                $(valobj.email1).val("");
                $(valobj.email1).attr("readonly", false);
            } else {
                $(valobj.email1).val(selectVal);
                $(valobj.email1).attr("readonly", true);
            }
        }
    });

	$(".email input[type=text]").focusin(function(){
		$(this).parent().css("borderColor","#215a98");
	}).focusout(function(){
		$(this).parent().css("borderColor","#d9d9d9");
	});
});

/*cs*/
function csValidate() {
    emailSum();
    var phone = $(valobj.phone).val();
    var email = $(valobj.hemail).val();
    if ($('input[name=csAgree]').is(':checked') == 0 || $('input:radio[id=csAgree02]').is(':checked')) {
        alertUtil.msg("개인정보수집&middot;이용 관련 동의서에 동의를 하셔야 이용하실 수 있습니다.", "확인");
        return false;
    }

    if ($(valobj.name).val() == "") {
        msgUtil.error(valobj.name, "이름을 입력해 주세요.");
        return false;
    }

    if (phone == "") {
        msgUtil.error(valobj.phone, "휴대폰 번호를 입력해주세요.");
        return false;
    }

    if (!validatePhoneNum(phone)) {
        msgUtil.error(valobj.phone, "휴대폰 번호가 유효하지 않습니다.");
        return false;
    }

    if ($(valobj.email).val() == "") {
        $(".email div").addClass("active");
        msgUtil.error(valobj.email, "이메일 ID를 입력해주세요.");
        return false;
    }

    if ($(valobj.email1).val() == "") {
        msgUtil.error(valobj.email1, "이메일 도메인을 입력해주세요.");
        return false;
    }

    if (!validateEmail(email)) {
        $(".email div").addClass("active");
        msgUtil.error(valobj.email, "이메일이 유효하지 않습니다.");
        return false;
    }


    $(".email div").removeClass("active");

    if ($(valobj.cstext).val() == "") {
        msgUtil.error(valobj.cstext, "내용을 입력해주세요.");
        return false;
    }


    return true;
}

/*seminar*/
function smnValidate() {
    emailSum();
    var phone = $(valobj.phone).val();
    var email = $(valobj.hemail).val();

    if ($(valobj.name).val() == "") {
        msgUtil.error(valobj.name, "이름을 입력해 주세요.");
        return false;
    }

    if (phone == "") {
        msgUtil.error(valobj.phone, "휴대폰 번호를 입력해주세요.");
        return false;
    }

    if (!validatePhoneNum(phone)) {
        msgUtil.error(valobj.phone, "휴대폰 번호가 유효하지 않습니다.");
        return false;
    }

    if ($(valobj.email).val() == "") {
        $(".email div").addClass("active");
        msgUtil.error(valobj.email, "이메일 ID를 입력해주세요.");
        return false;
    }

    if ($(valobj.email1).val() == "") {
        msgUtil.error(valobj.email1, "이메일 도메인을 입력해주세요.");
        return false;
    }

    if (!validateEmail(email)) {
        msgUtil.error(valobj.email, "이메일이 유효하지 않습니다.");
        return false;
    }

    if ($('input[name=smnAgree]').is(':checked') == 0 || $('input:radio[id=smnAgree02]').is(':checked')) {
        alertUtil.msg("개인정보수집&middot;이용 관련 동의서에 동의를 하셔야 이용하실 수 있습니다.", "확인");
        return false;
    }

    return true;

}

function validateEmail(email) {
	var re = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	return re.test(email);
}

function validatePhoneNum(phone) {
	var re = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/;
	return re.test(phone);
}

function emailSum() {
    $(valobj.hemail).val($(valobj.email).val() + '@' + $(valobj.email1).val());
}





