<%@page import="sub1.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	String auto = request.getParameter("auto");
	
	if(uid.equals("abc123") && pass.equals("1234")){
		
		//자동 로그인 처리
		if(auto != null){
			Cookie cookie = new Cookie("");
		}
		
		UserVO vo = new UserVO();
		vo.setUid("abc123");
		vo.setPass("1234");
		vo.setName("홍길동");
		vo.setAge(23);
		
		session.setAttribute("sessuser", vo);
		response.sendRedirect("./loginsuccess.jsp");
		
	}else {
		response.sendRedirect("./login.jsp?success=100");
	}
%>