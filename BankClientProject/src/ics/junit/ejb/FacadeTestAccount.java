package ics.junit.ejb;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.ics.ejb.Entity.Account;
import org.ics.ejb.Entity.Customer;
import org.ics.facade.FacadeLocal;

import junit.framework.TestCase;

public class FacadeTestAccount extends TestCase {

	//Declaring variabels
	FacadeLocal facade;
	Customer c1;
	String expectedSsn;
	String expectedName;
	String expectedAddress;
	String expectedPhoneNumber;
	
	Account a1;
	double expectedBalance;
	
	public FacadeTestAccount(String name) {
		super(name);
	}
	

	//Set up method who creates objects for test
	protected void setUp() throws Exception {
		super.setUp();
		expectedSsn = "9910205445";
		expectedName = "John Doe";
		expectedAddress = "Nobelvägen 52";
		expectedPhoneNumber = "0704987667";
		
		expectedBalance = 0.0;
		Context context = new InitialContext();
		facade =(FacadeLocal)context.lookup("java:app/BankEJBProject/Facade!org.ics.facade.FacadeLocal");
		c1 = new Customer();
		c1.setSsn(expectedSsn);
		c1.setName(expectedName);
		c1.setAddress(expectedAddress);
		c1.setPhoneNumber(expectedPhoneNumber);
	}

	//Tear down method who deletes object after test
	protected void tearDown() throws Exception {
		super.tearDown();
		facade.deleteAccount(a1.getAccountNumber());
		facade.deleteCustomer(expectedSsn);
		facade= null;
		c1 = null;
		a1= null;
	}	
	
	//Test method which tests account bean and facades create customer and openaccounts method
	public void testFacadeMethodsAccount() throws Exception{
		c1 = facade.createCustomer(c1);
		a1 = facade.openAccount(expectedBalance, expectedSsn);
		assertEquals(a1.getBalance(), expectedBalance);
	}

}
