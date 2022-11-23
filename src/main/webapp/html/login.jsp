<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Log In</title>
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
	      		<a class="navbar-item" href="/PDFCombinator/register">Register</a>
	      		<a class="navbar-item" href="/PDFCombinator/login">Log In</a>
	      	</div>
	    </div>
		
	</nav>
	
<!--  Main part of website -->

	<main class="container pb-6">
		<section class="content py-4">
			<h1 class="title is-1">PDFCombinator - Log In</h1>
		</section>
		
		<section class="message content">
			<% if (request.getAttribute("error") != null) { %>
			<%= request.getAttribute("error") %>
			<% } %>	
		</section>
		
		<section class="container pb-6">
		
			<form class="box" action="/PDFCombinator/login" method="post">
			
				<div class="field">
					<label class="label" for="username">Username </label>
					<div class="control">
						<input class="input" type="text" id="username" name="username" required placeholder="username"/>
					</div>
				</div>
				
				<div class="field">
					<label class="label" for="password">Password </label>
					<div class="control">
						<input class="input" type="password" id="password" name="password" required placeholder="password"/>
					</div>
				</div>
				
				<button class="button">Log In</button>
				
			</form>	
		</section>
		
		<section class="content">
			<a href="/PDFCombinator/resetpassword">Forgot password?</a>		
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