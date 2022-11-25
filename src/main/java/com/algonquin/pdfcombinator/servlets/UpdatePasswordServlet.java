package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;
import java.sql.SQLException;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/html/account.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Check if user is logged in
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			// User is not logged in
			request.getRequestDispatcher("/html/login.jsp").forward(request, response);
		}

		// Get username for current session
		String username = (String) session.getAttribute("username");

		ApplicationDao dao = new ApplicationDao();

		User user;
		//added try catch as appDAO changed
		try {
			user = dao.getUserByUsername(username);

			String password = user.getPassword();

			boolean success = false;
			String message = "";

			// Check if old password entered matches password in database
			String oldPassword = request.getParameter("old-password");
			if (password.equals(oldPassword)) {
				// Check if new password1 matches new password 2
				String newPassword1 = request.getParameter("new-password1");
				String newPassword2 = request.getParameter("new-password2");
				if (newPassword1.equals(newPassword2)) {
					if (dao.updatePassword(username, newPassword1)) {
						success = true;
					}
				} else {
					message = "New passwords don't match. Please try again.";
				}

			} else {
				message = "Incorrect current password. Please try again.";
			}

			if (success) {
				// Display confirmation message
				message = "Password updated successfully";
				System.out.println(message);

			} else {
				message = "Sorry, we were not able to update your password";

			}

			request.setAttribute("message", message);
			request.getRequestDispatcher("/html/account.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
