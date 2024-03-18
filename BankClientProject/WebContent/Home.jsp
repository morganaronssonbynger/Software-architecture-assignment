<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.ics.ejb.Entity.Customer"%>

<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href ="css/Home.css">
</head>
<script src="main.js"></script>
<body>
<header> <img src = "https://i.postimg.cc/tCJctbLj/senaste-logga.png" id = "logo"></header>
	<section id="head">
		<%
			// Gets the session variable customer
			Customer c = (Customer) session.getAttribute("customer");
		%>
		<h1>
			<p>
				Welcome,  
				<%=c.getName()%>!
			</p>
		</h1>
	</section>
	<section id="main">
		<nav id="menu">	
			<form action="/BankClientProject/BankServlet" method="get">
							<input type=image src="https://i.postimg.cc/MTnr37rM/homegr-m.jpg"
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
		<section id="informationSection">
		<h2>
		<p>We here at KritaEnFemma ensure you that we manage your money with the highest security and the best revenue. </p>
		</h2>
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