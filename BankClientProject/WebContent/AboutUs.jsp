<!DOCTYPE html>
<html>
<head>
<title>Settings</title>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href ="css/AboutUs.css">
</head>
<script src="main.js"></script>
<body>
<header><img src = "https://i.postimg.cc/tCJctbLj/senaste-logga.png" id = "logo"> </header>
	<section id="main">
		<nav id="menu">
			<a href="Home.jsp"> <img
				src="https://i.postimg.cc/CxZ3QVBL/home-vit.jpg">
			</a> <a href="Accounts.jsp"> <img
				src="https://i.postimg.cc/fLpDpsPy/accountsnyvit.jpg">
			</a> <a href="Withdraw.jsp"> <img
				src="https://i.postimg.cc/SQMzknBW/withdrawvit.jpg">
			</a> <a href="Deposit.jsp"> <img
				src="https://i.postimg.cc/t4X132Y5/depositvit.jpg">
			</a> <a href="Settings.jsp"> <img
				src="https://i.postimg.cc/QVLPkyVh/profile-vit.jpg">
			</a> 
			<form action="/BankClientProject/BankServlet" method="get">
				<input type=image src="https://i.postimg.cc/qMrgrjXT/logoutvit.jpg"
					alt="Submit feedback"> 
					<input name="operation" value="logout" type="hidden">
			</form>
		</nav>
		<section id="informationSection">
			<h2>
				<p>About us</p>
			</h2>
			<h3>
				<p> The four guys behind this amazing bank application is Morgan Aronsson, Filip Wik, Jakob Wadmark and Wilhelm Olbin.
				We are four informatic students at LUSEM and are currently studying our fourth semester at the Science in information systems program.
 				We felt a strong obligation to fulfill our dreams and decided to start our very own website, KritaEnFemma.
				On this website we have created some amazing functionalty where you can both withdraw and deposit money to and from your bank accounts.  
				Currently we are situated in a very compact office space at EC2 where the heating system seams to be faulty so we are looking forward to getting a new
				office space. </p>
			</h3>
		</section>
		<aside id = pictures>
		<img src = "https://i.postimg.cc/L8bF0wq8/ny.png">
		</aside>
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