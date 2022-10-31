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



@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.getRequestDispatcher("/html/login.html").forward(request, response);
		}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get values entered into form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Search for user in database 
		ApplicationDao dao = new ApplicationDao();
		if (!dao.validateUser(username, password)) {
			//error
		} else {
			// log in
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			
			request.getRequestDispatcher("/html/account.html").forward(request, response);
		}
		
		
		doGet(request, response);
	}

}
