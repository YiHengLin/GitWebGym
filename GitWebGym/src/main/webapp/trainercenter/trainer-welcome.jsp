<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trainer Space </title>

	<link rel="stylesheet" href="../css/magnific-popup.css">
	<link rel="stylesheet" href="../css/animations.css">
	<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
    <link href='../css/jquery.qtip.min.css' rel='stylesheet' />
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <script src='../js/jquery.min.js'></script>
    <script src="../js/bootstrap.min.js"></script>
<style>
body{
/* 	background-image:url(../images/ccc.jpg); */
/* 	background-color: #7698B1; */
}

.flex-container {
    display: -webkit-flex;
    display: flex;
    width: 100%;
    height: 360px;
    justify-content: center;
}

.flex-item {
 	width:360px; 
 	height:360px; 
    margin: 30px;
    border-radius: 20px;
	text-align: center;
	padding-top: 100px;
}

#flex-item-1{
  	   	background-image: url(../images/trainercenter-3.jpg);   
     	background-size: cover;    
     	visibility: hidden;
}

#flex-item-2{	
  	   	background-image: url(../images/trainercenter-4.jpg);   
     	background-size: cover;
     	visibility: hidden;
}

#section-content{
	margin-top: 100px;
}
#div-greeting{
	margin-bottom: 100px;
	text-align: center;
	
}

#div-greeting h1{
	font-size: 70px;
	color:#002147;	
	font-weight: bold;
}

h3{
	color:#ffffff;
	font-size: 60px;
	font-weight: bold;
}
</style>
<script >
$(document).ready(function(){
	$(".flex-item").addClass("fadeIn")
})
</script>
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">
			
		</div>
		<div id="navbar" class="navbar-collapse collapse">
		<a class="navbar-brand" href="<c:url value="/trainercenter/trainer-welcome.jsp"/>">Temmujin Fitness</a>
		<c:if test="${not empty trainerLoginOK}">
			<ul class="nav navbar-nav navbar-right">
				 <c:if test="${loginidentity=='trainer' }">
					<li class="dropdown">
		              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi ${trainerLoginOK.trainerID } !</a>
		              <ul class="dropdown-menu">
		             	<li><a href="<c:url value="/trainercenter/opencourse.jsp"/>">New Course</a></li>
		              	<li><a href="<c:url value="/blog/postarticle.jsp"/>">Post Article</a></li>            
		              </ul>
		            </li>
				 </c:if>	
				<li><a href="<c:url value="/LogoutServlet"/>">登出</a></li>
			</ul>
		</c:if>
		</div><!--/.nav-collapse -->
		</div>
	</nav>


<section id="section-content">
	<div class="container">
		<div class="row" id="div-greeting">
			<h1>Coach Space</h1>
		</div>
		
		<div class="row">
			<div class="flex-container">
		
				<a href="<c:url value="/trainercenter/opencourse.jsp"/>">
				<div class="flex-item" id="flex-item-1">
						<h3>New Course</h3>				
				</div>
				</a>
				
				
				<a href="<c:url value="/blog/postarticle.jsp"/>">
				<div class="flex-item" id="flex-item-2">
						<h3>Post Article</h3>
				</div>
				</a>
			
			</div>
		</div>
	</div>
</section>


<script src="../js/wow.min.js"></script>

</body>
</html>