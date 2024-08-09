<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String seq = request.getParameter("seq");
String name = request.getParameter("name");
String gender = request.getParameter("gender");
String age = request.getParameter("age");
String addr = request.getParameter("addr");

String host = "jdbc:mysql://127.0.0.1:3306/studydb";
String user = "root";
String pass = "1234";
try {
	Connection conn =DriverManager.getConnection(host,user,pass);
	String sql = "INSERT INTO `user5` (`name`,`gender`,`age`,`addr`) values (?,?,?,?)";
	PreparedStatement psmt = conn.prepareStatement(sql);
	
	psmt.setString(1, name);
	psmt.setString(2, gender);
	psmt.setString(3, age);
	psmt.setString(4, addr);
	
	psmt.executeUpdate();
	
	psmt.close();
	conn.close();
	
}catch(Exception e){
	e.printStackTrace();
}

response.sendRedirect("/ch06/user5/list.jsp");

%>
