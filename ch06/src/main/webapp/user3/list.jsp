
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="user3.User3VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String host = "jdbc:mysql://127.0.0.1:3306/studydb";
String user = "root";
String pass = "1234";

List <User3VO> users = new ArrayList<>();

try{
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection(host,user,pass);
	String sql = "select * from `user3`";
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	
	while(rs.next()){
		User3VO vo = new User3VO();
		vo.setUid(rs.getString(1));
		vo.setName(rs.getString(2));
		vo.setBirth(rs.getString(3));
		vo.setHp(rs.getString(4));
		vo.setAddr(rs.getString(5));
		
		users.add(vo);
		
	}
	
	
}catch(Exception e){
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>user3</h3>
<a href="/ch06/user3/register.jsp">등록</a>
<table border="1">
	<tr>
		<td>아이디</td>
		<td>이름</td>
		<td>생년월일</td>
		<td>핸드폰 번호</td>
		<td>주소</td>
		<td>관리</td>
	</tr>
	<%for(User3VO us : users){ %>
	<tr>
			<td><%= us.getUid() %></td>
			<td><%= us.getName() %></td>
			<td><%= us.getBirth() %></td>
			<td><%= us.getHp() %></td>
			<td><%= us.getAddr() %></td>
			<td>
				<a href="/ch06/user3/modify.jsp?uid=<%=us.getUid()%>">수정</a>
				<a href="/ch06/user3/delete.jsp?uid=<%=us.getUid()%>" class="del">삭제</a>
			</td>
	</tr>
<%} %>
</table>
</body>
</html>