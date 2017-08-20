<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>讀取站內信</title>

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
th{
	font-size: 22px;
}

#section-header{
	margin-top: 70px;
}
#section-allpost{
	margin-top: 20px;
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
			<h1 align='center' style="color:#ffffff;font-size: 50px;">站內信息列表</h1>
		</div>
	</div>
</section>

<section id="section-allpost">
	<div class="container">
		<div class="row">
			<table class="table table-hover">
				<thead>
					<th>No.</th>
					<th>寄件者</th>
					<th>標題</th>
					<th>內容</th>
					<th>發送時間</th>
					<th>類型</th>
				</thead>
				<tbody>
				<c:forEach var="MailBean" items="${mailList}">
					<tr>
						<td>${MailBean.mailno}</td>
						<td>${MailBean.memberid}</td>
						<td>${MailBean.title}</td>
						<td>${MailBean.content}</td>
						<td>${MailBean.m_postdata}</td>
						<td>${MailBean.kind}</td>			
					</tr>	
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</section>

<!-- <section id="section-writepost"> -->
<!-- 	<div class="container"> -->
<!-- 		<div class="row"> -->
<!-- 			<h3>管理員站內信息</h3>  -->
<!-- 			<div class="col-md-8"> -->
<%--                 <form action="<c:url value='SendMailController'/>" method="POST" > --%>
<!--                 <input type="text" name="kind" value="toMember" placeholder="請輸入標題" hidden="hidden"> -->
<!--                 <label for="title">輸入標題</label> -->
<!--                     <div class="form-group"> -->
<!-- 	                    <select name="kind" > -->
<!-- 							<option >信息分類</option> -->
<!-- 							<option  value="return">回覆</option> -->
<!-- 							<option  value="report">通知</option> -->
<!-- 							<option  value="option">系統</option> -->
<!-- 						</select> -->
<!-- 					<input type="text" name="title" id="title" placeholder="請輸入標題"> -->
<!-- 					</div> -->
<!--                     <div class="form-group"> -->
<!--                         <label for="content">編輯內容</label> -->
                       
<%--                         <textarea class="form-control" name="content" rows="5" id="content" value='${param.content}'></textarea> --%>
<%--                          <input type="hidden" name="memberid" class="form-control" id="id" value='${AdminOK.memberID}'>  --%>
<!--                                                                                      此處識別字串需要修改為本專案的AdminOK.memberid -->
<!--                          <input type="hidden" name="posttime" class="form-control" id="time"> -->
                         
<%--                       <font color='red'>${errorMsg.titleError}</font><br> --%>
<%--                       <font color='red'>${errorMsg.kindError}</font><br> --%>
<%--                      <font color='red'>${errorMsg.contentError}</font><br> --%>
<!--                      <button type="submit" class="btn btn-primary">送出</button> -->
<!--                     </div>           -->
<!--                 	</form> -->
<!--               </div> -->
<!--          	</div> -->
<!--          </div> -->
<!-- 	</div> -->
<!-- </section> -->
        
</body>
</html>