
// Checks if SSN field is filled in for login page
function checkLogIn(){
	const ssnValue = document.getElementById("SSN").value;
	const ssn = document.getElementById("SSN");
	ssn.style.border = "1px solid white";
	if(ssnValue == null || ssnValue == ""){
		ssn.style.border = "2px solid red";
		ssn.placeholder = "Please enter your SSN.";
		
		return false;
	}
}
// Resets borders for the checkRegister class 
function resetBorders() {

	document.getElementById("SSN").style.border = "1px solid white";
	document.getElementById("fullName").style.border = "1px solid white";
	document.getElementById("address").style.border = "1px solid white";
	document.getElementById("phoneNumber").style.border = "1px solid white";
}

// Checks if all fields are filled in and filled in correctly 
function checkRegister(){

	resetBorders();
	const ssn = document.getElementById("SSN").value;
	const fullName = document.getElementById("fullName").value;
	const address = document.getElementById("address").value;
	const phoneNumber = document.getElementById("phoneNumber").value;
	
	// Creates array to hold the text field 
	let fields = [document.getElementById("SSN"), document.getElementById("fullName"), document.getElementById("address"), document.getElementById("phoneNumber") ];
	
	// Creates array to hold error message to the corresponding text field
	let errorMessage =["Digits only from 0-9.", "Letters only.", "Your full address.", "Digits only from 0-9."];
	
	if(ssn == null || ssn == "" || fullName == null || fullName == "" || address == null || address == "" || phoneNumber == null || phoneNumber == "" || !ssn.match(/^([0-9])/) || !fullName.match(/^[a-zA-Z\s]+$/) || !phoneNumber.match(/^([0-9])/)){
			
		// loops through all text fields to check if it is filled in correctly 
		for(let field of fields){
			if(field.value == ssn){
				if(!ssn.match(/^([0-9])/)) {
					var tmp = fields.indexOf(field);
					field.style.border = "2px solid red";
					field.value = "";
					field.placeholder = errorMessage[tmp];
				}
			}
			if(field.value == fullName) {
				if(!fullName.match(/^[a-zA-Z\s]+$/)) {
					var tmp = fields.indexOf(field);
					field.style.border = "2px solid red";
					field.value = "";
					field.placeholder = errorMessage[tmp];
				}
			}			
			if(field.value == phoneNumber){
				if(!phoneNumber.match(/^([0-9])/)){
					var tmp = fields.indexOf(field);
					field.style.border = "2px solid red";
					field.value = "";
					field.placeholder = errorMessage[tmp];
				}			
			}			
			if(field.value == null || field.value == ""){
				var tmp = fields.indexOf(field);
				field.style.border = "2px solid red";			
				field.placeholder = errorMessage[tmp];
			}		
		}
		return false;
	}
	
}
// Checks if all fields are filled in correctly 
function checkAccount(){		
	
	let accountNumber = document.getElementById("account");
	const amount = document.getElementById("amount");
	accountNumber.style.border = "1px solid white";
	amount.style.border = "1px solid white";
	
	let accLength = accountNumber.value.length;
	
	let myBoolean = true;
	
		
		if (accountNumber.value == null || accountNumber.value == "" || accLength < 9 || !accountNumber.value.match(/SEK/)) {
			accountNumber.style.border = "2px solid red";
			accountNumber.placeholder = "Please choose account.";
			 
			accountNumber.value = "";
			myBoolean = false;
			
		}
		if (amount.value == null || amount.value == "" || !amount.value.match(/^([0-9])/)) {
			amount.style.border = "2px solid red";
			amount.placeholder = "Please enter amount.";
			amount.value = "";
			myBoolean = false;
		}
		
		return myBoolean;
		
}
// timer for error message box 
setTimeout(function () {
    document.querySelector(".displayBox").style.display = "none";
  }, 4000);




	