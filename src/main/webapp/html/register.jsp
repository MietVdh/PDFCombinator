<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>PDFCombinator | Register</title>
	<link rel="stylesheet" href="css/styles.css">
</head>
	
	
<body>

<!--  Nav Bar  -->

	<nav>
		<ul>
			<li><a href="/PDFCombinator/home">Home</a></li>
			<li><a href="/PDFCombinator/register">Register</a></li>
			<li><a href="/PDFCombinator/about">About</a></li>
			<li><a href="/PDFCombinator/login">Log In</a></li>
		</ul>
	</nav>
	
	
<!--  Main part of website -->

	<main>
		<h1>Register</h1>
		
		<section class="message">
			<% if (request.getAttribute("message") != null) { %>
			<%= request.getAttribute("message") %>
			<% } %>
		</section>
		
<!--  Registration form  -->

		<form action="/PDFCombinator/register" method="post">
			<label for="fname">First Name</label>
			<input type="text" name="fname" required/>
			<br>
			
			<label for="lname">Last Name</label>
			<input type="text" name="lname" required/>
			<br>
			
			<label for="uname">Username</label>
			<input type="text" name="uname" required/>
			<br>
			
			<label for="email">Email Address</label>
			<input type="email" name="email" required/>
			<br>
		
			<label for="password">Password</label>
			<input type="password" name="password" required/>
			<br>
			
			<label for="password2">Re-enter Password</label>
			<input type="password" name="password2" required/>
			<br>
			
			<button type="submit">Register</button>
		
		</form>
		
		
		
	</main>



</body>


</html>