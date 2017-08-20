<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>課程更新成功</h1>

<%-- <a href="<c:url value="/EventController?action=ItemListAll"/>">回到課程列表</a> --%>

<input type="button" value="回到課程列表" onclick="location.href='<c:url value='/EventController?action=ItemListAll'/>'">

<%-- <a href="<c:url value="/Admin.jsp"/>">回到管理頁面</a> --%>

<input type="button" value="回管理頁面" onclick="location.href='Admin.jsp'">

</body>
</html>