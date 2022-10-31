<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.pdfbox.pdmodel.PDDocument"%>
<%@page import="java.util.List"%>
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
			<li><a href="/PDFCombinator/about">About</a></li>
			<li><a href="/PDFCombinator/upload">Upload</a></li>
			<li><a href="/PDFCombinator/account">Account</a></li>
			<li><a href="/PDFCombinator/logout">Log Out</a></li>
		</ul>
		
	</nav>
	
<!--  Main part of website -->

	<main>
		<h1>PDFCombinator - Select pages</h1>
		
		<form action="/PDFCombinator/select" method="post">
			<% 
			List<PDDocument> pdfs = (ArrayList)request.getAttribute("pdfs");
			// Iterator<PDDocument> iterator = pdfs.iterator();

			// while (iterator.hasNext()) {
			// 	PDDocument pdf = iterator.next();
			
			for (int i = 1; i <= (pdfs.size() * 2); i++) {
			%>
			<label for="select-file-<%=i%>">File:</label>
			<select name="select-file-<%=i%>" id="select-file-<%=i%>">
			<option value="" selected="selected"> </option>
			<%
				for (PDDocument pdfDoc : pdfs) {
					String id = pdfDoc.getDocumentId().toString();
					String title = pdfDoc.getDocumentInformation().getTitle();
			%>
					<option value="<%= id %>"> <%= title %></option>
			<%
				}
			 %>
			</select>
			<label for="select-page-<%=i%>">Pages to select: </label>
			<input type="text" name="select-page-<%=i%>" id="select-page-<%=i%>" />
			<br>
			
			<% } %>
			
			
			<label for="file-name">Enter a name for the new PDF:</label>
			<input type="text" id="file-name" name="file-name"/>
			
			<% request.setAttribute("pdfs", pdfs); %>
			
			<button>Create PDF</button>
		
		</form>
		
	
	</main>



</body>

</html>