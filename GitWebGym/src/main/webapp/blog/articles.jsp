<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教練分享</title>

<jsp:useBean id='post' class='article.model.ArticleDAO' />
<jsp:useBean id='reply' class='comment.model.commDAO' />



<link rel="Shortcut icon" type="image/x-icon" href="../images/Temmujiicon1.ico"> 
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/notosanstc.css"> <!-- 中文字型:思源黑體 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/cwtexyen.css">  <!-- 中文字型:園體 -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href="../css/bootstrap.min.css" rel="stylesheet" >
<script src="../js/jquery.min.js"></script>

<script src="../js/bootstrap.min.js"></script>

<style>

body{
 	background-image: url(../images/articles_bg.jpg);  
   	background-attachment: scroll;
	font-size: 16px;
}

h2{
	line-height: 1.5;
}

#section-header{

	margin-top: 50px;
}


#header-title{
	font-family: 'cwTeXYen', sans-serif; 
	color:#4B4C50;
	font-size: 60px;
}

.btn-group-justified {
	margin-top: 10px;
	margin-bottom: 10px;
	font-
}

.row {
	
	margin-top: 20px;
	margin-bottom: 20px;
}

.feed {
	background-color: #ffffff;
}




.feedheader {
	margin-top: 10px;
	padding:10px;
	
}

.div-trainer-img{
 	
	display: inline-block;
	width: 150x;
    height: 150px;
}

.title{
	display: inline-block;
	width: 70%;
    height: 100%;
    font-family: 'cwTeXYen', sans-serif;
    
    font-style: italic;
    line-height: 200%;
    margin-left: 10px;

}

.pubdate{
	margin:10px 0;
	font-size: 10px;
	font-weight: lighter;
	color:lightgrey;
}

.feedcontent {
	margin-top: 10px;
	font-size: 16px;
}

.content{
	font-family: 'Noto Sans TC', sans-serif;
	font-weight:lighter;
	font-size: 20px;
	text-align:justify;
	line-height: 160%;
}

.div-member-img{
	display: inline-block;
	width: 30x;
    height: 30px;
}
.commentcontent{	
	display: inline-block;
}



</style>
<script type="text/javascript">
function confirmDelete(n) {
	if (confirm("確定刪除留言 ? ") ) {
		document.forms[0].action="<c:url value='/comment.do?act=del&commid=" + n +"' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();
		
	} 
	
}


function PostComm(key,id,index) {
    var x="inputcomm"+key
    
	var comm =document.getElementById(x).value;

	    document.forms[0].action="<c:url value='/comment.do?act=post&ArticleNo=" + key +"&comm="+comm+"&memberId="+index+ "' />" ;
	    document.forms[0].method="POST";
	    document.forms[0].submit();
		
	
}


function modifyComm(key2,index,id){
	console.log("modifyComm key2="+key2)
	  var x='txt'+key2;
	  var y='update'+key2;
	  var comm =document.getElementById(x).value;
	  if( document.getElementById(y).firstChild.nodeValue=="修改"){
	  document.getElementById(x).disabled=false;
	  document.getElementById(y).firstChild.nodeValue="更新";
	  }else if(document.getElementById(y).firstChild.nodeValue=="更新"){
	  
		  
		document.forms[0].action="<c:url value='/comment.do?act=update&commid=" + key2 +"&ArticleNo="+index+"&memberId="+id+"&comm="+comm+ "' />" ;
		document.forms[0].method="POST";
		document.forms[0].submit();	  
	  	  
	  document.getElementById(x).disabled=true;
	  document.getElementById(y).firstChild.nodeValue="修改";
	  }
	}
</script>

</head>
<body>

<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
		<div class="navbar-header">
		<c:if test="${not empty trainerLoginOK }">
			<a class="navbar-brand" href="<c:url value="/trainercenter/trainer-welcome.jsp"/>">Temmujin Fitness</a>
		</c:if>
		<c:if test="${empty trainerLoginOK }">
			<a class="navbar-brand" href="<c:url value="/index.jsp"/>">Temmujin Fitness</a>
		</c:if>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:if test="${empty trainerLoginOK}">  
				<li><a href="<c:url value="/courses/enroll.jsp"/>">Courses</a></li>
				<li><a href="<c:url value="/guide/trainerguide.jsp"/>">Coaches</a></li>
				<li><a href="<c:url value="/selectAllpost?action=postList2"/>">News</a></li>
				<li><a href="<c:url value="/blog/articles.jsp"/>">Blog</a></li>
				<li><a href="<c:url value="/group/create.jsp"/>">Groups</a></li> 
				</c:if>
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
	 	             	<li><a href="<c:url value="/membercenter/sendmail.jsp" />">站內信</a></li>   	 
		                <li><a href="<c:url value="/membercenter/memberprofile.jsp" />">個人資料</a></li>		                     
		              </ul>
		            </li>
					<li><a href="<c:url value="/LogoutServlet"/>">Logout</a></li>	
				</c:if>	
				<c:if test="${not empty trainerLoginOK}">   
					<li class="dropdown">
			              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Hi ${trainerLoginOK.trainerID } !</a>
			              <ul class="dropdown-menu">	
			              	<li><a href="<c:url value="/trainercenter/opencourse.jsp"/>">New Course</a></li>             
			                <li><a href="<c:url value="/blog/postarticle.jsp"/>">Post Article</a></li>
			              </ul>
			            </li>
			        <li><a href="<c:url value="/LogoutServlet"/>">登出</a></li>
				</c:if>  
			</ul>

		</div>
		</div>
	</nav>
<section id="section-header">
	<div class="container">
		<div class="row" style="text-align: center;">
				<h1 id="header-title">教練分享</h1>
			
		</div>
	</div>
</section>
<section id="section-article">
	<div class="container">			
		<c:forEach var='mb' items='${post.aritcle}' varStatus='vs'> 
			<div class="row">
				<div class="feed col-md-offset-2 col-md-8">						
					<div class="feedheader">
						<div class="div-trainer-img">
							<img class='trainer-img' style="height: 150px; width: 150px;"
								src="${pageContext.servletContext.contextPath}/images/${mb.trainId}.jpg">				
						</div>
						<div class="title">
							<h1>${mb.title}<h1>
						</div>
					</div>
		

					<div class="feedcontent">
						<div class="content"> 
							${mb.content}  <%--文章內容 --%>
						</div>
						<div class="pubdate"> 
							${mb.pubdate}<%-- 刊登日期--%>
						</div>
						<c:forEach var='cb' items='${reply.commemts}' varStatus='vs2'><%-- 由usebean使用留言dao並抓取留言table所有屬性 --%>
							<c:if test="${cb.articleNo==mb.articleNo}"><%--查詢文章table文章no和留言table的文章no一樣的留言--%>
								<div class="comment  row">
								 	<div class="div-member-img">
<%-- 										<img style="height: 40px; width: 40px;margin-left: 15px;"  src='${pageContext.servletContext.contextPath}/misc/showmemberphoto?id=${cb.memberId}'> --%>
										<img style="height: 40px; width: 40px;margin-left: 15px;"  src='${pageContext.servletContext.contextPath}/images/${cb.memberId}.jpg'>
									</div>
						            <div class="commentcontent col-md-8">
										<input type="text" class="form-control" id="txt${cb.commNo}" value="${cb.commcontet}" disabled=true ><%--留言內容 設定input的id為留言no --%>
									</div>
									<c:if test="${memberLoginOK.memberID==cb.memberId}"><%-- 如果登入帳號和留言帳號一樣才會出現修改和刪除--%>
										<span>
										<button type="button" name="delete"  class="btn btn-danger" onClick="confirmDelete(${cb.commNo})">刪除</button>
										<button type="button" id="update${cb.commNo}" class="btn btn-primary" onClick="modifyComm(${cb.commNo},${mb.articleNo},'${cb.memberId}')">修改</button>
										<%--  <Input type="button" name="delete" value="刪除" onClick="confirmDelete(${cb.commNo})" > --%>					
										<%--  <Input type="button" id="update${cb.commNo}" value="修改" onClick="modifyComm(${cb.commNo},${mb.articleNo},'${cb.memberId}')"></span>										</span> --%>
							       </c:if>
						       </div>			  
							</c:if>    
						</c:forEach>
						<div class="row">
							<c:if test="${not empty memberLoginOK}">
								<div class="col-md-8">
									<input type="text" class="form-control" id="inputcomm${mb.articleNo}"  name="inputcomm" placeholder="留言...."><%--留言內容  input的id為文章no class="form-control"會使得留言鈕無法接在留言input後 --%>
								</div>
								<div class="col-md-2" >
									<button type="submit" class="btn btn-default" style="float: left;margin-left: 0px;" onClick="PostComm(${mb.articleNo},'${mb.trainId}','${memberLoginOK.memberID}')" style="float:left;">留言</button><%--設定留言鈕  傳入文章編號 教練和會員編號 --%>	
								</div>
							</c:if>
						</div>

					</div>
				</div>
			</div>
			<hr>
		</c:forEach>	
	</div>
	<form>
		<input type="hidden" name="a" />
	</form>
</section>

</body>
</html>