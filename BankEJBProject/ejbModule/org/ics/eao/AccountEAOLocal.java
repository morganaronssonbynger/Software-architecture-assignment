package org.ics.eao;

import java.util.List;

import javax.ejb.Local;

import org.ics.ejb.Entity.Account;
import org.ics.exceptions.MyAccountException;

@Local
public interface AccountEAOLocal {
	public Account findByAccountNbr(String id);
	public Account createAccount(Account account);
	public Account updateAccount(Account account);
	public void deleteAccount(String id);
	public void deposit(String accountNumber, double amount)
	throws MyAccountException; 
	public void withdraw(String accountNumber, double amount)
	throws MyAccountException;
	public List<Account> findAll();

}
