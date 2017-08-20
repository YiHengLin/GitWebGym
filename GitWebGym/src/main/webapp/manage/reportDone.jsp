<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Mail Report Done</title>
</head>
<body>

	<h1>
		<font color="Red">成功送出信息  </font>
	</h1>
<%-- 	<div>感謝會員${memberLoginOK.memberID}的來信</div> --%>
<h2>
	<div>管理員 ${AdminOK.memberid} 站內信息已送出</div>
	<br>
	<br>
	<div>Attention：請管理員留意後續信息動態</div>
</h2>
	<font color='BLUE'> 
<%-- 	${pageContext.servletContext.contextPath} 為 /Timmujin_02 --%>
<%-- 	<a href="${pageContext.servletContext.contextPath}/index.jsp">回首頁</a><br> --%>

        <input  type="button" value="回站內信息" style="background-color:blue;color:white;border:5px #cccccc solid;" onclick="location.href='sendmail.jsp'">
		<input  type="button" value="回管理頁面" onclick="location.href='Admin.jsp'">
		
	</font>

    
</body>
</html>