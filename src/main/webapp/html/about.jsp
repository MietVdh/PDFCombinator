<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>PDFCombinator | About</title>
	<link href="css/styles.css" rel="stylesheet" >
</head>

<body>

<!--  Nav Bar  -->

	<nav>
		<ul>
			<li><a href="/PDFCombinator/home">Home</a></li>
			<li><a href="/PDFCombinator/about">About</a></li>
			
			<% if (session.getAttribute("username") != null) {
				out.println("<li><a href=\"/PDFCombinator/account\">Account</a></li> " 
						+ "<li><a href=\"/html/upload.jsp\">Upload</a></li> "
						+ "<li><a href=\"/html/logout.jsp\">Log Out</a></li>");
			} else {
				out.println("<li><a href=\"register.jsp\">Register</a></li> "
						+ "<li><a href=\"login.jsp\">Log In</a></li>");
			}
			%>
			
		</ul>
	</nav>
	
	
<!--  Main part of website -->	

	<main>
	
		<h1>About PDFCombinator</h1>
	
	</main>



</body>

