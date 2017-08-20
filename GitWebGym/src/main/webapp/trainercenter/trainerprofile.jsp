<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trainer Profile</title>

	<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
    <link href='../css/jquery.qtip.min.css' rel='stylesheet' />
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet" />
    <link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <script src='../js/jquery.min.js'></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<script>

$(document).ready(function(){
	
	
	  $('#trainerprofile').on('submit', function(e){
		  var that = $(this)
		  console.log($(that).serialize())
		  
		  $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/cudtrainers',
				data: $(that).serialize(),	
			}).done(function(data) {
				console.log("success");	
				$("#msg-modal").modal()
			})		
	  })
})
</script>
<style>


body{
	font-size: 14px;
	background-color: #7698B1;
}

h1,h2,h3,h4,h5,h6{
	font-family: 'Montserrat', sans-serif;
}

#section-header{
	margin-top: 70px;
}

.circular {
	width: 300px;
	height: 300px;
	border-radius: 150px;
	-webkit-border-radius: 150px;
	-moz-border-radius: 150px;
}

#photo img{
	display: block;
	margin:20px auto;
}

label{
	color:#ffffff;
}

</style>

<body>
	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/trainercenter/trainer-welcome.jsp"/>">Temmujin Fitness</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
		<c:if test="${not empty trainerLoginOK || not empty memberLoginOK}">
			<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
		              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi ${trainerLoginOK.trainerID } !</a>
		              <ul class="dropdown-menu">
		                <li><a href="<c:url value="/trainercenter/opencourse.jsp"/>">New Course</a></li>
		                <li><a href="<c:url value="/blog/postarticle.jsp"/>">Post Article</a></li>
		              </ul>
		            </li>
				<li><a href="<c:url value="/LogoutServlet"/>">登出</a></li>
			</ul>
		</c:if>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>
	
<section id="section-header">
	<div class="container">
		<div class="row" style="margin-bottom: 10px;">
        	<div style="font-weight:bold;font-style:italic;text-align: center">
          		<h1 style="color:#ffffff;font-size: 30px;">Profile</h1>
       		</div>
		</div>
	</div>
</section>
<section id="section-profile">
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div id="photo" > 
					<img width="250" height="250" class="img-circle"
						src="${pageContext.request.contextPath}/misc/showtrainerphoto?id=${trainerLoginOK.trainerID}">
				</div>
				<form class="form-horizontal" id="trainerprofile" >
					
					<input type="text" name="t_id_number" id="t_id_number" value="${trainerLoginOK.t_id_number}"  hidden="hidden">
					<input type="text" name="t_gender" id="t_gender" value="${trainerLoginOK.t_gender}"  hidden="hidden">
					<input type="text" name="t_register" id="t_register" value="${trainerLoginOK.t_register}" hidden="hidden">
            		<input type="text" name="t_identity" id="t_identity" value="${trainerLoginOK.t_identity}"  hidden="hidden">	
            		<input type="text" name="is_blacklist" id="is_blacklist" value="${trainerLoginOK.is_blacklist}"  hidden="hidden">
	
					
					<div class="form-group">
						<label for="t_name" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-10 input-group">
							<input type="text" class="form-control" id="t_name" name="t_name" value="${trainerLoginOK.t_name}" placeholder="name" >
						</div>
					</div>
					<div class="form-group">
						<label for="t_mail" class="col-sm-2 control-label">信箱</label>
						<div class="col-sm-10 input-group">
							<input type="email" class="form-control" id="t_mail" name="t_mail" placeholder="Email" value="${trainerLoginOK.t_mail}" placeholder="email" >
						</div>
					</div>
					<div class="form-group">
						<label for="t_password" class="col-sm-2 control-label">密碼</label>
						<div class="col-sm-10 input-group">
							<input type="password" class="form-control" id="t_password" name="t_password" value="${trainerLoginOK.t_password}" placeholder="password" >
						</div>
					</div>
					<div class="form-group">
						<label for="t_bday" class="col-sm-2 control-label">生日</label>
						<div class="col-sm-10 input-group">
							<input type="text" class="form-control" id="t_bday" name="t_bday" value="${trainerLoginOK.t_bday}" placeholder="yyyy-MM-dd" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="t_mobile" class="col-sm-2 control-label">手機</label>
						<div class="col-sm-10 input-group">
							<input type="text" class="form-control" id="t_mobile" name="t_mobile" value="${trainerLoginOK.t_mobile}" placeholder="mobile" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="t_tel" class="col-sm-2 control-label">電話</label>
						<div class="col-sm-10 input-group">
							<input type="text" class="form-control" id="t_tel" name="t_tel" value="${trainerLoginOK.t_tel}" placeholder="tel" >
						</div>
					</div>

					<div class="form-group">
						<label for="t_address" class="col-sm-2 control-label">地址</label>
						<div class="col-sm-10 input-group">
							<input type="text" class="form-control" id="t_address" name="t_address" value="${trainerLoginOK.t_address}" placeholder="address" >
						</div>
					</div>		
					
					<div class="form-group">
						<label for="t_field" class="col-sm-2 control-label">專業</label>
						<div class="col-sm-10 input-group">
							<input type="text" class="form-control" id="t_field" name="t_field" value="${trainerLoginOK.t_field}" placeholder="專業領域" >
						</div>
					</div>		
					<div class="form-group">
						<label for="t_experience" class="col-sm-2 control-label">經歷</label>
						<div class="col-sm-10 input-group">
							<input type="text" class="form-control" id="t_experience" name="t_experience" value="${trainerLoginOK.t_experience}" placeholder="經歷" >
						</div>
					</div>		
					
					<div class="form-group">
						<label for="t_licence" class="col-sm-2 control-label">證照</label>
						<div class="col-sm-10 input-group">
							<input type="text" class="form-control" id="t_licence" name="t_licence" value="${trainerLoginOK.t_licence}" placeholder="證照" >
<!-- 							<span class="input-group-btn"> -->
<!-- 									<button type="button" class="btn btn-primary btn-md"><span class="glyphicon glyphicon-pencil"></span></button> -->
<!-- 							</span> -->
						</div>
					</div>										
					<div class="form-group">
						<div class="col-md-offset-5 col-md-8" >
							
							<button type="reset" class="btn btn-warning">取消</button>
							<button type="submit" class="btn btn-primary">修改</button>
							
						</div>
					</div>
				</form>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
</section>

	<!-- CRUD Message 視窗 -->
	<div class="modal fade" id="msg-modal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">訊息</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<H4 id="msg-content"></H4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">關閉</button>
				</div>
			</div>
		</div>
	</div>
	<script src="../js/smoothscroll.js"></script>
	<script src="../js/wow.min.js"></script>
</body>
</html>