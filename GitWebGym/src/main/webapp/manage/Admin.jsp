<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理員首頁</title>
<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link rel="stylesheet" href="../css/animations.css">
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<script src='../js/jquery.min.js'></script>
<script src="../js/bootstrap.min.js"></script>>

<style>

body{
	background-image: url(../images/admin_bg.jpg);
	background-size:cover;
/* 	background-repeat: no-repeat; */
/* 	background-attachment: fixed; */
/*  	background-position: 50% 150%;   */
 		
	font-size: 16px;
}

h1,h2,h3,h4,h5,h6{
	font-family: 'Montserrat', sans-serif;
}

#section-header{
	margin-top: 100px;
}


.feature{
	display:inline-block;
	padding: 10px;
	text-align: center;
}

.feature-img{
		border-radius: 20px;
		text-align: center;
		vertical-align: middle;	
 		width:100%; 
 		height:100%; 
 		background-color: #002147;
}
.feature-img h1{
	font-weight: bold;
	color:#ffffff;
}


</style>

</head>



<body>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="<c:url value="/manage/Admin.jsp"/>">Temmujin Fitness</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav navbar-right">
           <li><a href="<c:url value="/LogoutServlet"/>">登出</a></li>
        </ul>
	</div>
	</div>
</nav>


<section id="section-header">
	<div class="container">
		<div class="row">
			<h1 align="center" style="font-size: 100px;color:#ffffff;">Administration</h1> 
		</div>
	</div>
</section>


<section id="section-list">
<div class="container">
	<div class="row">
		<a href="<c:url value="/selectAllpost?action=postList"/>">
			<div class="col-md-offset-1 col-md-2 feature ">		
				<div class="feature-img" id="feature-img-1" >
					<h1>發布公告</h1>
				</div>
			</div>
		</a>
		
		<a href="<c:url value="/MembersController?action=ListAll"/>">
			<div class="col-md-2 feature ">
				<div class="feature-img" id="feature-img-2">
					<h1>管理會員</h1>				
				</div>
			</div>
		</a>
		
		<a href="<c:url value="/EventController?action=ItemListAll"/>">
			<div class="col-md-2 feature ">
				<div class="feature-img" id="feature-img-3">
					<h1>管理課程</h1>				
				</div>
			</div>
		</a>
		

		
		<a href="<c:url value="/MembersController?action=BlackList"/>">
			<div class="col-md-2 feature ">
				<div class="feature-img" id="feature-img-4">
					<h1>黑名單</h1>				
				</div>
			</div>
		</a>	
		
		<a href="<c:url value="/selectAllmail?action=mailList"/>">
			<div class="col-md-2 feature ">
				<div class="feature-img" id="feature-img-5">
					<h1>站內信</h1>				
				</div>
			</div>
		</a>	
		</div>
	</div>
</section>

</body>
</html>