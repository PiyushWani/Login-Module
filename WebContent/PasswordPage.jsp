<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/PasswordPageStyles.css">
<style type="text/css">	
	
</style>
<% if(request.getAttribute("emailId") == null) 
	response.sendRedirect("WelcomePage.jsp");%>
</head>
<body>
<form action="http://localhost:7010/Imagico2/PasswordPageServ" method="post">
	<div class="container-fluid">
		<div class="row">
			<div class="col card">
				Imagico.com
				<input type="email" name="emailId" placeholder="EMAIL" value="<%=request.getAttribute("emailId")%>" readonly>
				<input type="password" name="password" placeholder="Password" required/>
				<button class="submit_button" type="submit"> Sign In </button>
				<span style="text-align:center; color:red">
					${WRONG_PASSWORD}
					${DATABASE_SQL_EXCEPTION}
					${DATABASE_CLASS_EXCEPTION}
				</span>
				<div class="container">
		 			<a href="">Forgot password?</a> | Not you? <a href="WelcomePage.jsp">Go back</a>		
				</div>	
			</div>
		</div>
	
			<div class="row">
				<div class="col col-sm-7">	
				</div>
				<div class="col col-sm-3">
					<button class="small_button" type=button >Help</button>
					<button class="small_button" type=button >Privacy</button>
					<button class="small_button" type=button >Terms</button> 
				</div>
				<div class="col col-sm-2">
				</div>
			</div>
		
	</div>
</form>
</body>
</html>