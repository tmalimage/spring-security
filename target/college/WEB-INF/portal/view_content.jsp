<!DOCTYPE html>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page isELIgnored="false"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="css/common.css" rel="stylesheet" type="text/css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="jumbotron text-right">
		<p class="right-margin-10">
			Hello ${sessionScope.sa_consumer_name}
			<form action="logOut.do" method="POST">
				<button type="submit" class="btn btn-primary">Log Out</button>
			<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</p>
	</div>
	<div class="container">
		<ul class="nav nav-pills">
			<sec:authorize access="hasAnyRole('SUPER_ADMIN')">
				<li><a href="manageConsumer.do">Consumers/ Users</a></li>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('SUPER_ADMIN', 'INSTRUCTOR')">
				<li><a href="createContent.do">Create Content</a></li>
			</sec:authorize>
			<li class="active"><a href="viewContent.do">View Content</a></li>
		</ul>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<h3>Student Content</h3>
				<p>Super Admin, Instructor & Student have access to view student
					content</p>
			</div>
			<div class="col-sm-4">
				<sec:authorize access="hasAnyRole('SUPER_ADMIN', 'INSTRUCTOR')">
					<h3>Instructor Content</h3>
					<p>Super Admin & Instructor have access to view student content</p>
				</sec:authorize>
			</div>
			<div class="col-sm-4">
				<sec:authorize access="hasAnyRole('SUPER_ADMIN')">
					<h3>Price Content</h3>
					<p>Super Admin has access to view price content</p>
				</sec:authorize>
			</div>
		</div>
	</div>
	${requestScope.ra_consumer}
	<div class="jumbotron text-center"></div>
</body>
</html>