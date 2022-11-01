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


@WebServlet("/account")
public class AccountServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApplicationDao dao = new ApplicationDao();
		
		// Get username for current session
		HttpSession session = request.getSession();		
		String username = (String) session.getAttribute("username");
		User user = dao.getUserByUsername(username);
		request.setAttribute("username", username);
		request.setAttribute("password", user.getPassword());
		request.setAttribute("firstName", user.getFirstName());
		request.setAttribute("lastName", user.getLastName());
		request.setAttribute("email", user.getEmail());
		request.getRequestDispatcher("/html/account.jsp").forward(request, response);
		
		// Get user info from database
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApplicationDao dao = new ApplicationDao();
		// Get username for current session
		HttpSession session = request.getSession();		
		String username = (String) session.getAttribute("username");
		User user = dao.getUserByUsername(username);
		
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		
		// Get info from form
		String newUsername = request.getParameter("account-username");
		String newFirstName = request.getParameter("account-first-name");
		String newLastName = request.getParameter("account-last-name");
		String newEmail = request.getParameter("account-email");

		// Check which fields have been updated
		if (newUsername.equals("") || newUsername == null) {
			// nothing happens to username
		} else if (!newUsername.equals(username)) {
			// check new username is available
			
			if (dao.updateUsername(username, newUsername)) {
				// successfully updated username; 
				// TODO display message
			} else {
				// TODO display error message
			}
			
		}
		
		if (!newFirstName.equals(firstName) && !newFirstName.equals("") && newFirstName != null) {
			// TODO update first name
		}
		
		if (!newLastName.equals(lastName) && !newLastName.equals("") && newLastName != null) {
			// TODO update last name
		}
		
		if (!newEmail.equals(email) && !newEmail.equals("") && newEmail != null) {
			// TODO update email
			// TODO send verification email!
		}
		
		
		doGet(request, response);
	}

}
