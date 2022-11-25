package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algonquin.pdfcombinator.dao.ApplicationDao;

@WebServlet("/setnewpassword")
public class NewPasswordServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/update.html").forward(request, response);
	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword = request.getParameter("new-password-1");
		String newPasswordConfirmation = request.getParameter("new-password-2");
		
		String username = (String) request.getAttribute("username");
		
		String message = "";
		
		ApplicationDao dao = new ApplicationDao();
		
		if (newPassword.equals(newPasswordConfirmation)) {
			//added try/catch as appDAO changed
			try {
				if (dao.updatePassword(username, newPassword)) {
					message = "Password updated successfully";
					request.setAttribute("message", message);
					request.getRequestDispatcher("/login").forward(request, response);
					return;
				} else {
					message = "Sorry, something went wrong. Please try again";
					request.setAttribute("message", message);
					request.getRequestDispatcher("/").forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			message = "Passwords don't match.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
	}
}
