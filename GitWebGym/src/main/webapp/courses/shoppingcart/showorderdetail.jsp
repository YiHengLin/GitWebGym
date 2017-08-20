<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>個人訂單明細</title>
<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

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
  	   	background-image: url(<c:url value="/images/order-footer-5.jpg" />);   
     	background-size: cover;
}


#flex-item-2{	
  	   	background-image: url(<c:url value="/images/order-footer-7.jpg" />);   
     	background-size: cover;
}

#flex-item-3{	
  	   	background-image: url(<c:url value="/images/order-footer-2.jpg" />);   
     	background-size: cover;
}


#flex-item-4{	
  	   	background-image: url(<c:url value="/images/order-footer-10.jpg" />);   
     	background-size: cover;
}

#flex-item-5{	
  	   	background-image: url(<c:url value="/images/order-footer-3.jpg" />);   
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
		<c:if test="${not empty memberLoginOK}">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/courses/enroll.jsp"/>">回選課畫面</a></li>
				<li><a href="<c:url value="/membercenter/membercenter.jsp"/>">Hi ${memberLoginOK.memberID } !</a></li>
				<li><a href="<c:url value="/LogoutServlet"/>">登出</a></li>
			</ul>
		</c:if>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>

<section id="section-header"> 
	<div class="container">
		<div class="row">
			<table border = "2" class="table table-hover">
				<tr>
					<th colspan="5" style="text-align: center;"><h3>${memberLoginOK.m_name}的訂單明細</h3></th>
				</tr>
				<tr>
					<td colspan="5">
						<table width="700">
							<tr>
								<td align="center">訂購日期: ${orderBean.enrollday}</td>
								<td align="center">訂單編號: ${orderBean.orderno}</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th style="text-align: center;">課程名稱</th>
					<th style="text-align: center;">教練姓名</th>
					<th style="text-align: center;">地點</th>
					<th style="text-align: center;">課程日期</th>
					<th style="text-align: center;">金額</th>
				</tr>
				<c:forEach var="orderDetails" items="${orderDetails}" >
				<tr>
					<td style="text-align: center;">${orderDetails.event_title}</td>
					<td style="text-align: center;">${orderDetails.t_name}</td>
					<td style="text-align: center;">${orderDetails.locationname} ${orderDetails.room_title}</td>
					<td style="text-align: center;">${orderDetails.eventstart}到${orderDetails.eventend}</td>
					<td style="text-align: center;">${orderDetails.charge}</td>
					
				</tr>
				</c:forEach>
				<tr>
					<TD colspan='5' align='center'>總金額: ${orderBean.totalcharge}元</TD>
				</tr>
	
			</table>
		</div>
		<div class="row">
			<table width="700" height="50" align="center" style="margin: auto;">
				<tr>
					<td height="40" align="center">
						<a href="<c:url value="/courses/shoppingcart/myorder.jsp"/>">返回我的訂單頁面</a>
					</td>
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