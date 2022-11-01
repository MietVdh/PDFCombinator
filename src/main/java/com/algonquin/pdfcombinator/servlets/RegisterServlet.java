package com.algonquin.pdfcombinator.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algonquin.pdfcombinator.beans.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = getHTMLString(request.getServletContext().getRealPath("/html/register.html"));
		response.getWriter().write(page);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve information entered
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String userName = request.getParameter("uname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password");
		
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

}
