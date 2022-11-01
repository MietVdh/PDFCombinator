<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>PDFCombinator | Log In</title>
	<link rel="stylesheet" href="css/styles.css">
</head>

<body>

<!--  Nav Bar  -->

	<nav>
		<ul>
			<li><a href="/PDFCombinator/home">Home</a></li>
			<li><a href="/PDFCombinator/register">Register</a></li>
			<li><a href="/PDFCombinator/about">About</a></li>
		</ul>
		
	</nav>
	
<!--  Main part of website -->

	<main>
		<h1>PDFCombinator - Log In</h1>
		
		<section class="message">
			<% if (request.getAttribute("error") != null) { %>
			<%= request.getAttribute("error") %>
			<% } %>	
		</section>
		
		<section>
			<form action="/PDFCombinator/login" method="post">
				<label for="username">Username: </label>
				<input type="text" id="username" name="username" required/>
				<br>
				<label for="password">Password: </label>
				<input type="password" id="password" name="password" required/>
				<br>
				<button>Log In</button>
			</form>	
		</section>
		
		<section>
			<a href="/PDFCombinator/resetpassword">Forgot password?</a>		
		</section>
		
		
		
		
	
	</main>



</body>

</html>