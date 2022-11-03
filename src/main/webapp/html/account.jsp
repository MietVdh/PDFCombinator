<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.algonquin.pdfcombinator.beans.User" %>
<%@page import="com.algonquin.pdfcombinator.dao.ApplicationDao" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Account</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
	<link rel="stylesheet" href="css/styles.css">
</head>

<body>

<!--  Nav Bar  -->

	<nav class="navbar p-4" role="navigation" aria-label="main navigation">
		<div class="navbar-brand">
			<a class="navbar-item" href="/PDFCombinator/home">Home</a>
			<a class="navbar-item" href="/PDFCombinator/about">About</a>
		</div>
		<div id="navbarBasicExample" class="navbar-menu is-active">
			<div class="navbar-end">
				<a class="navbar-item" href="/PDFCombinator/upload">Upload</a>
				<a class="navbar-item" href="/PDFCombinator/account">Account</a>
				<a class="navbar-item" href="/PDFCombinator/logout">Log Out</a>
			</div>
	    </div>
		
	</nav>

<!--  Main part of website -->

	<main class="container pb-6">
	
		<section class="content py-4">
			<h1 class="title" >PDFCombinator - Account</h1>
		</section>
		
		
		<% 
		String username = (String) session.getAttribute("username");
		
		ApplicationDao dao = new ApplicationDao();
		
		User user = dao.getUserByUsername(username);
		
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String message = (String) request.getAttribute("message");
		if (message == null) {
			message = "";
		}
		
		%>
		<section class="message content">
			<p><%= message %></p>
		</section>
		
		<section class="container">
			<form class="box" action="/PDFCombinator/account" method="post">
			
				<div class="field">
					<label class="label" for="account-username">Username</label>
					<div class="control">
						<input class="input" type="text" name="username" id="account-username" value="<%=username%>">
					</div>
				</div>
				
				<div class="field">
					<label class="label" for="account-first-name">First Name</label>
					<div class="control">
						<input class="input" type="text" name="firstname" id="account-first-name" value="<%=firstName%>">
					</div>
				</div>
				
				<div class="field">
					<label class="label" for="account-last-name">Last Name</label>
					<div class="control">
						<input class="input" type="text" name="lastname" id="account-last-name" value="<%=lastName%>">
					</div>
				</div>
				
				<div class="field">
					<label class="label" for="account-email">Email</label>
					<div class="control">
						<input class="input" type="email" name="email" id="account-email" value="<%=email %>">
					</div>
				</div>
				
				<button class="button">Save changes</button>
			
			</form>
		
			<form class="box" action="/PDFCombinator/updatepassword" method="post">
			
				<div class="field">
					<label class="label" for="old-password">Current password</label>
					<div class="control">
						<input class="input" type="password" name="old-password" id="old-password" required>
					</div>
				</div>
				
				
				
				<div class="field">
					<label class="label" for="new-password1">New password</label>
					<div class="control">
						<input class="input" type="password" name="new-password1" id="new-password1" required>
					</div>		
				</div>
				
				<div class="field">
					<label class="label" for="new-password2">Re-enter new password</label>
					<div class="control">
						<input class="input" type="password" name="new-password2" id="new-password2" required>
					</div>		
				</div>
			
				<button class="button">Update password</button>
			
			</form>
			
			<form class="box" action="/PDFCombinator/delete" method="post">
			
				<article class="message is-danger">
				  <div class="message-header">
				    <p>Delete account</p>
				  </div>
				  <div class="message-body">
				  	<p>Deleting your account will remove all your information from our database. This action cannot be undone!</p>
				  </div>
				</article>
				<div class="field">
					<label class="label" for="delete-password">Please enter your current password to confirm:</label>
					<div class="control">
						<input class="input" type="password" name="password" id="delete-password">
					</div>
				</div>
				<button class="button is-danger">Delete Account</button>
			</form>
			
		
		</section>
	
	</main>

	<footer class="footer">
	  <div class="content has-text-centered">
	    <p>
	      <strong>PDFCombinator</strong> by <a href="https://github.com/v3g4n/">Miet Vanderheyden</a>, <a href="https://github.com/emchyeah">Meigan Cheah</a>, 
	      and <a href="https://github.com/Serapik">Serap Keskinler</a>. 2022
	    </p>
	  </div>
	</footer>


</body>

</html>