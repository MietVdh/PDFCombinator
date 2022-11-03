package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algonquin.pdfcombinator.dao.ApplicationDao;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/account.jsp").forward(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = "";
		
		// Get currently logged in user
		String username = (String) request.getSession().getAttribute("username");
		
		// Get password from form 
		String password = request.getParameter("password");
		
		// Check that password is correct
		ApplicationDao dao = new ApplicationDao();
		if (dao.validateUser(username, password)) {
			// Delete account
			if (dao.deleteUser(username)) {
				message = "Your account has been successfully deleted.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/html/delete.jsp").forward(request, response);
				
				// Invaidate session
				request.getSession().invalidate();
				return;
			} else {
				message = "We're sorry, something went wrong. Please try again.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/html/account.jsp").forward(request, response);
				return;
			}
		}
		
		
		
		// Forward user to webpage confirming account deletion
		
		
		doGet(request, response);
	}

	
}
