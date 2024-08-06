<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>4.application</title>
		<!-- 
		날짜 : 2024.08.06
		이름 : 정지현
		내용 : application 내장객체 실습하기
		-->
</head>
<body>
	<h3>4. application</h3>
	<h4>서버 정보</h4>
	<%= application.getServerInfo() %>
	
	<h4>파라미터 정보</h4>
	<%
	
		String param1 = application.getInitParameter("param1");
		String param2 = application.getInitParameter("param2");
		//xml을 수정했기때문에 서버 재부팅해야함 / 최초로 생성된 정보를 출력하기 때문에 수정할때마다..
	%>
	<p>
		param1 : <%= param1 %><br>
		param2 : <%= param2 %><br>
	</p>
	
	<h4>자원 경로</h4>
	<%= application.getRealPath("./4.application.jsp") %>
</body>
</html>