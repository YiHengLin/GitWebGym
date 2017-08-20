<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Post Report Done</title>
</head>
<body>

	<h1>
		<font color="Red">成功發佈公告 </font>
	</h1>

	<h2>
		<div>管理員 ${AdminOK.memberid} 公告已發佈</div>
		<br> 
		<br>
		<div>Attention：請管理員留意後續信息動態</div>
	</h2>
	
	<font color='BLUE'> 
<%-- 	${pageContext.servletContext.contextPath} 為 /Timmujin_02 --%>
<%-- 	<a href="${pageContext.servletContext.contextPath}/index.jsp">回首頁</a><br> --%>

        <input  type="button" value="回發佈公告" style="background-color:blue;color:white;border:5px #cccccc solid;" onclick="location.href='Post.jsp'">
		<input  type="button" value="回管理頁面" onclick="location.href='Admin.jsp'">
		
	</font>

</body>
</html>