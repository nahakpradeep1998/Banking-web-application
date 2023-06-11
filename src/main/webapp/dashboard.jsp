<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Screen</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
* {
	padding: 0px;
	margin: 0px;
	box-sizing: border-box;
}
#withdraw-deposit {
  display: none;
  margin-top: 20px;
  padding: 10px;
  background-color: #f2f2f2;
  border: 1px solid #ccc;
}

#withdraw-deposit label {
  display: inline-block;
  width: 100px;
  font-weight: bold;
}

#withdraw-deposit input[type="number"] {
  width: 150px;
  margin-right: 10px;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 3px;
  font-size: 14px;
}

#withdraw-deposit button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 5px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 14px;
  margin-top: 10px;
  cursor: pointer;
  border-radius: 3px;
}

#withdraw-deposit button:hover {
  background-color: #3e8e41;
}

.logout-btn {
	position: absolute;
	top: 10px;
	right: 10px;
	padding: 10px;
	background-color: #f20808;
	color: #fff;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	cursor: pointer;
}
.container {
	position: relative;
	display: flex;
	flex-direction: row;
	height: 100vh;
}
.header {
	background-color: #ccc;
	flex: 0 0 auto;
	padding: 20px;
}
.nav {
	background-color: #ddd;
	padding: 20px;
	width: 15%;
	height: 100%;
}
.main {
	flex: 2;
	padding: 20px;
	background-color: #eee;
	padding: 20px;
}
.account-details {
  background-color: #f7f7f7;
  border-radius: 5px;
  padding: 20px;
  margin-top: 20px;
  text-align: left;
}

.account-details h2 {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 10px;
}

.account-details hr {
  border: none;
  border-top: 1px solid #ccc;
  margin-bottom: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<c:if test="${successMessage != null}">
				<p>${successMessage}</p>
			</c:if>
			<c:if test="${errorMessage != null}">
				<p>${errorMessage}</p>
			</c:if>
			<button onclick="window.location.href = 'logoutServlet'" class="logout-btn">Logout</button>
		</div>
		<hr>
		<div class="nav">
			<ul>
				<li><a href="editservlet?accountHolderId=${accountHolderId }">EditProfile</a></li><br>
				<li><a href="javascript:void(0);" onclick="checkBalance()">CheckBalance</a></li><br>
				<li><a href="transferMoney.jsp">Transfer Money</a></li>
			</ul>
		</div>
		<div class="main">
			<c:if test="${accountHolderName != null}">
				<div class="account-details">
					<h3>Account Holder Details</h3>
					<hr>
					Full Name: ${accountHolderName}<br><br> 
					Account Number: ${accountNumber}<br><br>
					Current Balance: ${accountHolderBalance}<br><br>
					Email: ${accountHolderEmail}<br><br> 
					PhoneNumber: ${accountHolderPhoneNo}<br><br> 
					Nominee Name: ${NomineeName}
				</div>
				<br>
				<hr>
				<div id="withdraw-deposit" style="display: none;">
					 <label for="deposit">Deposit:</label>
					<input type="number" id="deposit" name="deposit" value="0">
					<button id="deposit-btn">Deposit</button>
					<br><br>
					<label for="withdraw">Withdraw:</label>
					<input type="number" id="withdraw" name="withdraw" value="0">
					<button id="withdraw-btn">Withdraw</button>
					<br><br>
				</div>
				<div id="balance">Total Balance:</div>
			</c:if>
		</div>	
	</div>
	<script>
	function checkBalance() {
	    $.ajax({
	        url: "withdraw_depositServlet",
	        type: "POST",
	        data: {action: "getBalance"},
	        success: function(data) {
	            $("#balance").html("Total Balance: " + data);
	            $("#withdraw-deposit").show();
	        },
	        error: function() {
	            alert("Error: Unable to get balance.");
	        }
	    });
	}
	
		$(document).ready(function() {
			  $('#deposit-btn').click(function() {
				  console.log("deposit button clicked");
			    var depositAmount = $('#deposit').val();
			    if (depositAmount <= 0) {
			      alert("deposit amount must be greater than zero.");
			      return;
			    }
			    $.ajax({
			        url: 'withdraw_depositServlet',
			        type: 'POST',
			        data: {
			            action: 'deposit',
			            deposit: depositAmount
			        },
			        success: function(data) {
			            console.log("success");
			            $('#balance').text('Total Balance: ' + data);
			        },
			        error: function() {
			            alert('Error: Unable to deposit funds.');
			        }
			    });
			  });
			});
		
		$(document).ready(function() {
			  $('#withdraw-btn').click(function() {
				  console.log("withdraw button clicked");
			    var withdrawAmount = $('#withdraw').val();
			    if (withdrawAmount <= 0) {
			      alert("withdraw amount must be greater than zero.");
			      return;
			    }
			    $.ajax({
			        url: 'withdraw_depositServlet',
			        type: 'POST',
			        data: {
			            action: 'withdraw',
			            withdraw: withdrawAmount
			        },
			        success: function(data) {
			            console.log("success");
			            $('#balance').text('Total Balance: ' + data);
			        },
			        error: function() {
			            alert('Error: Unable to withdraw funds.');
			        }
			    });
			  });
			});
	</script>
</body>
</html>
