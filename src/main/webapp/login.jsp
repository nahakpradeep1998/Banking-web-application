<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<style>
.container {
	display: flex;
	justify-content: center;
	height: 350px;
	margin-top: 80px;
	margin-left: 10px;
}

form {
	background-color: #f2f2f2;
	border-radius: 12px;
	text-align: center;
	width: 350px;
	height: 300px;
}

input[type=text], 
input[type=password] {
	width: 260px;
	padding: 12px;
	margin: 8px;
	border: 2px solid #ccc;
	border-radius: 7px;
	font-size: 16px;
}

.loginbtn {
	background-color: #314bb2ea;
	color: white;
	padding: 14px;
	margin: 10px;
	border: none;
	border-radius: 7px;
	width: 290px;
	font-size: 16px;
}

.create {
	text-align: center;
}

h2 {
	text-align: center;
}

a {
	color: #4839cf;
}

.success {
	color: green;
	font-weight: bold;
}

.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="container">
		<img
			src="http://localhost:8080/BankWebApplication/Images/Home.png"
			alt="banner">
		<div class="form-container">
			<c:if test="${successMessage != null}">
				<p class="success">${successMessage}</p>
			</c:if>
			<c:if test="${errorMessage != null}">
				<p class="error">${errorMessage}</p>
			</c:if>
			<form action="loginservlet" method="post">
				<h2>Login</h2>
				<div>
					<input type="text" placeholder="Email" name="email" required>
					<input type="password" placeholder="Password" name="password"
						required><br> <a href="#">forget password?</a><br>
					<button class="loginbtn">Login</button>
					<input type="hidden" name="logout" value="true">
				</div>
				<div class="create">
					<p>
						Don't have an account? <a href="registerAH.jsp">Create One</a>.
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
