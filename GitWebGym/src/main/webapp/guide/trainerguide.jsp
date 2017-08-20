<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Course Guide</title>
<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico">
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/notosanstc.css"> <!-- 中文字型:思源黑體 -->
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link  href="../css/font-awesome.min.css" rel="stylesheet">

<link href="../css/bootstrap.min.css" rel="stylesheet" />
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<style>

body{
	font-size: 16px;
	background-image: url(../images/signup_bg.jpg);  
   	background-size:cover;   
}


.img-circle{
	width:70%;
	height:70%;
}

#section-header{
	margin-top: 100px;
	margin-bottom: 50px;
	
}

h1,h2{
	
	font-weight:bold;
	font-family: 'Montserrat', sans-serif;
}
p{
	color:#ffffff;
	font-family: 'Montserrat', sans-serif;
}
h4{
	font-family: 'Noto Sans TC', sans-serif;
	font-weight: lighter;
}

.flex-container {
	background-color:#ffffff;
	border-radius:30px;
   display: flex;
   align-items: center;
   justify-content: center;
   flex-flow: row wrap;
/*    align-content: center; */
    width: 1300px;
    height: 1600px;
}

.flex-item {
	border-radius:20px;
    padding:10px;
    width: 300px;
    height: 400px;
    margin-left: 40px;
    margin-right: 50px;
    margin-bottom: 20px;
}



</style>
<script>
$(document).ready(function(){
	
	var contextPath = "${pageContext.request.contextPath}"
	
	$.get(contextPath+'/trainers',{},function(trainers){	
		
		trainers.map(function(trainer){


			
			
			var trainerid,flexitem=null
			var item = $("<div class='flex-item'></div>")
				
			$("#showtrainer").append(item)
			
			
			var photo = $("<div class='photo'></div>")
			
			trainerid = $("<h2></h2>").text(trainer.trainerid.toUpperCase())
			
			var img = $("<img class='img-circle'  />")  
			img.prop('src',contextPath+'/images/'+trainer.trainerid+'.jpg')
			
			var field = $("<div class='field'></div>")
			var exp = $("<div class='exp'></div>")
			var lisc = $("<div class='lisc'></div>")
			
			var fieldtxt = $("<h4></h4>").text("專長 : "+trainer.field)
			var experiencetxt = $("<h4></h4>").text("經歷 : "+trainer.experience)
			var licencetxt = $("<h4></h4>").text(("證照 : "+trainer.licence))
			
			field.append(fieldtxt)
			exp.append(experiencetxt)
			lisc.append(licencetxt)

			photo.append(img)
			item.append(trainerid).append(photo).append(field).append(exp).append(lisc)
			
		})
	})
})

</script>
</head>
<body>

	<!-- Fixed navbar -->
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
				<c:if test="${empty memberLoginOK && empty trainerLoginOK}">
					<li><a href="<c:url value="/login/login.jsp"/>">登入</a></li>
					<li><a href="<c:url value="/signup/signup.jsp"/>">註冊</a></li>	
				</c:if>
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
	
	
<section id="section-header">
	<div class="container">	
		<div class="row" style="text-align: center;">
			<h1 style="font-size: 60px; color:#ffffff;">Meet Our Coaches</h1>
			<p>Wer're out there. The ones who push you.</p>
		</div>
	</div>
	<div class="row">
		<div class="flex-container col-md-offset-2 col-md-8" id="showtrainer" ></div>
	</div>
</section>
</body>
</html>