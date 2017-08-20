<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站內信</title>
<link rel="Shortcut icon" type="image/x-icon" href="images/Temmujiicon1.ico">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
<link href="../css/bootstrap.min.css" rel="stylesheet" >
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>


<style>
body{
/*  	background-color: #404764;  */
 	font-size: 16px;
 		background-image: url(../images/member_sendmail_bg.jpg);
 		background-size: cover;
}

h1,h2,h3,h4,h5,h6{
	font-family: 'Montserrat', sans-serif;
}

#section-content{
	
	margin-top: 150px;
}

.form-group label{
	font-size: 18px;
}

#div-form{
	background-color:#ffffff;
	border-radius: 20px;
	padding: 20px;
}

</style>
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<c:if test="${not empty memberLoginOK}">	      
			<ul class="nav navbar-nav">
				<li><a href="<c:url value="/courses/enroll.jsp"/>">Courses</a></li>
				<li><a href="<c:url value="/guide/trainerguide.jsp"/>">Coaches</a></li>
				<li><a href="<c:url value="/selectAllpost?action=postList2"/>">News</a></li>
				<li><a href="<c:url value="/blog/articles.jsp"/>">Blog</a></li>
				<li><a href="<c:url value="/group/create.jsp"/>">Groups</a></li> 
			</ul>
         	</c:if>
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
	                <li><a href="<c:url value="/membercenter/memberprofile.jsp" />">個人資料</a></li>		                     
	              </ul>
	            </li>
				<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>	
			</c:if>				
			</ul>
		</div>
		<!--/.nav-collapse -->
		</div>
	</nav>
	
<section id="section-content">
	<div class="container">
        <div class="row" id="content-row">
        	<div class="col-md-offset-3 col-md-6" id="div-form">
                <h1>站內信</h1> 
                <form action="<c:url value='/sendmailcontroller'/>" method="POST" >
                	<div class="form-group">
	                	<label for="title">標題</label>
	                	<input type="text" class="form-control" name="title" id="title" placeholder="title">
                	</div>
                    <div class="form-group">
	                    <select name="kind" class="form-control" >
							<option >分類</option>
							<option  value="回報">回報</option>
							<option  value="檢舉">檢舉</option>
							<option  value="意見">意見</option>
						</select>
					</div>
                    <div class="form-group">
                        <label for="content">內容</label>
                       
                        <textarea class="form-control" name="content" rows="5" id="content" value='${param.content}'></textarea>
                         <input type="hidden" name="memberid" class="form-control" id="id" value='${memberLoginOK.memberID}'>
                         <input type="hidden" name="posttime" class="form-control" id="time">
                         <button type="submit" class="btn btn-primary btn-lg" style="margin-top: 10px;">寄信</button>
	                     <font color='red'>${errorMsg.titleError}</font><br>
	                     <font color='red'>${errorMsg.kindError}</font><br>
	                     <font color='red'>${errorMsg.contentError}</font><br>
	                     
                    </div>
                </form>
        	</div>
    	</div>
	</div>
</section>
</body>
</html>