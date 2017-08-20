<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member Profile</title>

<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css">
<link href="../css/bootstrap.min.css" rel="stylesheet" />	
<script src='../js/jquery.min.js'></script>
<script src="../js/bootstrap.min.js"></script>
<script>
	function fileViewer() {
		var theFiles = document.getElementById("file1").files;
		for (var i = 0; i < theFiles.length; i++) {
			var reader = new FileReader();
			var locHref = location.href;
			reader.readAsDataURL(theFiles[i]);

			reader.onload = function(e) {
				var fileContent = e.target.result;
					document.getElementById("pic").src = fileContent;

			}
		}
	}
</script>

</head>

<style>
body{
	background-image:url(../images/slide-img2.jpg);
	background-size:cover;
/* 	background: linear-gradient(to bottom right, #5073AB, #87A9D6); */
	font-size: 20px;
/* 	font-family: 'cwTeXYen', sans-serif; */
}
.nav{
	font-size: 16px;
}

#file1 {
	margin: 20px auto
}

.circular {
	width: 300px;
	height: 300px;
	border-radius: 150px;
}

#photo img {
	display: block;
	margin: 20px auto;
}

#section-header{
	
	margin-top: 100px;
	
}

</style>

<body>
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
	                <li><a href="<c:url value="/membercenter/sendmail.jsp" />">站內信</a></li>   	                                   
	              </ul>
	            </li>
				<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>		
			</c:if>	
		</ul>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>
	
<section id="section-header">
	<div class="container">
		<div class="row"> 
			<form class="form-horizontal" id="trainerprofile"
				action="<c:url value="/modifyServlet.do"/>" method="post"
				enctype="multipart/form-data">
				<div class="col-sm-offset-2 col-md-8" style="background-color: #ffffff;border-radius:50px;">
					<div id="photo">
						<img id="pic" width="150" height="150" class="img-circle"
							src="${pageContext.request.contextPath}/misc/showmemberphoto?id=${memberLoginOK.memberID}">
							<input type="file" id="file1" name="m_photo" multiple
							accept="image/*" onchange="fileViewer()" >

					</div>
					<input type="text" name="m_id_number" id="m_id_number"
						value="${memberLoginOK.m_id_number}" hidden="hidden"> <input
						type="text" name="m_gender" id="m_gender"
						value="${memberLoginOK.m_gender}" hidden="hidden"> <input
						type="text" name="m_register" id="m_register"
						value="${memberLoginOK.m_Regi}" hidden="hidden"> <input
						type="text" name="m_identity" id="m_identity"
						value="${memberLoginOK.m_iden}" hidden="hidden"> <input
						type="text" name="is_blacklist" id="is_blacklist"
						value="${memberLoginOK.blist}" hidden="hidden">

					<div class="form-group">
						<label for="memberid" class="col-sm-2 control-label">帳號</label>
						<div class="col-sm-8 input-group">
							<input type="text" class="form-control" id="memberid"
								name="memberid" value="${memberLoginOK.memberID}">
						</div>
						<div style="color: red; font-size: 60%; display: inline">
							${ErrorMsg.id}</div>
					</div>
                
					<div class="form-group">
						<label for="m_name" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-8 input-group">
							<input type="text" class="form-control" id="m_name" name="m_name"
								value="${memberLoginOK.m_name}">
						</div>
						<span style="color: red; font-size: 60%; display: inline">
							${ErrorMsg.name}</span>
					</div>
					<div class="form-group">
						<label for="m_mail" class="col-sm-2 control-label">信箱</label>
						<div class="col-sm-8 input-group">
							<input type="email" class="form-control" id="m_mail"
								name="m_mail" value="${memberLoginOK.m_mail}">
						</div>
						<div style="color: red; font-size: 60%; display: inline">
							${ErrorMsg.email}</div>
					</div>
					<div class="form-group">
						<label for="m_password" class="col-sm-2 control-label">密碼</label>
						<div class="col-sm-8 input-group">
							<input type="password" class="form-control" id="m_password"
								name="m_password" value="${memberLoginOK.m_password}">
						</div>
						<div style="color: red; font-size: 60%; display: inline">
							${ErrorMsg.password}</div>
					</div>
					<div class="form-group">
						<label for="m_bday" class="col-sm-2 control-label">生日</label>
						<div class="col-sm-8 input-group">
							<input type="text" class="form-control" id="m_bday" name="m_bday"
								value="${memberLoginOK.m_bday}">
						</div>
						<div style="color: red; font-size: 60%; display: inline">
							${ErrorMsg.date}</div>
					</div>

					<div class="form-group">
						<label for="m_mobile" class="col-sm-2 control-label">手機</label>
						<div class="col-sm-8 input-group">
							<input type="text" class="form-control" id="m_mobile"
								name="m_mobile" value="${memberLoginOK.m_mobile}">

						</div>
						<div style="color: red; font-size: 60%; display: inline">
							${ErrorMsg.phone}</div>
					</div>

					<div class="form-group">
						<label for="m_tel" class="col-sm-2 control-label">電話</label>
						<div class="col-sm-8 input-group">
							<input type="text" class="form-control" id="m_tel" name="m_tel"
								value="${memberLoginOK.m_tel}">
						</div>
					</div>

					<div class="form-group">
						<label for="m_address" class="col-sm-2 control-label">地址</label>
						<div class="col-sm-8 input-group">
							<input type="text" class="form-control" id="m_address"
								name="m_address" value="${memberLoginOK.m_addr}">

						</div>
						<div style="color: red; font-size: 60%; display: inline">
							${ErrorMsg.address}</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-5 col-sm-10">

							<button type="reset" class="btn btn-defalut btn-lg">取消</button>
							<button type="submit" class="btn btn-primary btn-lg">修改</button>
							   

						</div>
					</div>
			</form>

		</div>
	</div>

	</div>
</section>

</body>
</html>