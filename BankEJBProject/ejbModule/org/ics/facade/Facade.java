package org.ics.facade;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.ics.eao.AccountEAOLocal;
import org.ics.eao.CustomerEAOLocal;
import org.ics.ejb.Entity.Account;
import org.ics.ejb.Entity.Customer;
import org.ics.exceptions.MyAccountException;

/**
 * Session Bean implementation class Facade
 */
@Stateless
public class Facade implements FacadeLocal {

	@EJB
    CustomerEAOLocal customerEAO;
    @EJB
    AccountEAOLocal accountEAO;
    
    /**
     * Default constructor. 
     */
    public Facade() {
    	
    }
    
    public Account findByAccountId(String id){
    	return accountEAO.findByAccountNbr(id); 
    }
    
    //Open an account method/ create
    public Account openAccount(double amount, String customerId){
    	Account newAccount = null;
    	
    	Customer c = this.findByCustomerId(customerId);
    	
    	if(c != null) {
    		newAccount = new Account(); 
    		
    		//Autogenerating ID for the Account
    		Random rnd = new Random();
    		int i = rnd.nextInt((99999 - 10000) + 1) + 10000;    		 		
    		String lastIndexes = String.valueOf(i); 		
    		String accountNumber = "5355" + lastIndexes;
    		
    		newAccount.setAccountNumber(accountNumber); 
    		newAccount.setBalance(amount);
    		newAccount.setCustomer(c);
    		
    		//Connect the customer and account
    		newAccount = accountEAO.createAccount(newAccount);
    		customerEAO.updateCustomer(c);
    	}
    	return newAccount;	 	
    }
    
    // Update account method
    public Account updateAccount(Account account) { 
    	return accountEAO.updateAccount(account);
    }
    
    // Delete account method
    public void deleteAccount(String nbr) { 
    	accountEAO.deleteAccount(nbr);
    }
    
    // Deposit money to an account method
    public void deposit(String accountNumber, double amount) throws MyAccountException {
    	accountEAO.deposit(accountNumber, amount);

    }
    
    // Withdraw from an account method
    public void withdraw(String accountNumber, double amount) throws MyAccountException{
	    accountEAO.withdraw(accountNumber, amount); 
    }
    
    // Method that finds all accounts
    public List<Account> findAllAccounts(){
    	return accountEAO.findAll();
    }
    
    // Method that finds a customer by id
    public Customer findByCustomerId(String id){
    	return customerEAO.findByCustomerId(id);
    }
    
    // Method that creates a new customer
    public Customer createCustomer(Customer customer) {
    	return customerEAO.createCustomer(customer);
    }
    
    // Method that updates a customers values
    public Customer updateCustomer(Customer customer) {
    	return customerEAO.updateCustomer(customer);
    }
    
    // Method that deletes a customer
    public void deleteCustomer(String id) { 
    	customerEAO.deleteCustomer(id);
    }
    
    // Method that finds all customers
    public List<Customer> findAllCustomers(){
    	return customerEAO.findAll();
    }

}
