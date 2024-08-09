<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="user1.User1VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
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
String sql = "delete from `tb1user` where `uid`=?";
PreparedStatement psmt = conn.prepareStatement(sql);
psmt.setString(1,uid);
//4단계
psmt.executeUpdate();
//5단계

conn.close();
psmt.close();

}catch(Exception e){
	e.printStackTrace();
}
//목록 이동
	response.sendRedirect("/ch06/user1/list.jsp");

%>
