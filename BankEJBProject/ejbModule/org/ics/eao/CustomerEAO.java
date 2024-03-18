package org.ics.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ics.ejb.Entity.Customer;

/**
 * Session Bean implementation class CustomerEAO
 */
@Stateless
public class CustomerEAO implements CustomerEAOLocal {

	// Setts the persistence context
	@PersistenceContext(unitName="LabEJBSql") 
	private EntityManager em;
	
	public CustomerEAO() {
	// TODO Auto-generated constructor stub
	}
	
	// Method that finds a specific customer
	public Customer findByCustomerId(String id){
		return em.find(Customer.class, id);
	}
	
	// Method that creates a customer
	public Customer createCustomer(Customer customer) { 
		em.persist(customer);
	return customer;
	}
	
	// Method that updates a customer
	public Customer updateCustomer(Customer customer) {
		em.merge(customer);
	return customer;
	}
	
	// Method that deletes a customer
	public void deleteCustomer(String id) {
		Customer a = this.findByCustomerId(id);
		if (a != null) {
			em.remove(a);
	    }
	}
	
	// Method that return all customers
	public List<Customer> findAll(){
		TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
		List<Customer> listOfCustomers = query.getResultList();
		return listOfCustomers;
	}
}
