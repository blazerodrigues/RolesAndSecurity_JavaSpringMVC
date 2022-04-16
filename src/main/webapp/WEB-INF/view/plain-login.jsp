<!-- add support for spring mvc form-tags -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- adding support for JSTL(Java ServerPages Tag Lib) -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>

<title>Custom Login Page</title>

<style>

.failed {
	color: red;
}

</style>

</head>

<body bgcolor="#E3DDDB">

	<h3>My Custom Login Page</h3>

	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">

		<!-- Check for login error using JSTL -->
		<c:if test="${param.error != null}">

			<i class="failed">Sorry! You entered invalid username / password.</i>

		</c:if>

		<!-- Spring security filter will identify and read input NAME-attribute "username" and "password" , and then authenticate the user -->
		<p>
			User name: <input type="text" name="username" />
		</p>

		<p>
			Password: <input type="password" name="password" />
		</p>

		<input type="submit" value="Login" />

	</form:form>


</body>


</html>