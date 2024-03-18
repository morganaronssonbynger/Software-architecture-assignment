<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Register new user</title>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/Register.css">
</head>
<script src="main.js"></script>
<body>
	<header> </header>
	<section id="main">
		<section id="registerSection">
			<h2>
				<p>Register new user</p>
			</h2>
			<form action="/BankClientProject/BankServlet" method="post"
				onsubmit="return checkRegister()" autocomplete="off">
				<br> <label for="SSN">SSN*</label><br>
				<input type="text" name="SSN" id="SSN" maxlength="10"
					placeholder="9710101234" value=""> <br> <label
					for="fullName">Full Name*</label><br>
				<input type="text" name="fullName" id="fullName"
					placeholder="John Doe" value=""> <br> <label
					for="address">Address*</label><br>
				<input type="text" name="address" id="address"
					placeholder="Nobelvägen 52" value=""> <br> <label
					for="phoneNumber">Phone Number*</label><br>
				<input type="text" name="phoneNumber" id="phoneNumber"
					placeholder="2317255977" maxlength="10" value=""> <br>
				<input type="submit" name="submitBtn" value="Register"
					id="registerBtn"> <br>
				<a href="LogIn.jsp" id="cancelBtn"> <img
					src="https://i.postimg.cc/5tWX6sTk/cancel-button.jpg"></a> <input
					name="operation" value="register" type="hidden">
			</form>
		</section>
		<%
			//Validateing and displaying the success and error messeges when register succed/fail
			String registrationTxt = (String) request.getAttribute("registerResponse");
			if (registrationTxt != null) {
				if (registrationTxt.contains("failed.")) {
		%>
		<p id="displayBoxFail" class="displayBox" onload="setTimeout">
			<%=registrationTxt%>
		</p>
		<%
			} else if (registrationTxt.contains("succeded!")) {
		%>
		<p id="displayBoxSucced" class="displayBox" onload="setTimeout">
			<%=registrationTxt%>
		</p>

		<%
			}
			}
		%>
	</section>
</body>

</html>