package org.ics.exceptions;

//Own created exception class to fulfill needs of system rules and send Friendly error messeges when rules not obeyed.
public class MyAccountException extends Exception{

	//Default constructor
	public MyAccountException() {
		
	}
	
	//Constructor which make it possible to create own error messeges to throw when systemrules not obeyed.
	public MyAccountException(String msg) {
		super(msg);
	}
}
