package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

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
		
		request.getRequestDispatcher("/html/account.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = "";
		
		ApplicationDao dao = new ApplicationDao();
		// Get username for current session
		HttpSession session = request.getSession();		
		String username = (String) session.getAttribute("username");
		User user = dao.getUserByUsername(username);
		
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		
		// Get info from form
		String newUsername = request.getParameter("username");
		String newFirstName = request.getParameter("firstname");
		String newLastName = request.getParameter("lastname");
		String newEmail = request.getParameter("email");

		// Check which fields have been updated
		if (newUsername.equals("") || newUsername == null) {
			// nothing happens to username
			System.out.println("Username stays the same");
		} else if (!newUsername.equals(username)) {
			// check new username is available
			
			if (dao.updateUsername(username, newUsername)) {
				// successfully updated username; 
				session.setAttribute("username", newUsername);
				username = newUsername;
				System.out.println("Username updated to " + session.getAttribute("username"));
				message += "Successfully updated username \n";

			} else {
				System.out.println("That username is not available");
			}			
		}
		
		if (!newFirstName.equals(firstName) && !newFirstName.equals("") && newFirstName != null) {
			if (dao.updateFirstName(username, newFirstName)) {
				System.out.println("First name updated");
			} else {
				message += "Could not update first name\n";
				System.out.println(message);
			}
		}
		
		if (!newLastName.equals(lastName) && !newLastName.equals("") && newLastName != null) {
			if (dao.updateLastName(username, newLastName)) {
				System.out.println("Last name updated");
			} else {
				message += "Could not update last name\n";
				System.out.println(message);
			}
		}
		
		if (!newEmail.equals(email) && !newEmail.equals("") && newEmail != null) {
			if (dao.updateEmail(username, newEmail)) {
				System.out.println("Email updated. Please click confirmation link to complete");
				
				String code = UUID.randomUUID().toString();
				
				// update code in database
				dao.updateCode(username, code);
				
				String id = user.getID().toString();
				
				String link = "";
				
				try {
					URI verificationLink = new URI("http://localhost:8080/PDFCombinator/verify?id=" + id + "&code=" + code);
					link = verificationLink.toString();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Please paste this link in your browser to complete registration: \n" + link);
				message += "Please click the verification link we sent to you in order to complete updating your email address to " + newEmail + "\n";
				
			} else {
				message += "Could not update email\n";
				System.out.println(message);
			}
			// TODO send verification email!
		}
		
		
		doGet(request, response);
	}

}
