<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>發表文章</title>
	<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
	<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <link href='../css/jquery.qtip.min.css' rel='stylesheet' />
    <link href="../css/bootstrap.min.css" rel="stylesheet" />
    <link href="../css/bootstrap-theme.min.css" rel="stylesheet" />
    <script src='../js/jquery.min.js'></script>
    <script src="../js/bootstrap.min.js"></script>


<style>
body{
	background-image: url(../images/article_bg_2048x1280.jpg);
	background-size: cover;
	font-family: 'Montserrat', sans-serif;
}


#section-header{
	margin-top: 100px;
}

#section-header h1{
	font-size:40px;
	font-weight: bold;
}


</style>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">	
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li style="margin-left: 500px;"><a class="navbar-brand" href="<c:url value="/trainercenter/trainer-welcome.jsp"/>">Temmujin Fitness</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi ${trainerLoginOK.trainerID } !</a>
	              <ul class="dropdown-menu">
	             	<li><a href="<c:url value="/trainercenter/opencourse.jsp"/>">New Course</a></li>
	                
	              </ul>
	            </li>
	            <li><a href="<c:url value="/LogoutServlet"/>">登出</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>
<section id="section-header">
	<div class="container">
        <div class="row">
        	<div class="col-md-offset-2 col-md-8">
        		<h1 style="color:#ffffff;">Post Articles</h1>
        	</div>
        </div>
    </div>
</section>
<section id="section-content">
	<div class="container">
        <div class="row">
        	<div class="col-md-offset-2 col-md-8" style="padding-top:20px;background-color: #ffffff; border-radius: 20px;">      
                <form action="<c:url value="/postArticle.do"/>" method="POST" >
                    <div class="form-group">
                        <label for="title" style="font-size: 20px;color:#002147;">Article Title</label>
                        <input type="text" class="form-control input-lg" name="title" id="title" placeholder="title">
                        <label for="content" style="font-size: 20px;color:#002147;">Content</label>
                        <textarea class="form-control input-lg" name="content" rows="5" id="content" value='${param.content}'></textarea>
                         <input type="hidden" name="trainerid" class="form-control" id="id" value='${trainerLoginOK.trainerID}'>
                         <input type="hidden" name="posttime" class="form-control" id="time">
                    </div>
                    	<button type="reset" class="btn btn-default btn-lg" style="margin-bottom: 20px;">Cancel</button>
                    	<button type="submit" class="btn btn-primary btn-lg" style="margin-bottom: 20px;">Submit</button>
                    	
<%--                      <font color='red'>${errorMsg.titleError}</font><br> --%>
<%--                      <font color='red'>${errorMsg.contentError}</font><br> --%>
                     
                </form>
            </div>
    	 </div>
	</div>
</section>

</body>
</html>