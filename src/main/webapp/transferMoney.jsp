<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer Money</title>
<style>
form {
	background-color: #f2f2f2;
	border: 1px solid #ccc;
	padding: 20px;
	position: absolute;
	top: 31%;
	left: 20%;
	transform: translate(-50%, -50%);
}
label {
	display: block;
	margin-bottom: 10px;
}
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	border: 1px solid #ccc;
	box-sizing: border-box;
}
input[type=submit] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
input[type=submit]:hover {
	background-color: #45a049;
}
@media only screen and (max-width: 600px) {
	/* Adjust the form position for small screens */
	form {
		width: 80%;
		top: 40%;
	}
}
.message {
	font-weight: bold;
	padding: 10px;
	margin-top: 20px;
	text-align: center;
	border-radius: 5px;
}
.success {
	color: green;
	background-color: #d2f2d2;
}
.error {
	color: red;
	background-color: #f2d2d2;
}
</style>
</head>
<body>
	<h3>Transfer Money</h3>
	<p>Please enter the following details to transfer money:</p>
	<form action="transferMoneyServlet" method="POST">
		<label for="receiverAccNo">Payee Account No:</label> 
		<input type="text" name="receiverAccNo" id="receiverAccNo"><br>
		<label for="amount">Amount:</label> <input type="text" name="amount" id="amount"><br> 
		<input type="submit" value="Transfer Amount">
	</form>
	<c:if test="${not empty successMessage}">
		<div class="message success">${successMessage}</div>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<div class="message error">${errorMessage}</div>
	</c:if>
</body>
</html>