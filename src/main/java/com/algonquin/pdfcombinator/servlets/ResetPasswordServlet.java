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

import com.algonquin.pdfcombinator.beans.User;
import com.algonquin.pdfcombinator.dao.ApplicationDao;

@WebServlet("/resetpassword")
public class ResetPasswordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In ResetPasswordServlet doGet");
		request.getRequestDispatcher("/html/passwordreset.jsp").forward(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = "";
		// Get email
		String email = request.getParameter("email");
		
		System.out.println("Email: " + email);
		
		// Generate random code
		String code = UUID.randomUUID().toString();
		
		ApplicationDao dao = new ApplicationDao();
		
		User user = dao.getUserByEmail(email);
		
		if (user == null) {
			message = "That email address is not associated with an account";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/").forward(request, response);
			return;
		} else if (!dao.addCode(code, email)) {
			message = "Sorry, something went wrong. Please try again.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
		String id = user.getID().toString();
		String username = user.getUserName();
		System.out.println("Username in ResetPassword: " + username);
		
		String link = "";
		
		try {
			URI verificationLink = new URI("http://localhost:8080/PDFCombinator/recover?id=" + id + "&code=" + code);
			link = verificationLink.toString();
			message = "Please check your email for the link to reset your password";
		} catch (URISyntaxException e) {
			e.printStackTrace();
			message = "Sorry, something went wrong. Please try again.";
		}
		
		request.setAttribute("message", message);
		request.setAttribute("username", username);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirm");
		dispatcher.forward(request, response);
		System.out.println("Please paste this link in your browser to reset your password: \n" + link);
		
		
	}

}
