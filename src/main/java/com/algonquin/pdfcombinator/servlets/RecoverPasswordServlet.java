package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algonquin.pdfcombinator.dao.ApplicationDao;

@WebServlet("/recover")
public class RecoverPasswordServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		
		
		ApplicationDao dao = new ApplicationDao();
		
		System.out.println("ID: " + id + " \n Code: " + code);
		if (dao.verifyCode(id, code)) {
			System.out.println("Code verified");
			
		} else {
			System.out.println("We were not able to verify that code. Please try again");
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}
		
		String username = dao.getUserById(id).getUserName();		
		request.setAttribute("username", username);
		System.out.println("username in RecoverPassword doGet: " + username);
		request.getRequestDispatcher("/html/updatepassword.jsp").forward(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Message
		String message = "";
		
		// get passwords from form
		String password1 = request.getParameter("new-password-1");
		String password2 = request.getParameter("new-password-2");
		
		// get username
		String username = request.getParameter("username");
		System.out.println("Username in recoverPassword doPost: " + username);
		
		// Check that both passwords match
		if (!password1.equals(password2)) {
			message = "The passwords you entered don't match. Please try again.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/html/updatepassword.jsp").forward(request, response);
			return;
		} else {
			ApplicationDao dao = new ApplicationDao();
			if (dao.updatePassword(username, password1)) {
				message = "Your password has been updated";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/").forward(request, response);
			} else {
				message = "Sorry, something went wrong. Please try again.";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/html/updatepassword.jsp").forward(request, response);
			}
		}
		
	}

}
