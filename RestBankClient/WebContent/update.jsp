<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>Rest client - Update</title>
<link rel="stylesheet" href ="css/update.css">
</head>
<script src="restcustomer.js"></script>
<body>
	<header>
		<p>KritaEnFemma</p>
	</header>
	<section id="row">
		<nav>
			<ul>
				<li><a href="create.jsp">Create</a></li>
				<li><a href="find.jsp">Find</a></li>
				<li class="active"><a href="update.jsp">Update</a></li>
				<li><a href="delete.jsp">Delete</a></li>
			</ul>
		</nav>
		<aside>
			<table id="asideTable">
				<tr>
					<th style="text-align: left"><span id="city"></span></th>
					<th><span></span></th>
					<th><span></span></th>
					<th style="text-align: left"><span id="ipNbr"></span></th>
				</tr>
				<tr>
					<td><span id="degree"></span></td>
					<td><span id="weather"></span></td>
					<td><span></span></td>
					<td><span></span></td>
				</tr>
				<tr>
					<td colspan="4"><span id="sunrise"></span></td>
				</tr>
				<tr>
					<td colspan="4"><span id="sunset"></span></td>
				</tr>
			</table>
		</aside>
		<section id="main">
			<section id="content">

					<label for="customerUpdate">SSN</label><br> <input id="customerUpdate" list = "customers" maxlength="10" autocomplete="off">
						<datalist id="customers">
							</datalist>
							<br> <label for="fullName">Full Name</label><br> <input
							type="text" name="fullName" id="fullName" value="" autocomplete="off"><br>
							<label for="address">Address</label><br> <input type="text"
								name="address" id="address" value="" autocomplete="off"><br> <label
								for="phoneNumber">Phone Number</label><br> <input
									type="text" name="phoneNumber" id="phoneNumber" value="" autocomplete="off" maxlength="10">
									<br> <input type="button" name="updateBtn" value="Update"
									id="updateBtn">
			</section>
		</section>
	</section>
	<footer> </footer>
</body>
</html>