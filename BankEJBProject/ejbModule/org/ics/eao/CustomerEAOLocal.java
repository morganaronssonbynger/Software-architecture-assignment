package org.ics.eao;

import java.util.List;

import javax.ejb.Local;

import org.ics.ejb.Entity.Customer;

@Local
public interface CustomerEAOLocal {

	public Customer findByCustomerId(String id);
	public Customer createCustomer(Customer customer); 
	public Customer updateCustomer(Customer customer); 
	public void deleteCustomer(String id);
	public List<Customer> findAll();
}
