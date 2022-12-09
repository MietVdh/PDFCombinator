<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Reset Password</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
	<link rel="stylesheet" href="css/styles.css">
</head>

<body>

<!--  Nav Bar -->

	<nav class="navbar py-5 px-6" role="navigation" aria-label="main navigation">
		<div class="navbar-menu mx-6">
			<div class="navbar-start">
				<a class="navbar-item" href="/PDFCombinator/home">Home</a>
				<a class="navbar-item" href="/PDFCombinator/about">About</a>
			</div>
	      	<div class="navbar-end">
	      		<a class="navbar-item" href="/PDFCombinator/register">Register</a>
	      		<a class="navbar-item" href="/PDFCombinator/login">Log In</a>
	      	</div>
	    </div>
		
	</nav>
	
	
	
<!--  Main part of website -->	

	<main class="container pb-6">
	
		<section class="content py-1">
			<h1 class="title is-1">Reset Password</h1>
		</section>
		
		<section class="container">
		
			<form class="box" action="/PDFCombinator/resetpassword" method="post">
			
				<div class="field">
					<label class="label" for="email-recovery">Enter you email address: </label>
					<div class="control">
						<input class="input" type="email" name="email" id="email-recovery">
					</div>
				</div>
				
				<button class="button">Reset password</button>
			
			</form>
		
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

