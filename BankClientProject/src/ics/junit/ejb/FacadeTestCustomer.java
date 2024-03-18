package ics.junit.ejb;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.ics.ejb.Entity.Customer;
import org.ics.facade.FacadeLocal;

import junit.framework.TestCase;

public class FacadeTestCustomer extends TestCase {
	
	//Declaring variabels
	FacadeLocal facade;
	Customer c1;
	Customer c2;
	String expectedSsn;
	String expectedName;
	String expectedAddress;
	String expectedPhoneNumber;

	
	public FacadeTestCustomer(String name) {
		super(name);
	}

	//Set up method who creates objects for test
	protected void setUp() throws Exception {
		super.setUp();
		expectedSsn = "9910205445";
		expectedName = "John Doe";
		expectedAddress = "Nobelvägen 52";
		expectedPhoneNumber = "0704987667";
		
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
		facade.deleteCustomer(expectedSsn);
		facade= null;
		c1 = null;
		c2 = null;
	}
	

	//Test method which tests customers bean and facades create customer and find customer method
	public void testFacadeMethods() throws Exception {
		c1 = facade.createCustomer(c1);
		c2 = facade.findByCustomerId(expectedSsn);
		assertEquals(c1.getSsn(), expectedSsn);
		assertEquals(c1.getName(), expectedName);
		assertEquals(c1.getAddress(), expectedAddress);
		assertEquals(c1.getPhoneNumber(), expectedPhoneNumber);
		assertEquals(c2.getSsn(), expectedSsn);
		assertEquals(c2.getName(), expectedName);
		assertEquals(c2.getAddress(), expectedAddress);
		assertEquals(c2.getPhoneNumber(), expectedPhoneNumber);
		}

}
