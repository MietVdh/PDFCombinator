package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algonquin.pdfcombinator.beans.User;
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
			User user = dao.getUserById(id);
			request.setAttribute("username", user.getUserName());
		} else {
			System.out.println("We were not able to verify that code. Please try again");
		}
		
		
		request.getRequestDispatcher("/html/passwordreset.html").forward(request, response);
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
