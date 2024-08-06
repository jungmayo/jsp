<%@page import="sub1.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>5. session</title>
	
		<!-- 
		날짜 : 2024.08.06
		이름 : 정지현
		내용 : session 내장객체 실습하기
		-->
</head>
<body>
	<h3>5.session</h3>
	<h4>session ID 확인</h4> <!-- 나타나는 브라우저의 고유 쿠키값 -->
	<%= session.getId() %>
	
	<h4>session 설정</h4>
	<%
		String agent = request.getHeader("user-Agent"); //브라우저 정보
		
		if(agent.contains("Edg")){
			//edg 브라우저이면
			UserVO user = new UserVO("a101","김유신",23);
			
			//세션저장
			session.setAttribute("user", user);
			
		}else{
			//크롬 브라우저
			UserVO user = new UserVO("a102","김춘추",22);
			session.setAttribute("user", user);
		}
		
		// 세션 조회
		UserVO userVO = (UserVO)session.getAttribute("user");
	%>
	<p>
	 아이디 : <%= userVO.getUid() %><br>
	 이름 : <%= userVO.getName() %><br>
	 나이 : <%= userVO.getAge() %><br>
	</p>
</body>
</html>