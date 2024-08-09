<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String uid = request.getParameter("uid");

//데이터베이스 처리
String host = "jdbc:mysql://127.0.0.1:3306/studydb";
String user = "root";
String pass = "1234";

try{
	//1단계
	Class.forName("com.mysql.cj.jdbc.Driver");
	//2단계
	Connection conn = DriverManager.getConnection(host,user,pass);
	//3단계
	String sql = "delete from `user2` where `uid`=?";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, uid);
	psmt.executeUpdate();
	
	conn.close();
	psmt.close();
	
}catch(Exception e){
	e.printStackTrace();
}
	
	//목록 이동
		response.sendRedirect("/ch06/user2/list.jsp");
	%>
