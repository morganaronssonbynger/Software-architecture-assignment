package org.ics.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ics.ejb.Entity.Account;
import org.ics.ejb.Entity.Customer;
import org.ics.exceptions.MyAccountException;

/**
 * Session Bean implementation class AccountEAO
 */
@Stateless
public class AccountEAO implements AccountEAOLocal {

	//Sets persistence context
	@PersistenceContext(unitName="LabEJBSql")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public AccountEAO() {
        // TODO Auto-generated constructor stub
    }
    
    // method that finds a specific account and returns it
    public Account findByAccountNbr(String id){
    	return em.find(Account.class, id); }
    
    // Method that creates a account
    public Account createAccount(Account account) { 
    	em.persist(account);
    return account;
    }
    
    // Method that updates an account
    public Account updateAccount(Account account) {
    	em.merge(account);
    return account;
    }
    
    // Method that deletes account by id
    public void deleteAccount(String id) { 
    	Account a = this.findByAccountNbr(id); 
    	if (a != null) {
         em.remove(a);
      }
    }
    
    // Method that deposit money into an account
    public void deposit(String accountNumber, double amount) throws MyAccountException{
    	Account account = this.findByAccountNbr(accountNumber);
    	if(account!=null) { 
    		if (amount > 0) {
    			double new_balance = account.getBalance() + amount; 
    			account.setBalance(new_balance); 
    			this.updateAccount(account);
    		}else {
    			throw new MyAccountException("Deposit of negative amount is not allowed. Error code 1.deposit");
    		}
    	}
    	else {
    		throw new MyAccountException("Can't find account. Deposit rejected. Error code 2.deposit");
    	}
    }
    
    // Method that withdraws money from an account
    public void withdraw(String accountNumber, double amount) throws MyAccountException{
    	Account account = this.findByAccountNbr(accountNumber); 
    	if(account!=null) {
    		if (amount > account.getBalance()) {
    			throw new MyAccountException("Overdrawing the account. Withdraw was rejected. Error code 1.withdraw"); 
    		}else if (amount < 0){
    			throw new MyAccountException ("Withdraw of negative amount is not allowed. Error code 2.withdraw");
    		}else {
    			double new_balance=account.getBalance()-amount; 
    			account.setBalance(new_balance); 
    			this.updateAccount(account);
    		} 
    	}
    	else {
    		throw new MyAccountException("Can't find account. Withdraw rejected. Error code 3.withdraw");
    	}
    }
    
    //Method that finds all accounts
    public List<Account> findAll(){
		TypedQuery<Account> query = em.createNamedQuery("Account.findAll", Account.class);
		List<Account> listOfAccounts = query.getResultList();
		return listOfAccounts;
	}
    

    
}
