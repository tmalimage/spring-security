$(document).ready(function() {
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");

	var headers = {};
	headers[csrfHeader] = csrfToken;

	// To handle CSRF token, log out should be a HTTP POST
	$("#logOutUrl").on("click", function(event) {
		$.ajax({
			type : "POST",
			url : "logOut.do",
			headers : headers
		});
	});

});