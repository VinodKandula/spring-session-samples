<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>">
	<style type="text/css">
		body {
			padding: 1em;
		}
		#un {
			font-weight: bold;
		}
	</style>
</head>
<body>
	<div class="container">
	<c:url value="/login" var="loginUrl"/>
	<form action="${loginUrl}" method="post">
		<c:if test="${param.error != null}">
			<p>
				Invalid username and password.
			</p>
		</c:if>
		<c:if test="${param.logout != null}">
			<p>
				You have been logged out.
			</p>
		</c:if>
		<p>UserName: <input type="text" name="username"/></p>
		<p>Password: <input type="password" name="password"/></p>	
		<p><input type="submit" value="Login"/></p>
	</form>
		
	</div>
</body>
</html>
