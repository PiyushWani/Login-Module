<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/WelcomePageStyles.css">
<style type="text/css">	
	
</style>
</head>
<body>
<form action="http://localhost:7010/Imagico2/WelcomePageServ" method="post">
	<div class="container-fluid">
		<div class="row">
			<div class="col card">
				Imagico.com
				<input type="email" name="emailId" placeholder="EMAIL" required>
				<button class="submit_button" type="submit"> SUBMIT </button>
				<span style="text-align:center; color:red">${ErrorCode}</span>
				<a href="">Forgot Email?</a>
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
					hi! How are you Doing?
				</div>
			</div>
	</div>
</form>
</body>
</html>