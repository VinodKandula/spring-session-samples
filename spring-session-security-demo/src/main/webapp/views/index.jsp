<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>SpringSession Security Demo</title>
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
		<h1>Description</h1>
		<p>This demonstrates how Spring Session can be combined with Spring Security. 
		   The important thing to ensure is that Spring Session's Filter is included before Spring Security's Filter.</p>


		<p>You are currently logged in as <span id="un"><c:out value="${pageContext.request.remoteUser}"/></span>.</p>

		<c:url value="/logout" var="logoutUrl"/>
		<form action="${logoutUrl}" method="post">
			<input type="submit" value="Log Out"/>
		</form>
		
		<h4>Add Simple Attributes To Session</h4>
		<form class="form-inline" role="form" action="add-simple-attrs" method="post">
			<label for="attributeName">Attribute Name</label>
			<input id="attributeName" type="text" name="attributeName"/>
			<label for="attributeValue">Attribute Value</label>
			<input id="attributeValue" type="text" name="attributeValue"/>
			<input type="submit" value="Set Attribute"/>
		</form>
		
		<h4>Add Object Attributes To Session</h4>
		<form class="form-inline" role="form" action="add-object-attrs" method="post">
			<label for="name">User Name</label>
			<input id="name" type="text" name="name"/>
			<input type="submit" value="Save"/>
		</form>
		
		
		<h3>Session Attributes</h3>
		<table>
			<thead>
				<tr>
					<th>Attribute Name</th>
					<th>Attribute Value</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope}" var="attr">
					<tr>
						<td><c:out value="${attr.key}"/></td>
						<td><c:out value="${attr.value}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</div>
</body>
</html>
