<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>KritaEnFemma - Log in</title>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href ="css/LogIn.css">
</head>
<script src="main.js"></script>
<body>
<header>
		<% 
			//Error messege when you delete customer in other client and still logged in on this client
			String logInBarResponse = (String)request.getAttribute("logInBar");
			if(logInBarResponse != null){
				%>
				<p id="logInBar"> <%=logInBarResponse %> </p>
				<% 			
			}
				%>
<img src = "https://i.postimg.cc/tCJctbLj/senaste-logga.png" id = "logo">
<h1>
	<p>KritaEnFemma</p>
</h1>

<h2>
	<p>We take care of your banking needs.</p>
</h2>
</header>
<section id = "main">
	<section id = "loginSection">
		<div class = "loginDiv">
			<br><label for="SSN">SSN</label>
			<br>
			<form autocomplete="off" action="/BankClientProject/BankServlet" method="get" onsubmit="return checkLogIn()">
				<input type="text" name="txtSSN" id="SSN" placeholder="SSN" value="" maxlength="10">
				<br>
				<input type="submit" name="submitBtn" value="Log in" id="LoginBtn" >
				<br>
				<a href="Register.jsp">
				<input type="button" name="submitBtn" value="Register" id="RegisterBtn">
				</a>
				<input name="operation" value="login" type="hidden">
			</form>	
		</div>
					<% 
			//Error messege when login fail
			String logInTxt = (String)request.getAttribute("logInResponse");
			if(logInTxt != null){
				%>
				<p id="displayBoxFail" class="displayBox" onload="setTimeout"> <%=logInTxt %> </p>
				<% 			
			}
				%>	
	</section>
</section>
<footer>
</footer>
</body>

</html>