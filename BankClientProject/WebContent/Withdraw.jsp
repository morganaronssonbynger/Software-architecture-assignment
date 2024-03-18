<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.ics.ejb.Entity.Customer"%>
<%@ page import="org.ics.ejb.Entity.Account"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Withdraw</title>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href ="css/Withdraw.css">
</head>
<script src="main.js"></script>
<body>
<header><img src = "https://i.postimg.cc/tCJctbLj/senaste-logga.png" id = "logo"></header>
		<section id="main">
			<nav id="menu">
				<form action="/BankClientProject/BankServlet" method="get">
							<input type=image src="https://i.postimg.cc/CxZ3QVBL/home-vit.jpg"
					alt="Submit feedback"> 
					<input name="menyItem" value="toHome" type="hidden">
					<input name="operation" value="distributor" type="hidden">
				</form>
				<form action="/BankClientProject/BankServlet" method="get">
							<input type=image src="https://i.postimg.cc/fLpDpsPy/accountsnyvit.jpg"
					alt="Submit feedback"> 
					<input name="menyItem" value="toAccounts" type="hidden">
					<input name="operation" value="distributor" type="hidden">
				</form>
				<form action="/BankClientProject/BankServlet" method="get">
						<input type=image src="https://i.postimg.cc/vTf1zKrP/withdrawgr-m.jpg"
					alt="Submit feedback"> 
					<input name="menyItem" value="toWithdraw" type="hidden">
					<input name="operation" value="distributor" type="hidden">
				</form>
				<form action="/BankClientProject/BankServlet" method="get">
						<input type=image src="https://i.postimg.cc/t4X132Y5/depositvit.jpg"
					alt="Submit feedback"> 
					<input name="menyItem" value="toDeposit" type="hidden">
					<input name="operation" value="distributor" type="hidden">
				</form>
				<form action="/BankClientProject/BankServlet" method="get">
						<input type=image src="https://i.postimg.cc/QVLPkyVh/profile-vit.jpg"
					alt="Submit feedback"> 
					<input name="menyItem" value="toSettings" type="hidden">
					<input name="operation" value="distributor" type="hidden">
				</form>
			<form action="/BankClientProject/BankServlet" method="get">
				<input type=image src="https://i.postimg.cc/qMrgrjXT/logoutvit.jpg"
					alt="Submit feedback"> 
					<input name="operation" value="logout" type="hidden">
			</form>
			</nav>
			<section id="informationSection">
				<h2>
					<p>Withdraw</p>
				</h2>
				<label for="account">Bank account</label><br>
				<form autocomplete="off" action="/BankClientProject/BankServlet"
					method="post" onsubmit="return checkAccount()">
					<input list="accounts" name="account" id="account">
					<datalist id="accounts">
						<%
							//Gets the session attribute of customer and populates the datalist with the customers accounts
							Customer c = (Customer) session.getAttribute("customer");
							List<Account> accounts = c.getAccounts();

							for (Account a : accounts) {
						%>
						<option
							value="<%=a.getAccountNumber()%> - <%=a.getBalance()%> SEK">
							<%
								}
							%>
						
					</datalist>
					<br> <label for="amount">Amount</label><br> <input
						type="text" name="amount" id="amount" value=""> <br>
					<input type="submit" name="withdrawBtn" value="Withdraw"
						id="withdrawBtn"> <input name="operation" value="withdraw"
						type="hidden">
				</form>
				<%
					//Validateing and displaying the success and error messeges when withdraw succed/fail
					String withdrawTxt = (String)request.getAttribute("withdrawResponse");
					if (withdrawTxt != null) {
						if(withdrawTxt.contains("rejected")){
						%>
						<p id="displayBoxFail" class="displayBox" onload="setTimeout"> <%=withdrawTxt%></p>
						<%
						}
						else if(withdrawTxt.contains("successfully")){
							%>
							<p id="displayBoxSuccess" class="displayBox" onload="setTimeout"> <%=withdrawTxt%></p>
							<%			
						}
					}
				%>
			</section>
		</section>
	<hr>
</body>
<footer>
	<section id=footerLeft>
		<img src="https://i.postimg.cc/Pqvkb7fh/nobel.jpg" id="location">
		<br> <img src="https://i.postimg.cc/nzKg0by8/telefon.jpg"
			id="contactNumber"> <br> <img
			src="https://i.postimg.cc/nVkNmxKB/email.jpg" id="email">
	</section>
	<aside id=footerRight>
		<ul>
			<li id="liAboutUs"><a href="AboutUs.jsp" id="aboutUs"> <img
					src="https://i.postimg.cc/g2m72kBf/about.jpg"></a></li>
			<li id="liTest"><a href="index.html" id="test"> <img
					src="https://i.postimg.cc/Hn0tqbHj/Junit-1.jpg"></a></li>
		</ul>
	</aside>
</footer>

</html>