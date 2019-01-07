<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Imagico Editor</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Image display -->
<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>

</head>


<style>
.footer {
	  position: fixed;
	  left: 0;
	  bottom: 0;
	  width: 100%;
	  background-color: red;
	  color: white;
	  text-align: center;
	}
</style>

<script>

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
</script>

<body>
<%
	/* if(!(request.getSession().getId().equals(request.getServletContext().getAttribute("sessionId"))))
	{
		response.sendRedirect("WelcomePage.jsp");
	} */
%>



	<div>
		<div class="row w3-padding w3-teal" >
			<div class="col-sm-2">
				<button class="w3-button" type="button"> <- </button>
			</div>
			<div class="col-sm-8" align="center">
					<h2> Imagico.com </h2>
			</div>
			<div class="col-sm-2" align="right">
				<form action="http://localhost:7010/Imagico2/LogoutServ">
				<input class="w3-button" type="submit" value="Logout">
				</form>
			</div>
		</div>	
		
		<div class="row" style="background-color:#f1f1f1" align="center">
			<div class="navigation-bar col-sm-3" >	
				  <button class="w3-button " type="button" onclick=""> Home </button>
			</div>
			<div class="navigation-bar col-sm-3">	
				<input class="w3-button"  type="file" onchange="readURL(this);" name="picture" accept="image/*">
  				<input class="w3-button" type="submit" value="Upload"> 
			</div>
			
			<div class="navigation-bar col-sm-3">	
				 <div class="w3-dropdown-hover " style="background-color:#f1f1f1"align="center">
					  <button class="w3-button " >Recent Images</button>
					  <div class="w3-dropdown-content w3-bar-block">
						    <a href="#" class="w3-bar-item w3-button w3-block">Image1.jpg</a>
						    <a href="#" class="w3-bar-item w3-button w3-block">Image2.jpg</a>
						    <a href="#" class="w3-bar-item w3-button w3-block">Image3.jpg</a>
					  </div>
				</div>
			</div>
			
			<div class="navigation-bar col-sm-3">	
				<button class="w3-button" type="button" onclick=""> Contact us </button>
			</div>
		</div>	  
		
		<div class="image-canvas">
			<img id="blah" src="#" alt="your image" />
		</div>
		<div class="w3-padding w3-teal footer" >
		</div>
	</div>

</body>
</html>