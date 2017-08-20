<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理員發佈公告</title>
<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css">
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src='../js/jquery.min.js'></script>
<script src="../js/bootstrap.min.js"></script>>

<style>
body{
	background-color: #97B6B8;
	color:#002147;
	font-size: 18px;
	font-family: 'cwTeXYen', sans-serif;
	
}

#section-post{
	margin-top: 20px;
}
.table th{
	text-align: center;
}

.table td{
	text-align: justify;
}
#section-header{
	margin-top: 70px;
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
	<c:if test="${not empty trainerLoginOK || not empty memberLoginOK}">
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
             <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administration</a>
             <ul class="dropdown-menu">       		
            	<li><a href="<c:url value="/MembersController?action=ListAll"/>">管理會員</a></li>
            	<li><a href="<c:url value="/EventController?action=ItemListAll"/>">管理課程</a></li>
             	<li><a href="<c:url value="/MembersController?action=BlackList"/>">黑名單</a></li>
               	<li><a href="<c:url value="/selectAllmail?action=mailList"/>">站內信</a></li>               
             </ul>
           </li>
           <li><a href="<c:url value="/LogoutServlet"/>">登出</a></li>
          </ul>
	</c:if>
	</div>
	</div>
</nav>

<section id="section-header">
<div class="container">
	<div class="row">
		<h1 align='center' style="color:#ffffff;font-size: 50px;">公告專區</h1>
	</div>
</div>
</section>	
	

<section id="section-post">
<div class="container">
	<div class="row">
		<div class="col-md-6">
			<h3 style="text-decoration: underline;">發布新公告</h3>
			<form action="<c:url value='/SendPostController'/>" method="POST">
			<input type="hidden" name="posttime" class="form-control" id="time">
			<input type="hidden" name="memberid" class="form-control" id="id"value='${AdminOK.memberID}'>
			
			<div class="form-group">
				<label for="title" style="font-size: 20px;">標題</label>
				<input type="text" class="form-control col-4" name="title" id="title" placeholder="請輸入標題">
			</div>
			
			<div class="form-group">
				<select class="form-control mb-2 mr-sm-2 mb-sm-0" name="kind" >
					<option>公告分類</option>
					<option value="公告">公告</option>
					<option value="系統">系統</option>
					<option value="通知">通知</option>
				</select> 
			</div>
			<div class="form-group">
				<label for="content" style="font-size: 20px;">內容</label>
				<textarea class="form-control" name="content" rows="10"
				id="content" value=""></textarea>
			</div>
					<!-- 此處識別字串需要修改為本專案的AdminOK.memberid-->
			<div class="form-group">
					<button type="button" class="btn btn-default" onclick="location.href='Admin.jsp'">回管理頁面</button>
					<button type="submit" class="btn btn-primary">送出</button>
					<font color='red'>${errorMsg.titleError}</font><br> <font
						color='red'>${errorMsg.kindError}</font><br> <font
						color='red'>${errorMsg.contentError}</font><br>			

			</div>
		</form>
	</div>
</div>

<div class="container">
	<div class="row">
		
		<table class="table table-hover">
			<thead>
				<th>No.</th>
				<th>Author</th>
				<th>公告標題</th>
				<th>公告內容</th>
				<th>發送時間</th>
				<th>Type</th>	
			</thead>
			<tbody>
				<c:forEach var="PostBean" items="${postList}">
					<tr align='center' valign='middle'>
						<td>${PostBean.postno}</td>
						<td>${PostBean.memberid}</td>
						<td>${PostBean.title}</td>
						<td>${PostBean.content}</td>
						<td>${PostBean.postdate}</td>
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