<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.ics.ejb.Entity.Customer"%>
<%@ page import="org.ics.ejb.Entity.Account"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>Accounts</title>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href ="css/Accounts.css">
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
							<input type=image src="https://i.postimg.cc/wxR6Jttm/accountsgr-nnynyny.jpg"
					alt="Submit feedback"> 
					<input name="menyItem" value="toAccounts" type="hidden">
					<input name="operation" value="distributor" type="hidden">
				</form>
				<form action="/BankClientProject/BankServlet" method="get">
						<input type=image src="https://i.postimg.cc/SQMzknBW/withdrawvit.jpg"
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
		<section id=outerInformatonSection>
			<form action="/BankClientProject/BankServlet" method="post">

				<h2>
					<p>Bank accounts</p>
				</h2>

				<section id="informationSection">
					<%
					//Here we get the session attribute and all the customers accounts. 
					Customer c = (Customer) session.getAttribute("customer");
						if (!c.getAccounts().isEmpty()) {
							List<Account> accounts = c.getAccounts();
							int iterator = 0;
							
							//Creates unique ID for the input. Which is needed to make radiobuttons work.
							for (Account a : accounts) {
								iterator++;
								String iteratorTxt = Integer.toString(iterator);
					%>
					<div id="accountBox" class="firstAccount">
						<h3>
							<input type="radio" name="accountNumber" id=<%=iteratorTxt%>
								value="<%=a.getAccountNumber()%>">
								 <label
								for=<%=iteratorTxt%>> <%=a.getAccountNumber()%><br><%=a.getBalance()%> SEK</label>
						</h3>
						
					</div>
					<%
					}
						} else {
					%>
					<div class="firstAccount">
						<h3>
							<p>No accounts</p>
						</h3>
					</div>
					<%
						}
					%>
				</section>
				<input type="submit" name="createdeleteBtn"
					value="Create New Account" id="createBtn"> <input
					type="submit" name="createdeleteBtn" value="Remove Account"
					id="removeBtn"> <input name="operation"
					value="createDeleteAccount" type="hidden">
			</form>
		</section>
	</section>
	<hr>
	<footer>
	<section id=footerLeft>
		<img src="https://i.postimg.cc/Pqvkb7fh/nobel.jpg" id="location">
		<br> <img src="https://i.postimg.cc/nzKg0by8/telefon.jpg"
			id="contactNumber"> <br> <img
			src="https://i.postimg.cc/nVkNmxKB/email.jpg" id="email">
	</section>
	<aside id=footerRight>
		<ul>
			<li id = "liAboutUs"><a href="AboutUs.jsp" id="aboutUs"> <img
					src="https://i.postimg.cc/g2m72kBf/about.jpg"></a></li>
			<li id = "liTest"><a href="index.html" id="test"> <img
					src="https://i.postimg.cc/Hn0tqbHj/Junit-1.jpg"></a></li>
		</ul>
	</aside>
</footer>
</body>

</html>