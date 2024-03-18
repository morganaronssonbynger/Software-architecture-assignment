package org.ics.ejb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ics.ejb.Entity.Account;
import org.ics.ejb.Entity.Customer;
import org.ics.ejb.utilities.Feedback;
import org.ics.exceptions.MyAccountException;
import org.ics.facade.FacadeLocal;

/**
 * Servlet implementation class BankServlet
 */
@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
    private FacadeLocal facade;
	
	//Declaring variabals that are later used in the Bankservlet.
	Customer c1 = null;
	HttpSession session = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = null;
		String operation = request.getParameter("operation");
		
		//Log in
		if(operation.equals("login")) { 
			String ssn = request.getParameter("txtSSN");
			c1 = facade.findByCustomerId(ssn);
			if(c1 != null){
				url = "/Home.jsp";
				
				//On login we start the session and set a attribute that later is used in JSP files, so they know what customer is Logged in.
				session = request.getSession();
				session.setAttribute("customer", c1);
				
			}
			else {
				String messege = Feedback.errorMessages("logInFail");
				request.setAttribute("logInResponse", messege);
				url = "/LogIn.jsp";	
			}
			
		}
		
		//Log out
		else if(operation.equals("logout")) {
			url = "/LogIn.jsp";
			c1 = null;
			session.invalidate();
		}
		
		/*Method for menybar navigation. First we get the common distributor parameter and then check the value on the menyItem.
		We have to use forms to navigate because we have to update the session variabel customer.
		*/
		else if(operation.equals("distributor")) { 
			String item = request.getParameter("menyItem");
			 c1 = facade.findByCustomerId(c1.getSsn());
			 if(c1 != null){
					session.setAttribute("customer", c1);
				 if(item.equals("toHome")) {
					url = "/Home.jsp";				
				 }
				 else if(item.equals("toAccounts")) {
					 url = "/Accounts.jsp";
				 }
				 else if(item.equals("toWithdraw")) {
					 url = "/Withdraw.jsp";
				 }
				 else if(item.equals("toDeposit")) {
					 url = "/Deposit.jsp";
				 }
				 else if(item.equals("toSettings")) {
					 url = "/Settings.jsp";
				 }
			 }
			else {
				url = "/LogIn.jsp";
				String messege = Feedback.errorMessages("removedFromOtherClient");
				request.setAttribute("logInBar", messege);
			}
		}
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		String operation = request.getParameter("operation");
		
		try {
			
			//Register customer
			if (operation.equals("register")) {
				url = "/Register.jsp";
				String ssn = request.getParameter("SSN");
				String name = request.getParameter("fullName");
				String address = request.getParameter("address");
				String phoneNumber = request.getParameter("phoneNumber");
				
				if(facade.findByCustomerId(ssn) == null) {
					Customer c = new Customer();
					c.setSsn(ssn);
					c.setName(name);
					c.setAddress(address);
					c.setPhoneNumber(phoneNumber);
					
					facade.createCustomer(c);
					
					String messege = Feedback.successMessages("registrationSuccess");					
					request.setAttribute("registerResponse", messege);				
				}
				
				else {
					String messege = Feedback.errorMessages("registrationFail");
					request.setAttribute("registerResponse", messege);	
				}						
			}
			
			//Deposit submit
			else if (operation.equals("deposit")) {
				url = "/Deposit.jsp";
				
				String tmpAccountNumber = request.getParameter("account");
				String accountNumber = tmpAccountNumber.substring(0, 9);
				String amountTxt = request.getParameter("amount");
				double amount = Double.parseDouble(amountTxt);
				String messege = "";
				
				try {
					facade.deposit(accountNumber, amount);
					messege = Feedback.successMessages("depositSuccess");					
				}
				catch(MyAccountException exe) {
					messege = Feedback.errorMessages(exe.getMessage());		
				}
				
				request.setAttribute("depositResponse", messege);
				
				//This is for updating the datalist after deposit.
				c1 = facade.findByCustomerId(c1.getSsn());
				session.setAttribute("customer", c1);
			}
			
			
			//Withdraw submit
			else if (operation.equals("withdraw")) {
				url = "/Withdraw.jsp";
				
				String tmpAccountNumber = request.getParameter("account");
				String accountNumber = tmpAccountNumber.substring(0, 9);
				String amountTxt = request.getParameter("amount");
				double amount = Double.parseDouble(amountTxt);
				String messege = "";
				
				try {
					facade.withdraw(accountNumber, amount);
					messege = Feedback.successMessages("withdrawSuccess");			
				}
				catch(MyAccountException exe) {
					messege = Feedback.errorMessages(exe.getMessage());
				}
				
				request.setAttribute("withdrawResponse", messege);
				
				//This is for updating the datalist after withdraw.
				c1 = facade.findByCustomerId(c1.getSsn());
				session.setAttribute("customer", c1);
				
			}
			
			//Because the Settings/profile page have two buttons on the same form. We have to divide them and check their submit button value.
			else if (operation.equals("settings")) {
				
				//Update customer submit
				if(request.getParameter("settingsBtn").equals("Save changes")){
					url = "/Settings.jsp";
					
					String ssn = request.getParameter("SSN");				
					c1 = facade.findByCustomerId(ssn);
					if(c1 != null) {
						c1.setName(request.getParameter("fullName"));
						c1.setAddress(request.getParameter("address"));
						c1.setPhoneNumber(request.getParameter("phoneNumber"));
						
						facade.updateCustomer(c1);
						session.setAttribute("customer", c1);
						
						String messege = Feedback.successMessages("updateSuccess");						
						request.setAttribute("profileResponse", messege);						
					}
					
				}
				
				//Remove Customer submit
				else if(request.getParameter("settingsBtn").equals("Unregister")) {
					url = "/LogIn.jsp";

					String ssn = request.getParameter("SSN");
					c1 = facade.findByCustomerId(ssn);
					List<Account> accounts = c1.getAccounts();
					
					if(!accounts.isEmpty()) {
						for(Account a : accounts) {
							facade.deleteAccount(a.getAccountNumber());
						}
					}
					facade.deleteCustomer(ssn);
					
					//After removing we clear the variabls.
					c1 = null;
					session.invalidate();
	
				}
			}
			
			//Because the Accounts page have two buttons on the same form. We have to divide them and check their submit button value.
			else if (operation.equals("createDeleteAccount")) {
				
				//Create account submit
				if(request.getParameter("createdeleteBtn").equals("Create New Account")){
					url = "/Accounts.jsp";

					if(facade.findByCustomerId(c1.getSsn()) != null) {
						facade.openAccount(0, c1.getSsn());
						
						//This is needed to update Account page with correct values
						c1 = facade.findByCustomerId(c1.getSsn());
						session.setAttribute("customer", c1);
					}
					else{
						url = "/LogIn.jsp";
						String messege = Feedback.errorMessages("removedFromOtherClient");
						request.setAttribute("logInBar", messege);
					}
						
					
				}
				
				//Remove account submit
				else if(request.getParameter("createdeleteBtn").equals("Remove Account")) {
					url = "/Accounts.jsp";
					
					String accountNumber = request.getParameter("accountNumber");
					//Have to nullcheck here because inputID is dynamic on HTML input attribute. So can't check in javascriptfunction as we don't know what the id is.
					if(accountNumber != null) {
						
						facade.deleteAccount(accountNumber);
						
						//This is needed to update Account page with correct values.
						c1 = facade.findByCustomerId(c1.getSsn());
						session.setAttribute("customer", c1);	
					}
					else {
						System.out.println("Error in remove account. Not chosen an account.");
					}		
				}
			}
			
			else {
			url ="/LogIn.jsp";
			} 

		}catch(Exception exception) {
			
			System.out.println("Skrivs alltid ut om kommer in i catch");
			url ="/LogIn.jsp";
			
			if(exception.getMessage().contains("JDBCConnectionException")) {
				System.out.println("JDBC server down error");
			}
			else if(exception.getMessage().contains("SQLException")) {
				System.out.println("Please call customerservice at 0730880819 and tell them about SQL exception");
			}		
			else {
				System.out.println("Please call customerservice at 0730880819");
			}
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
