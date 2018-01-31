<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<li class="active"><a href="manageConsumer.do">Consumers/
					Users</a></li>
			<li><a href="createContent.do">Create Content</a></li>
			<li><a href="viewContent.do">View Content</a></li>
		</ul>
	</div>
	<div class="container">
		<div class="row">
			<form action="createConsumer.do" method="POST">
				<div class="col-sm-8">
					<h3>Create Consumer</h3>
					<div class="row bottom-margin-5">
						<div class="col-sm-4">*Name</div>
						<div class="col-sm-8">
							<input type="text" class="alphaOnly" id="name" name="name">
						</div>
					</div>
					<div class="row bottom-margin-5">
						<div class="col-sm-4">*Email</div>
						<div class="col-sm-8">
							<input type="email" class="emailAddressOnly" id="email"
								name="email">
						</div>
					</div>
					<div class="row bottom-margin-5">
						<div class="col-sm-4">*Password</div>
						<div class="col-sm-8">
							<input type="password" id="password" name="password">
						</div>
					</div>
					<div class="row bottom-margin-5">
						<div class="col-sm-4">*Role</div>
						<div class="col-sm-8">
							<select id="consumer-role" name="consumerRole.roleId">
								<c:forEach items="${sa_consumer_role_list}" var="role">
									<option value="${role.roleId}">${role.roleName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row bottom-margin-5">
						<div class="col-sm-4"></div>
						<div class="col-sm-8">
							<button type="submit" class="btn btn-primary">Create
								Consumer</button>
						</div>
					</div>
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<div class="col-sm-4">
				<h3>Manage Instructors & Students</h3>
				<p>Super Admin has access & privilege to manage consumers. Such
					as create, delete, modify consumers etc.</p>
			</div>
		</div>
	</div>
	${requestScope.ra_consumer}
	<div class="jumbotron text-center"></div>
</body>
</html>