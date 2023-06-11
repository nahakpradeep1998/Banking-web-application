<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<style>
html {
	height: 100%;
}

body {
	font-family: 'Lato', sans-serif;
	color: #888;
	margin: 0;
}

#main {
	display: table;
	width: 100%;
	height: 100vh;
	text-align: center;
}

.fof {
	display: table-cell;
	vertical-align: middle;
}

.fof h1 {
	font-size: 50px;
	display: inline-block;
	padding-right: 12px;
	animation: type .5s alternate infinite;
}

.message {
	font-weight: bold;
	padding: 10px;
	margin-top: 20px;
	text-align: center;
	border-radius: 5px;
}

.error {
	color: red;
	background-color: #f2d2d2;
}

@
keyframes type {
	from {box-shadow: inset -3px 0px 0px #888;
}

to {
	box-shadow: inset -3px 0px 0px transparent;
}
}
</style>
</head>
<body>
	<div id="main">
		<div class="fof">
			<h1>Error 404</h1>
			<p>An exception occurred!</p>
			<c:if test="${ errorMessage != null}">
				<div class="message error">${errorMessage}</div>
			</c:if>
			<p>
				<a href="dashboard.jsp">Return to Home</a>
			</p>
		</div>
	</div>
</body>
</html>