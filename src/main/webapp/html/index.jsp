<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>PDFCombinator | Home</title>
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
			<h1 class="title is-1" >PDFCombinator</h1>
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
		
		<section class="content">
			<% 
			String content = "";
			if (session.getAttribute("username") == null) {
				content = "Welcome to PDFCombinator! To start combining PDFs, please <a href=\"/PDFCombinator/login\">log in</a> or <a href=\"/PDFCombinator/register\">register</a>.";
			} else {
				content = "Welcome to PDFCombinator! To start combining PDFs, <a href=\"/PDFCombinator/upload\">upload</a> some files. Or, you can <a href=\"/PDFCombinator/account\">manage your account</a>.";
			}
			%>
			<p>
			<%= content %>
			</p>
		</section>
		
		<section class="content lorem">
			<h2 class="title">Filler text</h2>
			<p>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nibh nisl condimentum id venenatis a condimentum vitae sapien. Urna nec tincidunt praesent semper feugiat nibh sed. Elementum pulvinar etiam non quam. Quis vel eros donec ac. Libero nunc consequat interdum varius sit amet mattis vulputate. Convallis tellus id interdum velit laoreet. Odio morbi quis commodo odio aenean. Et netus et malesuada fames ac turpis. Quisque sagittis purus sit amet volutpat consequat mauris nunc congue. Cras ornare arcu dui vivamus arcu felis bibendum. Pulvinar neque laoreet suspendisse interdum consectetur. Volutpat commodo sed egestas egestas fringilla phasellus faucibus scelerisque eleifend. Mauris a diam maecenas sed enim ut. Adipiscing at in tellus integer feugiat. Diam maecenas ultricies mi eget mauris pharetra. Felis imperdiet proin fermentum leo vel orci porta non pulvinar. At urna condimentum mattis pellentesque id nibh. Sit amet purus gravida quis blandit turpis cursus in hac. Mattis aliquam faucibus purus in massa tempor.
			</p>
			<p>
			Enim sed faucibus turpis in eu mi. Sed elementum tempus egestas sed sed. Aliquam eleifend mi in nulla posuere sollicitudin aliquam ultrices. Id diam maecenas ultricies mi eget mauris pharetra et. Enim lobortis scelerisque fermentum dui faucibus. Accumsan sit amet nulla facilisi. Molestie at elementum eu facilisis sed. Rhoncus urna neque viverra justo nec ultrices. Integer feugiat scelerisque varius morbi enim nunc faucibus a. Volutpat blandit aliquam etiam erat. Velit laoreet id donec ultrices tincidunt arcu non. Faucibus in ornare quam viverra orci sagittis eu. Suspendisse faucibus interdum posuere lorem ipsum dolor sit amet. Quis vel eros donec ac odio tempor orci. Quis lectus nulla at volutpat diam ut. Lorem donec massa sapien faucibus. Mauris cursus mattis molestie a. Viverra mauris in aliquam sem fringilla ut morbi tincidunt augue. Dignissim diam quis enim lobortis scelerisque fermentum dui. Morbi quis commodo odio aenean.
			</p>
			<p>
			Lacus vel facilisis volutpat est velit. Eget aliquet nibh praesent tristique magna. Nisl rhoncus mattis rhoncus urna. Pellentesque dignissim enim sit amet venenatis urna cursus. Venenatis lectus magna fringilla urna porttitor rhoncus. Amet cursus sit amet dictum sit. Duis ultricies lacus sed turpis tincidunt id. Pellentesque nec nam aliquam sem et tortor consequat. Sed adipiscing diam donec adipiscing tristique risus nec. Volutpat diam ut venenatis tellus in metus vulputate eu. Ornare lectus sit amet est placerat in egestas erat. Faucibus interdum posuere lorem ipsum. A cras semper auctor neque vitae tempus. Nisi quis eleifend quam adipiscing vitae proin sagittis nisl. Aliquam ut porttitor leo a diam sollicitudin. Euismod in pellentesque massa placerat duis ultricies.
			</p>
				
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