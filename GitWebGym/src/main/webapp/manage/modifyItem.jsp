<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改課程</title>

<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css">
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
<script src='${pageContext.request.contextPath }/js/jquery.min.js'></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

<style>
body{
	background-color: #97B6B8;
	color:#002147;
	font-size: 24px;
	font-family: 'cwTeXYen', sans-serif;
}
.navbar{
	font-size: 16px;
}
#section-header{
	margin-top: 100px;
}
#section-table{
	margin-top: 100px;
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
					<li><a href="<c:url value="/MembersController?action=BlackList"/>">黑名單</a></li>
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
			<h1 align="center" style="color:#ffffff;font-size: 50px;">課程管理頁面</h1>
		</div>
	</div>
</section>
<section class="section-table">
	<div class="container">
		<div class="row">
		<div class="col-md-offset-4 col-md-8">
			<form action="<c:url value="/EventController"/>" method="post" name="modifyItem">		

				<div class="form-group row">
			      <label for="trainerid" class="col-sm-2 col-form-label">課程編號</label>
			      <div class="col-sm-10">
			      ${EventBean.eventno}
			      </div>
				</div>
				
				<div class="form-group row">
			      <label for="trainerid" class="col-sm-2 col-form-label">教練ID</label>
			      <div class="col-sm-10">
			        <input type="text"  id="trainerid" name="trainerid" value="${EventBean.trainerid}">
			      </div>
    			</div>
    			
    			<div class="form-group row">
			      <label for="roomno" class="col-sm-2 col-form-label">上課地點</label>
			      <div class="col-sm-10">
			        <input type="text" id="roomno" name="roomno" value="${EventBean.roomno}">
			      </div>
    			</div>
    			
    			<div class="form-group row">
			      <label for="title" class="col-sm-2 col-form-label">課程名稱</label>
			      <div class="col-sm-10">
			        <input type="text" id="title" name="title" value="${EventBean.title}">
			      </div>
    			</div>
    			
    			<div class="form-group row">
			      <label for="coursekind" class="col-sm-2 col-form-label">課程類型</label>
			      <div class="col-sm-10">
			        <input type="text" id="coursekind" name="coursekind" value="${EventBean.coursekind}">
			      </div>
    			</div>
				
    			<div class="form-group row">
			      <label for="e_status" class="col-sm-2 col-form-label">是否開課</label>
			      <div class="col-sm-10">
			        <input type="text" id="e_status" name="status" value="${EventBean.e_status}">
			      </div>
    			</div>				

				<div class="form-group row">
			      <label for="eventstart" class="col-sm-2 col-form-label">開始時間</label>
			      <div class="col-sm-10">
			        <input type="text" id="eventstart" name="eventstart" value="${EventBean.eventstart}">
			      </div>
    			</div>	

				<div class="form-group row">
			      <label for="eventend" class="col-sm-2 col-form-label">結束時間</label>
			      <div class="col-sm-10">
			        <input type="text" id="eventend" name="eventend" value="${EventBean.eventend}">
			      </div>
    			</div>					


				<div class="form-group row">
			      <label for="eventend" class="col-sm-2 col-form-label">結束時間</label>
			      <div class="col-sm-10">
			        <input type="text" id="eventend" name="eventend" value="${EventBean.eventend}">
			      </div>
    			</div>					

				<div class="form-group row">
			      <label for="charge" class="col-sm-2 col-form-label">課程費用</label>
			      <div class="col-sm-10">
			        <input type="text" id="charge" name="charge" value="${EventBean.charge}">
			      </div>
    			</div>				
				

				
				<input type="text" name="action" value="modifyItem" hidden="hidden">
				<input type="text" name="id" value="${EventBean.eventno}" hidden="hidden">
				<input type="text" name="roomno" value="${EventBean.roomno}" hidden="hidden">
				<input type="text" name="trainerid" value="${EventBean.trainerid}" hidden="hidden">
				<input type="text" name="title" value="${EventBean.title}" hidden="hidden">
				<input type="text" name="coursekind" value="${EventBean.coursekind}" hidden="hidden">
				<input type="text" name="eventstart" value="${EventBean.eventstart}" hidden="hidden">
				<input type="text" name="eventend" value="${EventBean.eventend}" hidden="hidden">
				<input type="text" name="enroll" value="${EventBean.enroll}" hidden="hidden">
				<%-- <input type="text" name="charge" value="${EventBean.charge}" hidden="hidden"> --%>
				<button type="submit" class="btn btn-primary btn-bg">確定修改</button>
				<button type="submit" class="btn btn-default btn-bg" onclick="location.href='manage/Admin.jsp'">回管理頁面</button>
			
			</form>
			</div>
		</div>
	</div>
</section>



</body>
</html>