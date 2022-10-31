package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.algonquin.pdfcombinator.dao.ApplicationDao;


@WebServlet("/account")
public class AccountServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get username for current session
		HttpSession session = request.getSession();		
		String username = (String) session.getAttribute("username");
		request.getRequestDispatcher("/html/account.html").forward(request, response);
		
		ApplicationDao dao = new ApplicationDao();
		
		// Get user info from database
	}

}
