<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="admin_members.model.*"%>
<%
	MembersService memSvc = new MembersService();
	List<MembersBean> list = memSvc.getAll();
    pageContext.setAttribute("list",list);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員名單</title>

<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css">
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src='../js/jquery.min.js'></script>
<script src="../js/bootstrap.min.js"></script>>

<style>

body{
	background-color: #97B6B8;
	color:#002147;
	font-size: 16px;
	font-family: 'cwTeXYen', sans-serif;
}
h1,h2,h3,h4,h5,h6{
	font-family: 'Montserrat', sans-serif;
}
#section-header{
	margin-top: 70px;
}


.table th,td{
	text-align: center;
	font-size: 18px;
}

#section-table{
	margin-top: 50px;
	margin-left:200px;
	width: 80%;
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
	            	<li><a href="<c:url value="/EventController?action=ItemListAll"/>">管理課程</a></li>
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
		<div class="row" >
			<h1 align="center" style="color:#ffffff;font-size: 50px;"	>會員資料</h1>
		</div>	
	</div>
</section>

<section id="section-table">

	<table class="table table-hover ">
		<thead>
			<th>Photo</th>
			<th>ID</th>
			<th>Name</th>
			<th>Id Number</th>
			<th>Gender</th>
			<th>Birth</th>
			<th>Email</th>
			<th>Mobile</th>
			<th>Tel</th>
			<th>Address</th>
			<th>Register</th>
			<th>Blacklist</th>
			<th>Identity</th>
			<th>Manage</th>
		</thead>
		<tbody>
		
		<%@ include file="page1.file" %> 

		<c:forEach var="MembersBean" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<c:if test="${MembersBean.memberid != 'admin'}">
			<tr>

			    <td><img style='margin-bottom:0px' hight='124px' width='124px'
			    src = '${pageContext.servletContext.contextPath}/Members.controller/ReadPicFromDB?id=${MembersBean.memberid}'></td>
			    
				<td>${MembersBean.memberid}</td>
				<td>${MembersBean.m_name}</td>
				<td>${MembersBean.m_id_number}</td>
				<td>${MembersBean.m_gender}</td>
				<td>${MembersBean.m_bday}</td>
				<td>${MembersBean.m_mail}</td>
				<td>${MembersBean.m_mobile}</td>
				<td>${MembersBean.m_tel}</td>
				<td>${MembersBean.m_address}</td>
				<td>${MembersBean.m_register}</td>
				<td>${MembersBean.is_blacklist}</td>
				<td>${MembersBean.m_identity}</td>
				
				<td><form method="post" action="<c:url value="/MembersController"/>" id="manageform"> 
						<c:if test="${MembersBean.is_blacklist  == 'n'}">
						<button type="submit" class="btn btn-danger btn-lg">加入黑名單</button>
						</c:if>
						<c:if test="${MembersBean.is_blacklist  == 'y'}">
						<button type="submit" class="btn btn-default btn-lg" disabled="true">已在黑名單</button>
						</c:if>
						<input type="hidden" name="memberid" value="${MembersBean.memberid}"> 
							<input type="hidden" name="action" value="set_blacklist">
					</form></td>
				
				<td><form method="post"
						action="<c:url value="/MembersController"/>"> 
						<input type="hidden"
							name="memberid" value="${MembersBean.memberid}"> 
							<input type="hidden"
							name="action" value="authority_check">
					</form></td>
			<tr>
			</c:if>
			</c:forEach>
		</tbody>
	</table>
	<%@ include file="page2.file" %> 
</section>
</body>
</html>