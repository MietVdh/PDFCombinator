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
		
		// Register user in database
		rows = dao.registerUser(user, code);
	
		if (rows==0) {
			infoMessage="Sorry, an error occurred!";
			req.setAttribute("message", infoMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/register.jsp");
			dispatcher.forward(req, resp);
			
		}
		else {
			infoMessage="User registered successfully!";
			
			String id = user.getID().toString();
			
			String link = "";
			
			try {
				URI verificationLink = new URI("http://localhost:8080/PDFCombinator/verify?id=" + id + "&code=" + code);
				link = verificationLink.toString();
				infoMessage = "Your information has been received. Please check your email inbox and click the verification link to complete the registration process.";
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			req.setAttribute("message", infoMessage);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirm");
			dispatcher.forward(req, resp);
			System.out.println("Please paste this link in your browser to complete registration: \n" + link);
		}
		
		System.out.println(infoMessage);
				
	}

}
