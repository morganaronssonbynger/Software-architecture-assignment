$(document).ready(function(){

	// Ajax method that gets the weather and displays it in our user interface on document reload
	$.ajax({
		method: "GET",
		url: "http://api.ipstack.com/check?access_key=cfa0015603affece759e9694302e6c61",
		error: ajaxReturn_WeatherError, 
		success: ajaxReturn_WeatherSuccess
		})

	function ajaxReturn_WeatherSuccess(result, status, xhr) {
		ParseJsonFile(result);
		}

	function ajaxReturn_WeatherError(result, status, xhr) {
		console.log("Ajax-api-stack: "+ status);
		}
	


	// Ajax method that populates our comboboxes with values on document reload

	$.ajax({
		method: "GET",
		url: "http://localhost:8080/RestBankClient/Customers/",
		error: ajaxReturn_ComboBoxError, 
		success: ajaxReturn_ComboBoxSuccess
		})

	function ajaxReturn_ComboBoxSuccess(result, status, xhr) {
			ParseJsonFileSSN(result);
		}

	function ajaxReturn_ComboBoxError(result, status, xhr) {
		console.log("Ajax-api-stack: "+ status);
		}
	

	// Method that checks users inputs when trying to find a customer and sends it to the servlet on success

	$("#findBtn").click( function() {
		ResetBorders();
		var ssn = $("#customerFind").val();
			
		if(ssn == null || ssn == ""){
			$("#customerFind").val("");
			$("#customerFind").attr("placeholder","Please choose a valid SSN");
			$("#customerFind").css("border-color", "red");
		}
		if(ssn.length != 10 || !ssn.match(/^([0-9])/)){
			$("#customerFind").val("");
			$("#customerFind").attr("placeholder","Please choose a valid SSN");
			$("#customerFind").css("border-color", "red");
		}
		else {
			$.ajax({method: "GET",
				url: "http://localhost:8080/RestBankClient/Customers/"+ ssn,
				error: ajaxFindReturnError,
				success: ajaxFindReturnSuccess
				})
			
			function ajaxFindReturnSuccess(result, status, xhr) {
				parseJsonFileCustomer(result);
				}
			function ajaxFindReturnError(result, status, xhr) {
				$("option[value='" + ssn + "'").remove();
				$("#customerFind").val("");
				$("#fullName").val("");
				$("#address").val("");
				$("#phoneNumber").val("");
				$("#customerFind").attr("placeholder","Customer does not exist");
				$("#customerFind").css("border-color", "red");
				console.log("Ajax-find customer: "+status);
				}
			}
		})
		
	// Method that checks values in the input field when a user is trying to create a customer and sends it to the servlet on success
	$("#createBtn").click( function() {
		ResetBorders();
		var ssn = $("#ssn").val();
		var fullName = $("#fullName").val();
		var address = $("#address").val();
		var phoneNumber = $("#phoneNumber").val();
		
		if(ssn == null || ssn == "" || fullName == null || fullName == "" || address == null || address == "" ||
				phoneNumber == null || phoneNumber == "" || !ssn.match(/^([0-9])/) || !fullName.match(/^[a-zA-Z\s]+$/) ||
				!phoneNumber.match(/^([0-9])/) || ssn.length!=10 || phoneNumber.length!= 10){
			
			if(ssn == null || ssn == ""){
				$("#ssn").val("");
				$("#ssn").attr("placeholder","Format: YYMMDDXXXX");
				$("#ssn").css("border-color", "red");
			}
			if(fullName == null || fullName == ""){
				$("#fullName").val("");
				$("#fullName").attr("placeholder","Format: John Doe");
				$("#fullName").css("border-color", "red");
			}
			if(address == null || address == ""){
				$("#address").val("");
				$("#address").attr("placeholder","Format: Nobelstreet 22");
				$("#address").css("border-color", "red");
			}
			if(phoneNumber == null || phoneNumber == ""){
				$("#phoneNumber").val("");
				$("#phoneNumber").attr("placeholder","Format: 07XXXXXXXX");
				$("#phoneNumber").css("border-color", "red");
			}
			if(!ssn.match(/^([0-9])/)){
				$("#ssn").val("");
				$("#ssn").attr("placeholder","Format: YYMMDD-XXXX");
				$("#ssn").css("border-color", "red");
			}
			if(!fullName.match(/^[a-zA-Z\s]+$/)){
				$("#fullName").val("");
				$("#fullName").attr("placeholder","Format: John Doe");
				$("#fullName").css("border-color", "red");
			}
			if(!phoneNumber.match(/^([0-9])/)){
				$("#phoneNumber").val("");
				$("#phoneNumber").attr("placeholder","Format: 07XXXXXXXX");
				$("#phoneNumber").css("border-color", "red");
			}
			if(ssn.length!=10){
				$("#ssn").val("");
				$("#ssn").attr("placeholder","Format: YYMMDDXXXX");
				$("#ssn").css("border-color", "red");
			}
			if(phoneNumber.length!= 10){
				$("#phoneNumber").val("");
				$("#phoneNumber").attr("placeholder","Format: 07XXXXXXXX");
				$("#phoneNumber").css("border-color", "red");
			}
		}
		else{
			$.ajax({method: "POST",
			url: "http://localhost:8080/RestBankClient/Customers/"
				, data: { ssn : ssn, name : fullName, address : address, phoneNumber : phoneNumber}
					,error: ajaxReturn_CreateError, success: ajaxReturn_CreateSuccess
				})
			
			function ajaxReturn_CreateSuccess(result, status, xhr) {
				$("#ssn").val("");
				$("#fullName").val("");
				$("#address").val("");
				$("#phoneNumber").val("");
				$("#ssn").attr("placeholder","Customer was succesfully created.");
				}
			function ajaxReturn_CreateError(result, status, xhr) {
				$("#ssn").val("");
				$("#fullName").val("");
				$("#address").val("");
				$("#phoneNumber").val("");
				$("#ssn").attr("placeholder","SSN already exists");
				$("#ssn").css("border-color", "red");
				console.log("Ajax-find customer: "+status);
				}
			}
		})
		
		// Method that runs when a user is trying to update a customer, checks input values on sends it to servlet on success
		$("#updateBtn").click( function() {
			
			ResetBorders();
			var ssn = $("#customerUpdate").val();
			var fullName = $("#fullName").val();
			var address = $("#address").val();
			var phoneNumber = $("#phoneNumber").val();
			if(ssn == null || ssn == "" || fullName == null || fullName == "" || address == null || address == "" ||
					phoneNumber == null || phoneNumber == "" || !ssn.match(/^([0-9])/) || !fullName.match(/^[a-zA-Z\s]+$/) ||
					!phoneNumber.match(/^([0-9])/) || ssn.length!=10 || phoneNumber.length!= 10){
			
				if(ssn == null || ssn == ""){
					$("#customerUpdate").val("");
					$("#customerUpdate").attr("placeholder","Please choose a valid SSN");
					$("#customerUpdate").css("border-color", "red");
				}
				if(fullName == null || fullName == ""){
					$("#fullName").val("");
					$("#fullName").attr("placeholder","Format: John Doe");
					$("#fullName").css("border-color", "red");
				}
				if(address == null || address == ""){
					$("#address").val("");
					$("#address").attr("placeholder","Format: Nobelstreet 22");
					$("#address").css("border-color", "red");
				}
				if(phoneNumber == null || phoneNumber == ""){
					$("#phoneNumber").val("");
					$("#phoneNumber").attr("placeholder","Format: 07XXXXXXXX");
					$("#phoneNumber").css("border-color", "red");
				}
				if(!ssn.match(/^([0-9])/)){
					$("#customerUpdate").val("");
					$("#customerUpdate").attr("placeholder","Format: YYMMDD-XXXX");
					$("#customerUpdate").css("border-color", "red");
				}
				if(!fullName.match(/^[a-zA-Z\s]+$/)){
					$("#fullName").val("");
					$("#fullName").attr("placeholder","Format: John Doe");
					$("#fullName").css("border-color", "red");
				}
				if(!phoneNumber.match(/^([0-9])/)){
					$("#phoneNumber").val("");
					$("#phoneNumber").attr("placeholder","Format: 07XXXXXXXX");
					$("#phoneNumber").css("border-color", "red");
				}
				
				if(ssn.length!=10){
					$("#customerUpdate").val("");
					$("#customerUpdate").attr("placeholder","Please choose a valid SSN");
					$("#customerUpdate").css("border-color", "red");
				}
				if(phoneNumber.length!= 10){
					$("#phoneNumber").val("");
					$("#phoneNumber").attr("placeholder","Format: 07XXXXXXXX");
					$("#phoneNumber").css("border-color", "red");
				}
			}

			else {$.ajax({method: "PUT",
					url: "http://localhost:8080/RestBankClient/Customers/"
						, data: { ssn : ssn, name : fullName, address : address, phoneNumber : phoneNumber} 
							,error: ajaxReturn_UpdateError, success: ajaxReturn_UpdateSuccess
					})
				
				function ajaxReturn_UpdateSuccess(result, status, xhr) {
					$("#customerUpdate").val("");
					$("#fullName").val("");
					$("#address").val("");
					$("#phoneNumber").val("");
					$("#customerUpdate").attr("placeholder","Customer was succesfully updated.");
					
					}
				function ajaxReturn_UpdateError(result, status, xhr) {
					$("option[value='" + ssn + "'").remove();
					$("#customerUpdate").val("");
					$("#fullName").val("");
					$("#address").val("");
					$("#phoneNumber").val("");
					$("#customerUpdate").attr("placeholder","Customer does not exist");
					$("#customerUpdate").css("border-color", "red");
					console.log("Ajax-find customer: "+status);
					}
				}
			})
		
		// Method that runs when a user is trying to delete a customer, checks input values and if it checks out it sends it to the servlet
		$("#deleteBtn").click( function() { 
			ResetBorders();
			var ssn = $("#customerDelete").val();
			
			if(ssn == null || ssn == "" || !ssn.match(/^([0-9])/)){
				$("#customerDelete").val("");
				$("#customerDelete").attr("placeholder","Please choose a valid SSN");
				$("#customerDelete").css("border-color", "red");
			}
			else{	
				$.ajax({method: "DELETE",
				url: "http://localhost:8080/RestBankClient/Customers/"+ssn,
				error: ajaxReturn_DeleteError,
				success: ajaxReturn_DeleteSuccess})
		
		function ajaxReturn_DeleteSuccess(result, status, xhr) {
					$("option[value='" + ssn + "'").remove();
					$("#customerDelete").val("");
					$("#customerDelete").attr("placeholder","Customer deleted");
					}
				
		function ajaxReturn_DeleteError(result, status, xhr) {
			$("option[value='" + ssn + "'").remove();
			$("#customerDelete").val("");
			$("#customerDelete").attr("placeholder","The customer does not exist");
			$("#customerDelete").css("border-color", "red");
			console.log("Ajax-find customer: "+status);
			}
		}
			})
			
			// when typing or choosing a value in update combobox the customers attributes are populated in the input textfields when found.
		$("#customerUpdate").on('input', function() { 
			var ssn = $("#customerUpdate").val();
			if(ssn != "") {
				$.ajax({method: "GET",
					url: "http://localhost:8080/RestBankClient/Customers/"+ssn,
					error: ajaxReturn_AutoCompleteError,
					success: ajaxReturn_AutoCompleteSuccess})
		
		function ajaxReturn_AutoCompleteSuccess(result, status, xhr) {
					$("#fullName").val(result.fullName);
					$("#address").val(result.address);
					$("#phoneNumber").val(result.phoneNumber);
					}
				
		function ajaxReturn_AutoCompleteError(result, status, xhr) {
			console.log("Ajax-find customer: "+status);
			}
		}
			})

	});
	// End ready function

// method that parses the result from the servlet when getting the ip, city, lonitude and latiude and displays it in our user interface
function ParseJsonFile(result) {
	var lat = result.latitude;
	var long = result.longitude;
	var city = result.city;
	var ipNbr = result.ip;

	$("#city").text(city);
	$("#ipNbr").text(ipNbr);

	$.ajax({
		method: "GET",
		url: "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+long+"&units=metric"+"&APPID=3c4a2cfac646d01a2d99c5bff403a37f",
		error: ajaxWeatherReturn_Error,
		success: ajaxWeatherReturn_Success
	})

// Method that sets the weather in our user interface
function ajaxWeatherReturn_Success(result, status, xhr) {
	var sunrise = result.sys.sunrise;
	var sunset = result.sys.sunset;
	var sunriseDate = new Date(sunrise*1000);
	var timeStrSunrise = sunriseDate.toLocaleTimeString();
	var sunsetDate = new Date(sunset*1000);
	var timeStrSunset = sunsetDate.toLocaleTimeString();

	$("#sunrise").text("Sunrise: "+timeStrSunrise);
	$("#sunset").text("Sunset: "+timeStrSunset);
	$("#weather").text(result.weather[0].main);
	$("#degree").text(result.main.temp+" \u2103");
	}// ajaxWeather Return_Success

function ajaxWeatherReturn_Error(result, status, xhr) {
	alert("Error i OpenWeaterMap Ajax");
	console.log("Ajax-find movie: "+status);
	}
	
//Method that clears fields
	function clearFields() {
		$("#ssn").val("");
		$("#fullName").val("");
		$("#address").val("");
		$("#phoneNumber").val("");
		}
	}
	
// Method that parses the Json object and puts the values in the right fields
	function parseJsonFileCustomer(result) {
		console.log("parseJsonFile");
		$("#fullName").val(result.fullName);
		$("#address").val(result.address);
		$("#phoneNumber").val(result.phoneNumber);
		}
	
	// Method that populates the combobox with the values (ssn) returned from get all customers
	function ParseJsonFileSSN(result){
		
		for(var i = 0; i < result.length ; i++){
		$("#customers").append("<option value='" + 
                result[i].ssn + "'></option>");
		}
	}
	

	// Method that resets the borders of the textfields to normal

	function ResetBorders(){
		$("#customerFind").css("border-color", "white");
		$("#customerUpdate").css("border-color", "white");
		$("#customerDelete").css("border-color", "white");
		$("#customerFind").attr("placeholder","");
		$("#customerUpdate").attr("placeholder","");
		$("#customerDelete").attr("placeholder","");
		$("#ssn").css("border-color", "white");
		$("#fullName").css("border-color", "white");
		$("#address").css("border-color", "white");
		$("#phoneNumber").css("border-color", "white");
		$("#ssn").attr("placeholder","");
		$("#fullName").attr("placeholder","");
		$("#address").attr("placeholder","");
		$("#phoneNumber").attr("placeholder","");
	}