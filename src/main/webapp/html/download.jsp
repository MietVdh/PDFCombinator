<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>PDFCombinator | Select</title>
	<link rel="stylesheet" href="css/styles.css">
</head>


<body>

<!--  Nav Bar  -->

	<nav>
		<ul>
			<li><a href="/PDFCombinator/home">Home</a></li>
			<li><a href="/PDFCombinator/register">Register</a></li>
			<li><a href="/PDFCombinator/about">About</a></li>
			<li><a href="/PDFCombinator/upload">Upload</a></li>
		</ul>
		
	</nav>
	
<!--  Main part of website -->

	<main>
		<section>
		
		<% System.out.println(session.getAttribute("resultFile")); %>
		<a href="<%= session.getAttribute("resultFile") %>" download>Download PDF</a>

		</section>
	
	</main>


</body>
</html>