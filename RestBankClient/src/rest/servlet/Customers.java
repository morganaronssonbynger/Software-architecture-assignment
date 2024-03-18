package rest.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ics.ejb.Entity.Account;
import org.ics.ejb.Entity.Customer;
import org.ics.facade.FacadeLocal;

/**
 * Servlet implementation class Customers
 */
@WebServlet("/Customers/*")
public class Customers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	FacadeLocal facade;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customers() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = request.getPathInfo();

		//Find all customers

		if (pathInfo == null || pathInfo.equals("/")) {
			System.out.println("Alla");
			System.out.println("1: " + pathInfo);
			List<Customer> allCustomers = facade.findAllCustomers();
			sendAsJson(response, allCustomers);
			return;
		}


		//Check if they are to many slashes in URL

		String[] splits = pathInfo.split("/");
		if (splits.length != 2) {;
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		


		// If the client wants to find a specific customer

		else if (splits.length == 2) {
			String id = splits[1];
			Customer customer = facade.findByCustomerId(id);
			if (customer==null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			else {
				sendAsJson(response, customer);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	// Rest method that creates a customer if the customer does not exist
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Gets values from JSP pages
		String ssn = request.getParameter("ssn");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");

		Customer customer = facade.findByCustomerId(ssn);

		if (customer != null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		else if (customer == null) {

			//Creates a Customer
			customer = new Customer();
			customer.setSsn(ssn);
			customer.setName(name);
			customer.setAddress(address);
			customer.setPhoneNumber(phoneNumber);

			facade.createCustomer(customer);
			sendAsJson(response, customer);
		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	// Rest method that updates a customer if it exists
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Gets values from JSP pages
		String ssn = request.getParameter("ssn");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");

		Customer customer = facade.findByCustomerId(ssn);
		
		if (customer == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//Updates customer and sends as JSON to client
		else if (customer != null) {
			customer.setName(name);
			customer.setAddress(address);
			customer.setPhoneNumber(phoneNumber);
			facade.updateCustomer(customer);
			sendAsJson(response, customer);
		}
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	
	// Rest method that deletes a customer and all its accounts
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//No slashes so can't find customer for delete
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		//To many slashes
		String[] splits = pathInfo.split("/");
		if (splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		//Get the id from the URL split, deletes all Customers accounts and then deletes the customer
		String id = splits[1];
		Customer customer = facade.findByCustomerId(id);
		if (customer != null) {
			
			List<Account>accounts = customer.getAccounts();
			
			if(!accounts.isEmpty()) {
				
				for(Account a : accounts) {
					facade.deleteAccount(a.getAccountNumber());
				}
			}
			facade.deleteCustomer(id);
		} 
		if (customer == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		sendAsJson(response, customer);
	}
	
	// Method that send a Customer as a json object

	//Send a customer as a JSON object
	private void sendAsJson(HttpServletResponse response, Customer customer) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");

		if (customer != null) {
			out.print("{\"ssn\":");
			out.print("\"" + customer.getSsn() + "\"");
			out.print(",\"fullName\":");
			out.print("\"" + customer.getName() + "\"");
			out.print(",\"address\":");
			out.print("\"" + customer.getAddress() + "\"");
			out.print(",\"phoneNumber\":");
			out.print("\"" + customer.getPhoneNumber() + "\"}");
		}

		else {
			out.print("{ }");
		}

		out.flush();
	}

	// Method that sends customers as Json objects in a Json array

	private void sendAsJson(HttpServletResponse response, List<Customer> customers) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		if (customers != null) {
			JsonArrayBuilder array = Json.createArrayBuilder();
			for (Customer c : customers) {
				JsonObjectBuilder o = Json.createObjectBuilder();
				o.add("ssn", c.getSsn());
				o.add("fullName", c.getName());
				o.add("address", c.getAddress());
				o.add("phoneNumber", c.getPhoneNumber());
				array.add(o);
			}
			JsonArray jsonArray = array.build();
			System.out.println("Customer Rest: " + jsonArray);
			out.print(jsonArray);
		} else {
			out.print("[]");
		}
		out.flush();
	}

}
