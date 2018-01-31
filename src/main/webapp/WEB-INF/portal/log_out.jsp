<form action="logOut.do" method="POST">
	<button type="submit" class="btn btn-primary">Log Out</button>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>