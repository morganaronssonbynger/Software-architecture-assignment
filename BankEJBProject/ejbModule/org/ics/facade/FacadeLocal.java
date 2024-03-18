package org.ics.facade;

import java.util.List;

import javax.ejb.Local;

import org.ics.ejb.Entity.Account;
import org.ics.ejb.Entity.Customer;
import org.ics.exceptions.MyAccountException;

@Local
public interface FacadeLocal {

	//Facade interface to hide code and information for frontend
	
	public Account findByAccountId(String id);
	public Account openAccount(double amount, String customerId);
	public Account updateAccount(Account account);
	public void deleteAccount(String nbr);
	public void deposit(String accountNumber, double amount) throws MyAccountException;
	public void withdraw(String accountNumber, double amount) throws MyAccountException;
	public Customer findByCustomerId(String id);
	public Customer createCustomer(Customer customer); 
	public Customer updateCustomer(Customer customer); 
	public void deleteCustomer(String id);
	public List<Customer> findAllCustomers();
	public List<Account> findAllAccounts();
}
