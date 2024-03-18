package org.ics.ejb.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Customer.findAll", query = "SELECT c FROM Customer c"),
})
	

@Table(name="Customer")
public class Customer implements Serializable {
	private String ssn;
	private String name;
	private String address;
	private String phoneNumber;
	private List<Account> accounts;
	
	@Id
	@Column(name="ssn")
	public String getSsn() {
		return ssn;
		}
	
	public void setSsn(String ssn) {
		this.ssn = ssn;
		}
	
	@Column(name="name")
	public String getName() {
		return name;
		}
	
	public void setName(String name) {
		this.name = name;
		}
	
	@Column(name="address")
	public String getAddress() {
		return address;
		}
	
	public void setAddress(String address) {
		this.address = address;
		}
	
	@Column(name="phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
		}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		}
	
	//Connects Customer to accounts
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	public List<Account> getAccounts() {
		return accounts;
		}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
		}
}
