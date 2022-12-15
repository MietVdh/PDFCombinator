<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.pdfbox.pdmodel.PDDocument"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" isErrorPage="false"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Select</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>

<body>

<!--  Nav Bar  -->

	<nav class="navbar py-5 px-6" role="navigation" aria-label="main navigation">
		<div class="navbar-brand">
			<a class="navbar-item" href="/PDFCombinator/home"><i class="fa-sharp fa-solid fa-house"></i>  Home</a>
			<a class="navbar-item" href="/PDFCombinator/about"><i class="fa-solid fa-circle-info"></i>  About</a>
			
			<a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false" data-target="navbarMain">
		      <span aria-hidden="true"></span>
		      <span aria-hidden="true"></span>
		      <span aria-hidden="true"></span>
		    </a>
		</div>
		<div id="navbarMain" class="navbar-menu">   	
			<div class="navbar-end">
				<a class="navbar-item" href="/PDFCombinator/upload"><i class="fa-solid fa-upload"></i>  Upload</a>
				<a class="navbar-item" href="/PDFCombinator/account"><i class="fa-solid fa-address-card"></i>  Account</a>
				<a class="navbar-item" href="/PDFCombinator/logout"><i class="fa-solid fa-right-from-bracket"></i>  Log Out</a>
			</div>
		</div>
	</nav>
	
<!--  Main part of website -->

	<main class="container pb-6">
	
		<section class="content py-1">
			<h1 class="title is-1">PDFCombinator - Select pages</h1>
		</section>
		
		<section class="container">
		
			<form class="box" action="/PDFCombinator/select" method="post">
			<% 
			List<PDDocument> pdfs = (ArrayList)session.getAttribute("pdfs");
			
			for (int i = 1; i <= (pdfs.size() * 2) && i < 7; i++) {
			%>
			<fieldset class="box">
				<div class="field">
					<label class="label" for="select-file-<%=i%>">File:</label>
					<div class="control">
						<div class="select">
							<select name="select-file-<%=i%>" id="select-file-<%=i%>">
								<option value="" selected="selected"> </option>
								<%
									for (PDDocument pdfDoc : pdfs) {
										String id = pdfDoc.getDocumentId().toString();
										String title = pdfDoc.getDocumentInformation().getTitle();
										int size = pdfDoc.getNumberOfPages();
								%>
										<option value="<%= id %>"> <%= title %> (<%=size %> pages)</option>
								<%
									}
								 %>
							</select>			
						</div>
					</div>
				</div>
				
				<div class="field">
					<label class="label" for="select-page-<%=i%>">Pages to select: </label>
					<div class="control">
						<input class="input" type="text" name="select-page-<%=i%>" id="select-page-<%=i%>" placeholder="e.g. 1-4, 5, 7-9" />
					</div>
				</div>
				
				</fieldset>
				
				<% } %>
				
				<div class="field">
					<label class="label" for="file-name">Enter a name for the new PDF:</label>
					<div class="control">
						<input class="input" type="text" id="file-name" name="file-name" placeholder="e.g. result"/>
					</div>
				</div>
				
				

			<% request.setAttribute("pdfs", pdfs); %>
			
			<button class="button">Create PDF</button>
		
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

<script>

document.addEventListener('DOMContentLoaded', () => {

	  // Get all "navbar-burger" elements
	  const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

	  // Add a click event on each of them
	  $navbarBurgers.forEach( el => {
	    el.addEventListener('click', () => {

	      // Get the target from the "data-target" attribute
	      const target = el.dataset.target;
	      const $target = document.getElementById(target);

	      // Toggle the "is-active" class on both the "navbar-burger" and the "navbar-menu"
	      el.classList.toggle('is-active');
	      $target.classList.toggle('is-active');

	    });
	  });

	});
</script>

</html>