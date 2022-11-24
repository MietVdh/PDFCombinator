<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Registration Confirmed</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
	<link rel="stylesheet" href="css/styles.css">
</head>

<body>

<!--  Nav Bar  -->

	<nav class="navbar py-5 px-6" role="navigation" aria-label="main navigation">
		<div class="navbar-menu mx-6">
			<div class="navbar-start">
				<a class="navbar-item" href="/PDFCombinator/home">Home</a>
				<a class="navbar-item" href="/PDFCombinator/about">About</a>
			</div>
			<div class="navbar-end">
			<% if (session.getAttribute("username") == null) {
				out.println(
						"<a class=\"navbar-item\" href=\"/PDFCombinator/register\">Register</a> "
						+ "<a class=\"navbar-item\" href=\"/PDFCombinator/login\">Log In</a>");
				
			} else {
				out.println(
						"<a class=\"navbar-item\" href=\"/PDFCombinator/upload\">Upload</a> " 
						+ "<a class=\"navbar-item\" href=\"/PDFCombinator/account\">Account</a>"
						+ "<a class=\"navbar-item\" href=\"/PDFCombinator/logout\">Log Out</a>");
			}
			%>
	
	      	</div>
	    </div>
		
	</nav>
	
<!--  Main part of website -->

	<main class="container pb-6">
	
		<section class="content py-1">
			<h1 class="title is-1">Welcome to PDFCombinator!</h1>
		</section>
		
		<section class="content">
		
			<% 
			String message= "";
			if (request.getAttribute("message") != null) {
				message = (String) request.getAttribute("message");
			}
			
			%>
			
			<p><%= message %> </p>

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