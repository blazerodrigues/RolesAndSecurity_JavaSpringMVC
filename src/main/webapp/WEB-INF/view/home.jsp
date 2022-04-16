<!-- add support for spring mvc form-tags -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- add support for spring security tag library -->
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<title>Blaze's Website Home Page</title>
</head>

<body bgcolor="#E3DDDB">

	<h2>Blaze's Website - Home Page</h2>
	<hr>

	<p>Welcome to Blaze's Website. This is the Home Page!</p>

	<hr>

	<!-- Display username and roles -->

	<p>
		<!-- Display the user-id who is currently logged in -->
		User:
		<security:authentication property="principal.username" />

		<br> <br>

		<!-- Display the ROLES of the user-id who is currently logged in -->

		Roles(s):
		<security:authentication property="principal.authorities" />

	</p>

	

	<security:authorize access="hasRole('MANAGER')">

		<!-- Add a link to point to /leaders ... this is for the MANAGER role -->

		<p>

			<a href="${pageContext.request.contextPath}/leaders">Leadership
				Page</a> (Only for MANAGER role)

		</p>

	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">

	<!-- Add a link to point to /systems ... this is for the ADMIN role -->

	<p>

		<a href="${pageContext.request.contextPath}/systems">IT System Admins
			Page</a> (Only for ADMIN role)

	</p>
	
	</security:authorize>

	<hr>

	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>

</body>
</html>