<%@page import="sub1.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 UserVO sessuser = (UserVO) session.getAttribute("sessuser");   
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>로그인 성공</h3>
	<p>
		<%=sessuser.getName()%>님 반갑습니다.<br>
		<a href="#">로그아웃</a>
	
	</p>
</body>
</html>