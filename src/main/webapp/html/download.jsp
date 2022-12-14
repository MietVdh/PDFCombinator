<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" isErrorPage="false"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Download</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>

<body>

<!--  Nav Bar  -->

	<nav class="navbar py-5 px-6" role="navigation" aria-label="main navigation">
			<div class="navbar-start">
				<a class="navbar-item" href="/PDFCombinator/home"><i class="fa-sharp fa-solid fa-house"></i>Home</a>
				<a class="navbar-item" href="/PDFCombinator/about"><i class="fa-solid fa-circle-info"></i>About</a>
			</div>
			<div class="navbar-end">
				<a class="navbar-item" href="/PDFCombinator/upload"><i class="fa-solid fa-upload"></i>Upload</a>
				<a class="navbar-item" href="/PDFCombinator/account"><i class="fa-solid fa-address-card"></i>Account</a>
				<a class="navbar-item" href="/PDFCombinator/logout"><i class="fa-solid fa-right-from-bracket"></i>Log Out</a>
			</div>
		
	</nav>
	
<!--  Main part of website -->

	<main class="container pb-6">
		<section class="content py-1">
			<h1 class="title is-1">Download your file</h1>
		</section>
		
		<section class="content">
		
			<% 
			System.out.println(session.getAttribute("filePath")); 
			String filePath = (String) session.getAttribute("filePath");
			%>
			
			<p>	Find your resulting pdf at this link: <%= filePath %> </p>
    		<br>

    		<a class="button is-link is-light" href="<%= filePath %>" download >

				<span class="icon">
      				<i class="fas fa-download" aria-hidden="true"></i>
    			</span>
    			<span>Download PDF</span>
    		</a>
  

		</section>
		
		<section class="content">
			<ul>
				<li><a href="/PDFCombinator/upload">Upload more files</a></li>
				<li><a href="/PDFCombinator/select">Combine uploaded files again</a></li>
			</ul>			
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
</html>