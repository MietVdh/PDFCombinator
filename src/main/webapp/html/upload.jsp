<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.pdfbox.pdmodel.PDDocument" errorPage="error.jsp" isErrorPage="false"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Upload</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
	<link rel="stylesheet" href="css/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>

<body>

<!--  Nav Bar  -->

	<nav class="navbar py-5 px-6" role="navigation" aria-label="main navigation">
		<div class="navbar-menu mx-6">
			<div class="navbar-start">
				<a class="navbar-item" href="/PDFCombinator/home"><i class="fa-sharp fa-solid fa-house"></i>Home</a>
				<a class="navbar-item" href="/PDFCombinator/about"><i class="fa-solid fa-circle-info"></i>About</a>
			</div>			
			<div class="navbar-end">
				<a class="navbar-item" href="/PDFCombinator/upload"><i class="fa-solid fa-upload"></i>Upload</a>
				<a class="navbar-item" href="/PDFCombinator/account"><i class="fa-solid fa-address-card"></i>Account</a>
				<a class="navbar-item" href="/PDFCombinator/logout"><i class="fa-solid fa-right-from-bracket"></i>Log Out</a>
			</div>
	    </div>
		
	</nav>
	
<!--  Main part of website -->

	<main class="container pb-6">
	
	<section class="content py-1">
		<h1 class="title is-1">Upload PDFs</h1>
	</section>
	
	<section class="content message">
			
			<% String message;
			if (request.getAttribute("message") == null) {
				message = " ";
			} else {
				message = (String) request.getAttribute("message");
			}
			%>
			
			<p><%=message %></p>	

	</section>
	
	<section class="container pb-4">
	
		<div class="content">
			
			<% 
			
			String divTitle = "";
			ArrayList<PDDocument> uploadedPDFs;
			if (session.getAttribute("pdfs") == null) {
				uploadedPDFs = null;
				divTitle = "No PDFs uploaded yet";
			} else {
				uploadedPDFs = new ArrayList<PDDocument>((ArrayList)session.getAttribute("pdfs")); 
				divTitle = "PDFs already uploaded and available:";
			%>
			<h3 class="title"><%= divTitle %></h3>
			<ul>
			<%
				for (PDDocument pdf : uploadedPDFs) {
					String pdfTitle = pdf.getDocumentInformation().getTitle();
			%>
				<li>
				<%= pdfTitle %>
				</li>
			<%
				}
			} 
			
			%>
			</ul>
			
		</div>
			
			

	</section>
		
	<section class="container">
	
		<form class="box" action="/PDFCombinator/upload" method="post" enctype="multipart/form-data">
		
			<fieldset class="box">
				<div class="file has-name" id="file-1-input">
				  <label class="file-label">
				    <input class="file-input" type="file" name="file1" accept=".pdf">
				    <span class="file-cta">
				      <span class="file-icon">
				        <i class="fas fa-upload"></i>
				      </span>
				      <span class="file-label">
				        Choose a file...
				      </span>
				    </span>
				    <span class="file-name">
      					No file selected
   					</span>
				  </label>
				</div>
						
				<div class="field">
					<label class="label" for="file1name">Optional: enter a name for this file</label>
					<div class="control">
						<input class="input" type="text" name="file1name" placeholder="e.g. contract"/>
					</div>
				</div>
	
			</fieldset>
		
			<br>
			<fieldset class="box">
				<div class="file has-name" id="file-2-input">
				  <label class="file-label">
				    <input class="file-input" type="file" name="file2" accept=".pdf">
				    <span class="file-cta">
				      <span class="file-icon">
				        <i class="fas fa-upload"></i>
				      </span>
				      <span class="file-label">
				        Choose a file...
				      </span>
				    </span>
				    <span class="file-name">
      					No file selected
   					</span>
				  </label>
				</div>

			
				<div class="field">
					<label class="label" for="file2name">Optional: enter a name for this file</label>
					<div class="control">
						<input class="input" type="text" name="file2name" placeholder="e.g. signed_page"/>
					</div>
				</div>
	
			</fieldset>
			<br>
			<fieldset class="box">
				<div class="file has-name" id="file-3-input">
				  <label class="file-label">
				    <input class="file-input" type="file" name="file3" accept=".pdf">
				    <span class="file-cta">
				      <span class="file-icon">
				        <i class="fas fa-upload"></i>
				      </span>
				      <span class="file-label">
				        Choose a file...
				      </span>
				    </span>
				    <span class="file-name">
      					No file selected
   					</span>
				  </label>
				</div>
			
				<div class="field">
					<label class="label" for="file3name">Optional: enter a name for this file</label>
					<div class="control">
						<input class="input" type="text" name="file3name" placeholder="e.g. financial_report"/>
					</div>
				</div>
	
			</fieldset>
						
			<button class="button" type="submit">Upload</button>
		
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
  const file1Input = document.querySelector('#file-1-input input[type=file]');
  const file2Input = document.querySelector('#file-2-input input[type=file]');
  const file3Input = document.querySelector('#file-3-input input[type=file]');
  
  file1Input.onchange = () => {
	    if (file1Input.files.length > 0) {
	      const file1Name = document.querySelector('#file-1-input .file-name');
	      file1Name.textContent = file1Input.files[0].name;
	    }
	  };
	  
  file2Input.onchange = () => {
		    if (file2Input.files.length > 0) {
		      const file2Name = document.querySelector('#file-2-input .file-name');
		      file2Name.textContent = file2Input.files[0].name;
		    }
		  };

  file3Input.onchange = () => {
	    if (file3Input.files.length > 0) {
	      const file3Name = document.querySelector('#file-3-input .file-name');
	      file3Name.textContent = file3Input.files[0].name;
	    }
	  };
</script>

</html>