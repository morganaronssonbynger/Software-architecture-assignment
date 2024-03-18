package org.ics.ejb.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Account.findAll", query = "SELECT a FROM Account a"),
})
@Table(name="Account")
public class Account implements Serializable {
	private String accountNumber;
	private double balance;
	private Customer customer;
	
	@Id
	@Column(name="accountNumber")
	public String getAccountNumber() {
		return accountNumber;
		}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		}
	
	@Column(name="balance")
	public double getBalance() {
		return balance;
		}
	
	public void setBalance(double balance) {
		this.balance = balance;
		}
	
	//Connects Account to customer
	@ManyToOne
	@JoinColumn(name ="customerSsn", referencedColumnName ="ssn")
	public Customer getCustomer() {
		return customer;
		}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		}
}
