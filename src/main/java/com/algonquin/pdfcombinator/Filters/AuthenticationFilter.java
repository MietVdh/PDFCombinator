package com.algonquin.pdfcombinator.Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)arg0;
		if (request.getRequestURI().startsWith("/PDFCombinator/account") || 
				request.getRequestURI().startsWith("/PDFCombinator/upload")) {
			HttpSession session = request.getSession();
			if (session.getAttribute("username") == null ) {
				request.getRequestDispatcher("/html/login.jsp").forward(request, arg1);
			} 
			
		}
		
		arg2.doFilter(request, arg1);

		
	}

}
