<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>寄信成功!</title>
<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css">
<link href="../css/bootstrap.min.css" rel="stylesheet" >
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<style>
body{
	font-size: 16px;
}
#section-content{
	margin-top: 100px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<c:if test="${not empty memberLoginOK}">	      
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/courses/enroll.jsp"/>">Courses</a></li>
				<li><a href="<c:url value="/guide/trainerguide.jsp"/>">Coaches</a></li>
				<li><a href="<c:url value="/selectAllpost?action=postList2"/>">News</a></li>
				<li><a href="<c:url value="/blog/articles.jsp"/>">Blog</a></li>
				<li><a href="<c:url value="/group/create.jsp"/>">Groups</a></li> 
			</ul>
         	</c:if>
			<ul class="nav navbar-nav navbar-right">
			<c:if test="${not empty memberLoginOK}">                                      
				<c:if test="${not empty ShoppingCart.size}">
					<li><a id = "cartSize" href="<c:url value="/courses/shoppingcart/showcartcontent.jsp"/>">購物車(${ShoppingCart.size})</a><li>
				</c:if>
				<c:if test="${empty ShoppingCart.size}">
					<li><a id = "cartSize" href="<c:url value="/courses/shoppingcart/showcartcontent.jsp"/>">購物車(0)</a></li>
				</c:if>                                   
				<li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi ${memberLoginOK.memberID } !</a>
	              <ul class="dropdown-menu">
	                <li><a href="<c:url value="/courses/enroll.jsp"/>">報名課程</a></li>	
	                <li><a href="<c:url value="/courses/shoppingcart/myorder.jsp"/>">歷史紀錄</a></li> 
	                <li><a href="<c:url value="/membercenter/mytrains.jsp" />">健身歷程</a></li>  
 	             	<li><a href="<c:url value="/group/create.jsp" />">揪團</a></li>  
	                <li><a href="<c:url value="/membercenter/memberprofile.jsp" />">個人資料</a></li>		                     
	              </ul>
	            </li>
				<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>	
			</c:if>				
			</ul>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>
<section id="section-content">
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1 style="color:red;">回報成功</h1>
				<h3>感謝會員${memberLoginOK.memberID}的來信</h3>
				<h3>我們將會盡快處理您的問題</h3>
			
			</div>
		</div>
	</div>
</section>
</body>
</html>