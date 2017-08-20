<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>News</title>

<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src='../js/jquery.min.js'></script>
<script src="../js/bootstrap.min.js"></script>
<style>
body{
 	background-image: url(../images/news-bg.jpg); 
    background-size: cover;    
	font-size: 18px;
	font-weight: blod;
}

#section-post{
	margin-top: 100px;
}
.table th{
	text-align: left;
}
#section-header{
	margin-top: 70px;
}
</style>
</head>
<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/courses/enroll.jsp"/>">Courses</a></li>
				<li><a href="<c:url value="/guide/trainerguide.jsp"/>">Coaches</a></li>
				<li><a href="<c:url value="/selectAllpost?action=postList2"/>">News</a></li>
				<li><a href="<c:url value="/blog/articles.jsp"/>">Blog</a></li>
				<li><a href="<c:url value="/group/create.jsp"/>">Groups</a></li> 
			</ul>
			<ul class="nav navbar-nav navbar-right">	
				<c:if test="${empty memberLoginOK && empty trainerLoginOK}">
					<li><a href="<c:url value="/login/login.jsp"/>">登入</a></li>
					<li><a href="<c:url value="/signup/signup.jsp"/>">註冊</a></li>	
				</c:if>
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

<section id="section-post">
	<div class="container">
		<div class="row">
			<table class="table table-hover">
				<thead>
					<th style="text-align: center;">公告標題</th>
					<th style="text-align: center;">公告內容</th>
					<th style="text-align: center;width: 140px;">公告日期</th>
					<th style="text-align: center;width: 80px;">類型</th>	
				</thead>
				<tbody>
					<c:forEach var="PostBean" items="${postList}">
						<tr align='center' valign='middle'>
							<td style="text-align: justify;">${PostBean.title}</td>
							<td style="text-align: justify;">${PostBean.content}</td>
							<td style="text-align: center;">${PostBean.postdate}</td>
							<td>${PostBean.kind}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>
</body>
</html>