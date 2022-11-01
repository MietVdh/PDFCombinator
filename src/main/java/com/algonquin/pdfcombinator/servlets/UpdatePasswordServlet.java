package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algonquin.pdfcombinator.beans.User;
import com.algonquin.pdfcombinator.dao.ApplicationDao;

@WebServlet("/updatepassword")
public class UpdatePasswordServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/updatepassword.html").forward(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApplicationDao dao = new ApplicationDao();
		// Get username for current session
		HttpSession session = request.getSession();		
		String username = (String) session.getAttribute("username");
		User user = dao.getUserByUsername(username);
		
		String password = user.getPassword();
		
		boolean success = false;
		String error = "";
		
		// Check if old password entered matches password in database
		String oldPassword = request.getParameter("old-password");
		if (password.equals(oldPassword)) {
			// Check if new password1 matches new password 2
			String newPassword1 = request.getParameter("new-password1");
			String newPassword2 = request.getParameter("new-password2");
			if (newPassword1.equals(newPassword2)) {
				// TODO update password in database. 
				success = true;
			} else {
				error = "New passwords don't match. Please try again.";
			}
			
		} else {
			error = "Incorrect current password. Please try again.";		
		}
		
		if (success) {
			// Display confirmation message 
			
		} else {
			request.setAttribute("error",  error);
			request.setAttribute("username", username);
			request.setAttribute("password", user.getPassword());
			request.setAttribute("firstName", user.getFirstName());
			request.setAttribute("lastName", user.getLastName());
			request.setAttribute("email", user.getEmail());
			request.getRequestDispatcher("/html/account.jsp").forward(request, response);
		}
		
		
		doGet(request, response);
	}

}
