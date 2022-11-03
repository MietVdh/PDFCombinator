package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			System.out.println("Session was already null");
		} else {
			session.invalidate();
			System.out.println("Session has been invalidated");
		}
		
		while (request.getAttributeNames().hasMoreElements()) {
			String attribute = request.getAttributeNames().nextElement();
			request.removeAttribute(attribute);
		}
		
		request.getRequestDispatcher("/html/index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
