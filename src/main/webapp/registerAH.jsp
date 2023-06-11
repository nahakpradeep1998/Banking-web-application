<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<style>
.container {
	display: flex;
	justify-content: center;
	height: 564px;
}

form {
	background-color: #f2f2f2;
	border-radius: 12px;
	text-align: center;
	width: 350px;
}

h2 {
	text-align: center;
}

input[type=text], input[type=password] {
	width: 260px;
	padding: 12px;
	margin: 8px;
	border: 2px solid #ccc;
	border-radius: 7px;
	font-size: 16px;
}
input[type="date"] {
  width: 260px;
	padding: 12px;
	margin: 8px;
	border: 2px solid #ccc;
	border-radius: 7px;
	font-size: 16px;
}

input[type="date"]::placeholder {
  color: #999;
}

input[type=checkbox] {
	margin-top: 10px;
}

.registerbtn {
	background-color: #0fb00dea;
	color: white;
	padding: 14px;
	margin: 10px;
	border: none;
	border-radius: 7px;
	width: 290px;
	font-size: 16px;
}

a {
	color: #4839cf;
}
.msg {
	display: flex;
}

.register-div {
	display: flex !important;
    margin-top: 30px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="msg">
			<c:if test="${errorMessage != null}">
				<p>${errorMessage}</p>
			</c:if>
		</div>
		<div class="register-div">
		<%-- <c:if test="${accountHolder != null }">
			<form action="updateservlet" method="post">
			</c:if>
			<c:if test="${accountHolder ==null }">
				<form action="registerservlet" method="post">
			</c:if> --%>
			<%-- <form action="${accountHolder != null ? 'updateservlet' : 'registerservlet'}" method="post"> --%>
			 <c:if test="${accountHolder != null }">
        		<form action="accountHolderServlet" method="post">
            	<input type="hidden" name="action" value="update">
    		</c:if>
    		<c:if test="${accountHolder == null }">
       			<form action="accountHolderServlet" method="post">
            	<input type="hidden" name="action" value="register">
    		</c:if>
				<h2> Account</h2>	
				<input type="hidden" name="accountHolderId" value="${accountHolder.accountHolderId}"> 
				<input type="text" placeholder="Full Name" name="fName" id="fName" value="${accountHolder.fullName}" required>   
				<input type="text" placeholder="Email" name="email" id="email" value="${accountHolder.email}" required> 
				<input type="text" placeholder="Phone Number" name="phoneNo" id="phoneNo" value="${accountHolder.phoneNo}" required>
				<input type="text" placeholder="Nominee Name" name="nName" id="nName" value="${accountHolder.nomineeName}" required>
				<input type="date" placeholder="Date of Birth" name="dob" id="dob" value="${accountHolder.dateOfBirth}" required>
				<input type="text" placeholder="Password" name="password" id="password" value="${accountHolder.password}" required>
				<input type="text" placeholder="Re-enter Password" name="reEnterPassword" id="reEnterPassword" value="${accountHolder.reEnterPassword}" required><br/>
				<button type="submit" class="registerbtn">Account</button>	
			</form>		
		</div>
	</div>
</body>

</html>