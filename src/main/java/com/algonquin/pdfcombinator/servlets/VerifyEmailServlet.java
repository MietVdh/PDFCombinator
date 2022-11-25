package com.algonquin.pdfcombinator.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algonquin.pdfcombinator.dao.ApplicationDao;

@WebServlet("/verify")
public class VerifyEmailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		
		ApplicationDao dao = new ApplicationDao();
		
		System.out.println("ID: " + id + " \n Code: " + code);
		//added try/catch as appDAO changed
		try {
			if (dao.verifyCode(id, code)) {
				System.out.println("Email verified");
				dao.updateVerifiedEmail(id, code);
			} else {
				System.out.println("We were not able to verify that email address");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/html/verify.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	

	
}
