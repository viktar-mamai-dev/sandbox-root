function registerValidation() {
	alert("Hello");
	var flag = true;
	var field = $("#username");
	var errorCell = field.parent().next();
	if ($.trim(field.val()) == "") {
		errorCell.text("Such field can'be empty");
		flag = false;
	} else {
		errorCell.empty();
	}
	field = $("#login");
	errorCell = field.parent().next();
	if ($.trim(field.val()) == "") {
		errorCell.text("Such field can'be empty");
		flag = false;
	} else {
		errorCell.empty();
	}
	field = $("#password");
	errorCell = field.parent().next();
	if ($.trim(field.val()) == "") {
		errorCell.text("Such field can'be empty");
		flag = false;
	} else {
		errorCell.empty();
	}
	if (field.val().length < 6 || field.val().length > 16) {
		errorCell.text("Password length must between 6 and 16");
		flag = false;
	} else {
		errorCell.empty();
	}
	var conPas = $("#conPas");
	errorCell = conPas.parent().next();
	if (conPas.val() != field.val()) {
		errorCell.text("Confirm password does not match password");
		flag = false;
	} else {
		errorCell.empty();
	}
	return flag;
}

function showHello() {
	alert("Hello1");
	return false;
}