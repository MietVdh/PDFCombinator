package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algonquin.pdfcombinator.beans.User;
import com.algonquin.pdfcombinator.dao.ApplicationDao;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	// Miet's code
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Check if user is already logged in
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/register.jsp");
			dispatcher.forward(request, response);
		} else {
			request.getRequestDispatcher("/html/account.jsp").forward(request, response);
		}
		
		
	}
	/*
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve information entered
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String userName = request.getParameter("uname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		// Check if username already exists
		
		
		// Check if passwords match
		if (!password2.equals(password)) {
			// error: passwords don't match -> try again
		} 
		
		
		// Create User object
		User user = new User(firstName, lastName, userName, email, password);
		System.out.println(user.getFirstName() + " " + user.getLastName() + " has been registered");
		
		
		// Add user to database
		
		
		
		// Send verification email
		
		
		// Forward to confirmation page
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirm");
		dispatcher.forward(request, response);
		
		
		
		doGet(request, response);
	}
	
	
	public String getHTMLString(String filePath) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(filePath));
    	String line = "";
    	StringBuffer buffer = new StringBuffer();
    	while ((line=reader.readLine())!= null) {
    		buffer.append(line);
    	}
    	
    	reader.close();
    	String page = buffer.toString();
    	
//    	page = MessageFormat.format(page);
    	
    	return page;
    }

	*/
	
	/*  Serap's code Register
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.getWriter().println("Successfully registered");
    	
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
     
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
         
        try {
         
            
            Class.forName("com.mysql.jdbc.Driver");
             
            //creating connection with the database
            Connection con = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/register\", \"root\", \"1234");
 
            PreparedStatement ps = con.prepareStatement
                        ("insert into register values(?,?,?)");
 
            ps.setString(1, firstname);
            ps.setString(1, lastname);
            ps.setString(2, email);
            ps.setString(3, password);
            int i = ps.executeUpdate();
             
            if(i > 0) {
                out.println("You are successfully registered at XYZ");
            }
         
        }
        catch(Exception se) {
            se.printStackTrace();
        }
     
    }
    
    */
	
// Serap's code RegisterUser
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// collect all form data
		String userName = req.getParameter("uname");
		String email = req.getParameter("email");
		String first = req.getParameter("fname");
		String last = req.getParameter("lname");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		
		// prepare an information message for user about the success or failure of the operation
		String infoMessage = "";
		
		if (!password2.equals(password)) {
			// error: passwords don't match -> try again
			infoMessage = "The passwords don't match. Please try again.";
			req.setAttribute("message", infoMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/register.jsp");
			dispatcher.forward(req, resp);
			return;
		} 

		// fill it up in a User bean
		User user = new User(first, last, userName, email, password);
		
		// call DAO layer and save the user object to DB
		ApplicationDao dao = new ApplicationDao();
		
		// check if username is available
		if (!dao.isUsernameAvailable(userName)) {
			infoMessage = "Sorry, that username is not available. Please choose a different username.";
			req.setAttribute("message", infoMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/register.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		
		
		int rows = 0;

		String code = UUID.randomUUID().toString();
		rows = dao.registerUser(user, code);
		

		
		if (rows==0) {
			infoMessage="Sorry, an error occurred!";
			req.setAttribute("message", infoMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/register.jsp");
			dispatcher.forward(req, resp);
			
		}
		else {
			infoMessage="User registered successfully!";
			// TODO generate verification email 
			
			String id = user.getID().toString();
			
			String link = "";
			
			try {
				URI verificationLink = new URI("http://localhost:8080/PDFCombinator/verify?id=" + id + "&code=" + code);
				link = verificationLink.toString();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirm");
			dispatcher.forward(req, resp);
			System.out.println("Please paste this link in your browser to complete registration: \n" + link);
		}
		
		System.out.println(infoMessage);

//		write the message back to the page in client browser\
//		String page = getHTMLString(req.getServletContext().getRealPath("/html/register.jsp"), infoMessage);
//		resp.getWriter().write(page);
//		
		
				
				
	}

//	private String getHTMLString(String realPath, String infoMessage) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
//	
	
}
