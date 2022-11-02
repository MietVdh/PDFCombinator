<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.algonquin.pdfcombinator.beans.User" %>
<%@page import="com.algonquin.pdfcombinator.dao.ApplicationDao" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>PDFCombinator | Account</title>
	<link rel="stylesheet" href="css/styles.css">
</head>

<body>

<!--  Nav Bar  -->

	<nav>
		<ul>
			<li><a href="/PDFCombinator/home">Home</a></li>
			<li><a href="/PDFCombinator/about">About</a></li>
			<li><a href="/PDFCombinator/upload">Upload</a></li>
			<li><a href="/PDFCombinator/logout">Log Out</a></li>
		</ul>
		
	</nav>
	
<!--  Main part of website -->

	<main>
		<h1>PDFCombinator - Account</h1>
		
		<section>
		
		<% 
		String username = (String) session.getAttribute("username");
		
		ApplicationDao dao = new ApplicationDao();
		
		User user = dao.getUserByUsername(username);
		
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		
		%>
		<section class="message">
			<p><%= request.getAttribute("message") %></p>
		</section>
		
		<form action="/PDFCombinator/account" method="post">
		
			<label for="account-username">Username</label>
			<input type="text" name="username" id="account-username" value="<%=username%>"><br>
			
			<label for="account-first-name">First Name</label>
			<input type="text" name="firstname" id="account-first-name" value="<%=firstName%>"><br>
			
			<label for="account-last-name">Last Name</label>
			<input type="text" name="lastname" id="account-last-name" value="<%=lastName%>"><br>
			
			<label for="account-email">Email</label>
			<input type="email" name="email" id="account-email" value="<%=email %>"><br>
			
			<button>Save changes</button>
		
		</form>
		
		<form action="/PDFCombinator/updatepassword" method="post">
		
			<label for="old-password">Current password</label>
			<input type="password" name="old-password" id="old-password" required><br>
			
			<label for="new-password1">New password</label>
			<input type="password" name="new-password1" id="new-password1" required><br>
			
			<label for="new-password2">Re-enter new password</label>
			<input type="password" name="new-password2" id="new-password2" required><br>
			
			<button>Update password</button>
		
		</form>
		
		
		</section>
	
	</main>



</body>

</html>