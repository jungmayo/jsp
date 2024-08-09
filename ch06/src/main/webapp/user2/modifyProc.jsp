<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String uid = request.getParameter("uid");
String name = request.getParameter("name");
String birth = request.getParameter("birth");
String adrr = request.getParameter("adrr");

String host = "jdbc:mysql://127.0.0.1:3306/studydb";
String user = "root";
String pass = "1234";

try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	//1단계
	Connection conn = DriverManager.getConnection(host,user,pass);
	String sql = "Update `user2` set `name` =? , `birth`=? , `adrr` =? where `uid`=?";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, name);
	psmt.setString(2, birth);
	psmt.setString(3, adrr);
	psmt.setString(4, uid);
	
	psmt.executeUpdate();
	
	psmt.close();
	conn.close();
	
	
}catch(Exception e){
	e.printStackTrace();
	
}

response.sendRedirect("/ch06/user2/list.jsp");
%>
