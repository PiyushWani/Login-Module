<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="pojo.UserInfoPojo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/temporary.css">
<style type="text/css">	
	.center-me{
	text-align:center
	}
</style>

</head>
<body>

<%
	if(request.getAttribute("tray") == null){
		System.out.println("tray is null. Redirectiong to Welcome.jsp");
		response.sendRedirect("WelcomePage.jsp");
	}	

	try
	{		
		UserInfoPojo tray = new UserInfoPojo();
		tray = (UserInfoPojo)request.getAttribute("tray");

%>
<form class="registration_form" action="http://localhost:7010/Imagico2/NewUserFormServ" method="post">
	<div class="container-fluid">
		<div class="row">
			<div class="col card">
				<h2 class="center-me">Registration Form</h2>
				<input class="center-me" title="Email ID. Cannot be changed" type="email" name="emailId" value="<%=tray.getEmailId()%>" placeholder="Email Id" style="border:none" readonly />
				<span class="errors">${INVALID_EMAIL_FORMAT}</span>
				<input type="text" name="firstName" value="<%=tray.getFirstName()%>" placeholder="First Name"/>
				<span class="errors">${INVALID_FIRSTNAME}</span>
				<input type="text" name="lastName" value="<%=tray.getLastName()%>" placeholder="Last Name"/>
				<span class="errors">${INVALID_LASTNAME}</span>
				<input type="password" name="password" placeholder="Password"/>
				<input type="password" name="confirmPassword" placeholder="Confirm Password"/>
				<span class="errors">${PASSWORDS_MISMATCH}</span>
				<span class="errors">${BAD_PASSWORD}</span>
				<span class="errors">${DATABASE_SQL_EXCEPTION}</span>
				<input class="submit_button" type="submit" value="Register"/>
				<div> <a href="WelcomePage.jsp">Sign In </a>with a different account? </div>
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

<%
	}catch(Exception e)
	{
		System.out.println("Jasper Exception occured.");
	}
%>
</body>
</html>