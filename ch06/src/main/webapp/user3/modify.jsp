<%@page import="user3.User3VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String uid = request.getParameter("uid");
	
	String host = "jdbc:mysql://127.0.0.1:3306/studydb";
	String user = "root";
	String pass = "1234";
	
	User3VO vo = null;
	try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	//2단계
	Connection conn = DriverManager.getConnection(host,user,pass);
	String sql  = "select * from `user3` where `uid` =? ";
	PreparedStatement psmt = conn.prepareStatement(sql);
	psmt.setString(1, uid);
	ResultSet rs = psmt.executeQuery();
	
	if(rs.next()){
		vo = new User3VO();
		vo.setUid(rs.getString(1));
		vo.setName(rs.getString(2));
		vo.setBirth(rs.getString(3));
		vo.setHp(rs.getString(4));
		vo.setAddr(rs.getString(4));
	}
	
	rs.close();
	conn.close();
	psmt.close();
	
	
	}catch (Exception e){
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
	<form action="/ch06/user3/modifyProc.jsp" method = "Post">
		<table border = "1">
			<tr>
				<td>아이디</td>
				<td><input type = "text" name = "uid" value= "<%=vo.getUid() %>" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type = "text" name = "name" value= "<%=vo.getName() %>"/></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input type = "text" name = "birth" value= "<%=vo.getBirth() %>"/></td>
			</tr>
			<tr>
				<td>핸드폰</td>
				<td><input type = "text" name = "hp" value= "<%=vo.getHp() %>"/></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type = "text" name = "addr" value= "<%=vo.getAddr() %>"/></td>
			</tr>
			<tr>
				<td colspan="2" align ="right">
				<input type="submit" value="수정하기">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>