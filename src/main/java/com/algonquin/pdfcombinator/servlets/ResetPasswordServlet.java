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
		request.getRequestDispatcher("/html/passwordreset.html").forward(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = "";
		// Get email
		String email = request.getParameter("email-recovery");
		
		// Generate random code
		String code = UUID.randomUUID().toString();
		
		ApplicationDao dao = new ApplicationDao();
		
		User user = dao.getUserByEmail(email);
		
		if (!dao.addCode(code, email)) {
			message = "Sorry, something went wrong. Please try again.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
		String id = user.getID().toString();
		
		String link = "";
		
		try {
			URI verificationLink = new URI("http://localhost:8080/PDFCombinator/recover?id=" + id + "&code=" + code);
			link = verificationLink.toString();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirm");
		dispatcher.forward(request, response);
		System.out.println("Please paste this link in your browser to reset your password: \n" + link);
		
		
		
		
	}

}
