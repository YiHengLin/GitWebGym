<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的訂單</title>
	<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body{
	font-size: 16px;
}
#section-header{
	margin-top: 100px;
}

footer{
		margin-top:100px;
	  position: absolute;  
	  right: 0;
	  left: 0;
}
.flex-container {
    display: -webkit-flex;
    display: flex;
    width: 100%;
    height: 200px;
    justify-content: center;
}

.flex-item {
/* 	background-color:yellow; */
 	width:200px; 
 	height:200px; 
    margin: 20px;
    border-radius: 20px;
	text-align: center;
}
#flex-item-1{	
  	   	background-image: url(../../images/order-footer-1.jpg);   
     	background-size: cover;
}


#flex-item-2{	
  	   	background-image: url(../../images/order-footer-2.jpg);   
     	background-size: cover;
}

#flex-item-3{	
  	   	background-image: url(../../images/order-footer-3.jpg);   
     	background-size: cover;
}


#flex-item-4{	
  	   	background-image: url(../../images/order-footer-4.jpg);   
     	background-size: cover;
}

#flex-item-5{	
  	   	background-image: url(../../images/order-footer-5.jpg);   
     	background-size: cover;
}

</style> 
 	
</head>
<body>

<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
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
		<c:if test="${not empty memberLoginOK}">
			<ul class="nav navbar-nav navbar-right">
<%-- 				<li><a href="<c:url value="/TestServlet"/>">test</a></li> --%>
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
	                <li><a href="<c:url value="/membercenter/mytrains.jsp" />">健身歷程</a></li> 
	                <li><a href="<c:url value="/group/create.jsp" />">揪團</a></li>  
	                <li><a href="<c:url value="/membercenter/sendmail.jsp" />">站內信</a></li>   	              
	                <li><a href="<c:url value="/membercenter/memberprofile.jsp" />">個人資料</a></li>		                     
	              </ul>
	            </li>
				<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>	
			</ul>
		</c:if>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>

<section id="section-header">
	<div class="container">
		<div class="row">
</section>


<section id="section-content">
	<div class="container">
		<div class="row">
		<table border="2" class="table table-hover">
			<tr>
				<th colspan="3" style="text-align: center;" >${memberLoginOK.m_name}的訂單明細</th>
			</tr>
			<tr>
				<th style="text-align: center;" >訂單編號</th>
				<th style="text-align: center;" >總金額</th>
				<th style="text-align: center;" >購買日期</th>
			</tr>
			<jsp:useBean id="YuiOrderBeanList" class="YuiAddOrder.YuiOrderDAO" scope="page"/>
			<c:forEach var="YuiOrderBean" items="${YuiOrderBeanList.allOrder}">
				<c:if test="${YuiOrderBean.memberID == memberLoginOK.memberID }">
					<tr>
					
						<td style="text-align: center;" ><a href="<c:url value="/YuiShowOrderDetailServlet?orderno=${YuiOrderBean.orderno}"/>">${YuiOrderBean.orderno}</a></td>
						<td style="text-align: center;" >${YuiOrderBean.totalcharge}</a></td>
						<td style="text-align: center;" >${YuiOrderBean.enrollday}</a></td>
					</tr>
				</c:if>
			</c:forEach>
			
			<tr>
				<TD colspan="3" align='center'>
					<a href="<c:url value="/courses/enroll.jsp"/>">返回課程查詢頁面</a>
				</TD>
			</tr>
		</table>	
		</div>
	</div>
</section>
<footer class="footer">
  <div class="flex-container" >

		<div class="flex-item" id="flex-item-1"></div>
		<div class="flex-item" id="flex-item-2"></div>
		<div class="flex-item" id="flex-item-3"></div>
		<div class="flex-item" id="flex-item-4"></div>
		<div class="flex-item" id="flex-item-5"></div>

  </div>
</footer>


</body>
</html>