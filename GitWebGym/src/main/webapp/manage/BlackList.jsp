<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>黑名單列表</title>

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
#section-header{
	margin-top: 70px;
}
#section-table{
	margin-top: 50px;
	margin-left:200px;
	width: 80%;
}

.table th{
	font-size: 20px;
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
					<li><a href="<c:url value="/manage/ItemListAll.jsp"/>">管理課程</a></li>
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
			<div class="col-md-offset-5 col-md-8">
			<h1 style="color:#ffffff;font-size: 50px;">黑名單</h1>
			</div>
		</div>
	</div>
</section>

<section id="section-table">
			<table class="table table-hover">
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
				 <!-- 從Bean方法中取出List內所需要的值 -->
				<c:forEach var="MembersBean" items="${blacklist}">
					<c:if test="${MembersBean.memberid != 'admin'}">
					<tr>
					<!-- 以下為動態取值欄位 -->
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
						<td style="text-align: center;">${MembersBean.is_blacklist}</td>	
			            <td>${MembersBean.m_identity}</td>	
		
						<td><form method="post" action="<c:url value="/MembersController"/>">
							<button type="submit" class="btn btn-success btn-lg">撤銷黑名單</button>
						     <input type="hidden" name="memberid" value="${MembersBean.memberid}">
						    <input type="hidden" name="action" value="authority_regain">
						    </form>		
						</td>
					</c:if>	
			      </c:forEach>			
				</tbody>
			</table>
</section>

</body>
</html>