<!DOCTYPE html>
<%@page isELIgnored="false"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="css/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="jumbotron text-center">
		<h1>The College</h1>
	</div>

	<div class="container">
		<form action="authenticate.do" method="POST">
			<div class="row bottom-margin-5">
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-4">Email</div>
						<div class="col-sm-8">
							<input type="email" class="emailAddressOnly" id="email"
								name="email">
						</div>
					</div>
				</div>
			</div>			
			<div class="row bottom-margin-5">
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-4">Password</div>
						<div class="col-sm-8">
							<input type="password" id="password" name="password">
						</div>
					</div>
				</div>
			</div>
			<div class="row bottom-margin-5">
				<div class="col-sm-6"></div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<div class="rs_err_msg">${requestScope.ra_err}</div>
							<button type="submit" class="btn btn-primary">Log In</button>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<div class="jumbotron text-center">		
	</div>
</body>
</html>