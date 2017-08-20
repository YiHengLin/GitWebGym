<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <link href='../css/jquery.qtip.min.css' rel='stylesheet' />
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <script src='../js/jquery.min.js'></script>
    <script src="../js/bootstrap.min.js"></script>
    
    
    
<style>
  
h1{
	font-family: 'Montserrat', sans-serif;
}
body{
	font-size:20px;;
/*    	background-image: url(../images/header-img_1200x400.jpg);  */
   	background-image: url(../images/login_bg.jpg);
/*    	background-size: cover;   */
 	background-repeat: no-repeat;
    background-attachment: fixed;
 	background-position: 50% 90%;  
}

.navbar-header {
    float: left;
    padding: 15px;
    text-align: center;
    width: 100%;
}
.navbar-brand {float:none;}
  
#inputEmail,
#inputPassword {
    margin-top: 5px;
    margin-bottom: 5px;
}

.checkbox {
    float: left;
}
#section-login{
	margin-top: 100px;
}


</style>
 <script>
 $(document).ready(function(){
	 
		$("input:checkbox").on('click', function() {
			  
			  var $box = $(this);
			  if ($box.is(":checked")) {
			    var group = "input:checkbox[name='" + $box.attr("name") + "']";
			    $(group).prop("checked", false);
			    $box.prop("checked", true);
			  } else {
			    $box.prop("checked", false);
			  }
		});
		
	$('input[name="identity"]').change(function(){
		console.log($('input[name="identity"]:checked').val())
	})

 })   

    
</script>
    
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
			</div>
		</div>
	</nav>

<section id="section-login">
    <div class="container">
    	<div class="row">
    		<div class="col-md-offset-2 col-md-8" id="div-login">
    			<h1>Login</h1>
    		</div>
    		<div class="col-md-offset-2 col-md-8" id="div-login">
		        <form class="form-signin"  action="<c:url value="/login/login.do"/>" method="POST">
		            
		            
		            <input type="text" name="trainerID"  id="inputID" class="form-control input-lg" placeholder="ID"  autofocus value="batman">
		            <small><font color="red" size="-3">${errMsgkey.idEmpty}</font></small>
		            <label for="inputPassword" class="sr-only">Password</label>
		            
		            <input type="password"  name="Password" id="inputPassword" class="form-control input-lg" placeholder="Password"  value="batman123">
		            <small><font color="red" size="-3"> ${errMsgkey.pwEmpty} </font></small>
		            
		            
		            <div class="checkbox">
		<!--                 <label> -->
		<!--                     <input type="checkbox" name="remeberMe" checked='checked' value="true"> Remember me -->
		<!--                 </label> -->
		           				 <label>
		                            <input type="checkbox" name="identity" value="trainer"> coach
		                        </label>
		                        <label>
		                            <input type="checkbox" name="identity" value="member"> member
		                        </label>
		                 <small><font color="red" size="-3"> ${errMsgkey.LoginError} </font></small>
		            </div>            
		            <button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
		        </form>
        	</div>
        </div>
    </div>
    <!-- /container -->
</section>
</body>

</html>