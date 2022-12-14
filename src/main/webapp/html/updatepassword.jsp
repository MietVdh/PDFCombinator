<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" isErrorPage="false"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Set New Password</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>

<body>

<!--  Nav Bar -->

	<nav class="navbar py-5 px-6" role="navigation" aria-label="main navigation">
		<div class="navbar-menu mx-6">
			<div class="navbar-start">
				<a class="navbar-item" href="/PDFCombinator/home"><i class="fa-sharp fa-solid fa-house"></i>Home</a>
				<a class="navbar-item" href="/PDFCombinator/about"><i class="fa-solid fa-circle-info"></i>About</a>
			</div>
	      	<div class="navbar-end">
	      		<a class="navbar-item" href="/PDFCombinator/register"><i class="fa-solid fa-address-card"></i>Register</a>
	      		<a class="navbar-item" href="/PDFCombinator/login"><i class="fa-solid fa-right-to-bracket"></i>Log In</a>
	      	</div>
	    </div>
		
	</nav>
	
	
	
<!--  Main part of website -->	

	<main class="container pb-6">
	
		<section class="content py-1">
			<h1 class="title is-1">Set New Password</h1>
		</section>
		
		<section class="content message">
			<% 
			String message = "";
			if (request.getAttribute("message") != null) {
				message = (String) request.getAttribute("message");
			}
			
			String username = (String) request.getAttribute("username");
				
			%>
			
			<p><%= message %></p>
			
		</section>
		
		<section class="container">
		
			<form class="box" action="/PDFCombinator/recover" method="post">
			
				<div class="field">
					<label class="label" for="new-password-1">Enter a new password: </label>
					<div class="control">
						<input class="input" type="password" name="new-password-1" id="new-password-1">
					</div>
				</div>
				
				<div class="field">
					<label class="label" for="new-password-2">Confirm new password: </label>
					<div class="control">
						<input class="input" type="password" name="new-password-2" id="new-password-2">
					</div>
				</div>
				
				<input type="text" id="username" name="username" value="<%= username %>" hidden=true>
				
				<button class="button">Save password</button>
			
			</form>
		
		</section>
		
	
	</main>

	<footer class="footer">
	  <div class="content has-text-centered">
	    <p>
	      <strong>PDFCombinator</strong> by <a class="link" href="https://github.com/v3g4n/">Miet Vanderheyden</a>, <a class="link" href="https://github.com/emchyeah">Meigan Cheah</a>, 
	      and <a class="link" href="https://github.com/Serapik">Serap Keskinler</a>. 2022
	    </p>
	  </div>
	</footer>


</body>

