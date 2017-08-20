<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員中心</title>
<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
<link rel="stylesheet" href="../css/bootstrap.min.css"></link>
<link rel="stylesheet" href="../css/animations.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style>

body{
/* 	background-color: #5073AB; */
	color:#ffffff;
}
h1,h2,h3,h4,h5,h6{
	font-family: 'Montserrat', sans-serif;
}

#section-content{
	margin-top: 100px;
}

.flex-container {
    display: -webkit-flex;
    display: flex;
    width: 100%;
    height: 360px;
    justify-content: center;
}

.flex-item {
	border-radius: 20px;
 	width:240px; 
 	height:240px; 
    margin: 20px;
    border-radius: 20px;
	text-align: center;
	padding-top: 100px;
}


#flex-item-1{
  	   	background-image: url(../images/membercenter-1.jpg);   
     	background-size: cover;    
     	visibility: hidden;
}

#flex-item-2{
  	   	background-image: url(../images/membercenter-2.jpg);   
     	background-size: cover;    
     	visibility: hidden;
}

#flex-item-3{
  	   	background-image: url(../images/membercenter-3.jpg);   
     	background-size: cover;    
     	visibility: hidden;
}

#flex-item-4{
  	   	background-image: url(../images/membercenter-4.jpg);   
     	background-size: cover;    
     	visibility: hidden;
}

#flex-item-5{
  	   	background-image: url(../images/membercenter-5.jpg);   
     	background-size: cover;    
     	visibility: hidden;
}

#flex-item-6{
  	   	background-image: url(../images/membercenter-6.jpg);   
     	background-size: cover;    
     	visibility: hidden;
}


#div-greeting{
	margin-bottom: 50px;
	text-align: center;
	
}

div#div-greeting h1{
	font-size: 70px;
	color:#ffffff;	
	font-weight: bold;
}

.flex-item h3{
	color:#ffffff;
	font-size: 40px;
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
			<a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/membercenter/membercenter.jsp"/>">Hi ${memberLoginOK.memberID } !</a></li>
				<li></li>
				<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>
			</ul>
		</div>
		</div>
	</nav>
	

<section id="section-content">
	<div class="container">
		<div class="row" id="div-greeting">
			<h1 style="color:#002147;">Member Space</h1>
		</div>
		
		<div class="flex-container" >
		
			<a href="<c:url value="/courses/enroll.jsp"/>">
			<div class="flex-item" id="flex-item-1">
				<h3>報名課程</h3>				
			</div>
			</a>
			
			<a href="<c:url value="/courses/shoppingcart/myorder.jsp"/>">
			<div class="flex-item" id="flex-item-2">
				<h3>歷史紀錄</h3>				
			</div>
			</a>
			
			<a href="<c:url value="/membercenter/mytrains.jsp"/>">
			<div class="flex-item" id="flex-item-3">
				<h3>健身歷程</h3>				
			</div>		
			</a>		
					
		</div>
		
		<div class="flex-container" >
		
			<a href="<c:url value="/group/create.jsp"/>">
			<div class="flex-item" id="flex-item-4">
				<h3>揪團</h3>				
			</div>	
			</a>
		
			<a href="<c:url value="/membercenter/sendmail.jsp"/>">
			<div class="flex-item" id="flex-item-5">
				<h3>站內信</h3>				
			</div>	
			</a>
			
			<a href="<c:url value="/membercenter/memberprofile.jsp"/>">
			<div class="flex-item" id="flex-item-6">
				<h3>個人資料</h3>				
			</div>	
			</a>			
			
		</div>
		
		
	</div>
</section>



</body>
</html>