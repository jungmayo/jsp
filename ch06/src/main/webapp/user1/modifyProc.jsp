<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//데이터베이스 수정처리
	
	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String age = request.getParameter("age");
	String adr = request.getParameter("adr");
	
	String host = "jdbc:mysql://127.0.0.1:3306/studydb";
	String user = "root";
	String pass = "1234";

	try {
	Connection conn = DriverManager.getConnection(host,user,pass);
	String sql = "Update `tb1user` set `name`=?,`hp`=?,`age`=?,`adr`=? where `uid`=?";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, name);
	psmt.setString(2, hp);
	psmt.setString(3, age);
	psmt.setString(4, adr);
	psmt.setString(5, uid);
	//3단계 - SQL실행
	psmt.executeUpdate();
	//4단계 - 결과처리(select일 경우)
	//5단계 - 데이터베이스 종료
	psmt.close();
	conn.close();
	
	
}catch(Exception e){
	e.printStackTrace();
}
//목록이동
response.sendRedirect("/ch06/user1/list.jsp");
%>
