package org.ics.ejb.utilities;

public class Feedback {
	
	//Error messege method. Here we declare and collect all the error messeges for the JSP pages.
	public static String errorMessages(String messege) {
		
		if(messege.equals("registrationFail")) {
			return "Registration failed. Someone with that SSN is already registered.";
		}
		else if(messege.equals("logInFail")) {
			return "Login in failed. Please write valid SSN or register.";
		}
		else if(messege.equals("removedFromOtherClient")) {
			return "Customer were removed from other client, please call customer service at 0730880819";			
		}
		else if(messege.contains("1.deposit")) {
			return "Deposit of negative amount is not allowed. Deposit rejected";
		}
		else if(messege.contains("2.deposit")) {
			return "Can't find account. Deposit rejected.";
		}	
		else if(messege.contains("1.withdraw")) {
			return "Overdrawing the account. Withdraw was rejected.";
		}
		else if(messege.contains("2.withdraw")) {
			return "Withdraw of negative amount is not allowed. Withdraw rejected.";
		}
		else if(messege.contains("3.withdraw")) {
			return "Can't find account. Withdraw rejected";
		}
		else{
			return "Please call customer service";
		}
		
	}
	
	//Success messege method. Here we declare and collect all the success messeges for the JSP pages.
	public static String successMessages(String messege) {
		
		if(messege.equals("registrationSuccess")) {
			return "Registration succeded!";
		}
		else if(messege.equals("updateSuccess")) {
			return "Update was a success";		
		}
		else if(messege.equals("depositSuccess")) {
			return "Deposit was made successfully";
		}
		else if(messege.equals("withdrawSuccess")) {
			return "Withdraw was made successfully";
		}		
		
		else{
			return "Please call customer service";
		}
	}
	
	

}
