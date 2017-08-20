<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>課程列表</title>

<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css">
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src='../js/jquery.min.js'></script>
<script src="../js/bootstrap.min.js"></script>


<style>
body{
	background-color: #97B6B8;
	color:#002147;
	font-size: 18px;
	font-family: 'cwTeXYen', sans-serif;
}
.navbar{
	font-size: 16px;
}
th{
	font-size: 22px;
}

/* h1,h2,h3,h4,h5,h6{ */
/* 	font-family: 'Montserrat', sans-serif; */
/* } */
#section-header{
	margin-top: 70px;
}
#section-table{
	margin-top: 30px;
}



.table th,td{
	text-align: center;
	font-size: 16px;
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
				<li class="dropdown">
	             <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administration</a>
	             <ul class="dropdown-menu">
	             	<li><a href="<c:url value="/selectAllpost?action=postList"/>">發布公告</a></li>
	            	<li><a href="<c:url value="/MembersController?action=ListAll"/>">管理會員</a></li>
	             	<li><a href="<c:url value="/MembersController?action=BlackList"/>">黑名單</a></li>
	               	<li><a href="<c:url value="/selectAllmail?action=mailList"/>">站內信</a></li>            
	             </ul>
	           </li>
	           <li><a href="<c:url value="/LogoutServlet"/>">登出</a></li>
           </ul>
		</div>
	</div>
</nav>

<section id="section-header">
	<div class="container">
		<div class="row">
			<h1 align='center' style="color:#ffffff;font-size: 50px;"	>課程列表</h1>
		</div>
	</div>
</section>


<section id="section-table">
<div class="container">
	<div class="row">
		<table class="table table-hover">
			<thead>
				<th>課程編號</th>
				<th>教練ID</th>
				<th>上課地點</th>
				<th>課程名稱</th>
				<th>訓練類型</th>
				<th>是否開課</th>
				<th>課程開始</th>
				<th>課程結束</th>
				<th>課程價格</th>
				<th>管理</th>
			</thead>
			<tbody>
			<c:forEach var="EventBean" items="${list}">
				<tr align='center' valign='middle'>
					<td>${EventBean.eventno}</td>
					<td>${EventBean.trainerid}</td>
					<td>${EventBean.roomno}</td>
					<td>${EventBean.title}</td>
					<td>${EventBean.coursekind}</td>
					<td>${EventBean.e_status}</td>
					<td>${EventBean.eventstart}</td>
					<td>${EventBean.eventend}</td>
					<td>${EventBean.charge}</td>
					
					<c:if test="${!empty AdminOK }">
					<td>
					<form method="post" action="<c:url value="/EventController"/>">
						<button type="submit" class="btn btn-warning">修改課程 </button>
					     <input type="hidden" name="id" value="${EventBean.eventno}">
					     <input type="hidden" name="status" value="${EventBean.e_status}">
					    <input type="hidden" name="action" value="authority_check">				
					</form>
					</td>
					</c:if>				
		      </c:forEach>	
		      </tbody>		
			</table>
		</div>
	</div>
</section>


</body>
</html>