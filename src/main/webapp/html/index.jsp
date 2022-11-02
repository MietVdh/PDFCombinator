<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>PDFCombinator | Home</title>
	<link rel="stylesheet" href="css/styles.css">
</head>

<body>

<!--  Nav Bar  -->

	<nav>
		<ul>
			<li><a href="/PDFCombinator/home">Home</a></li>
			<li><a href="/PDFCombinator/about">About</a></li>
			
			<% if (session.getAttribute("username") != null) {
				out.println("<li><a href=\"/PDFCombinator/about\">About</a></li> " 
						+ "<li><a href=\"/PDFCombinator/upload\">Upload</a></li> "
						+ "<li><a href=\"/PDFCombinator/logout\">Log Out</a></li>");
			} else {
				out.println("<li><a href=\"/PDFCombinator/register\">Register</a></li> "
						+ "<li><a href=\"/PDFCombinator/login\">Log In</a></li>");
			}
			%>	
		
		</ul>
		
	</nav>
	
<!--  Main part of website -->

	<main>
		<h1>PDFCombinator</h1>
	
	</main>



</body>

</html>