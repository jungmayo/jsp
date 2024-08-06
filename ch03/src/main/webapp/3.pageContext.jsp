<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3.pageContext</title>
		<!-- 
		날짜 : 2024.08.06
		이름 : 정지현
		내용 : pageContext 내장객체 실습하기 (= 각각의 jsp파일 하나하나에 대한 객체)
		-->
</head>
<body>
	<h3>3.pageContext</h3>
	<h4>include</h4> <!-- 페이지에 페이지를 삽입 -->
	
	<%@ include file="./inc/header.jsp" %> <!-- 정적, include 되어있는 상태 -->
	
	<%
		// pagecontext 내장객체 ,프로그래밍적으로 동적 include
		pageContext.include("./inc/header.jsp");
		pageContext.include("./inc/footer.jsp");
		
	
	%>
	<h4>forward</h4> <!-- //forward는 서버 자원 내에서 제어권의 이동이기 때문에 타 서버 자원으로 이동이 안됨 -->
	<a href="./proc/forward1.jsp">포워드1</a>
	<a href="./proc/forward2.jsp">포워드2</a>
</body>
</html>