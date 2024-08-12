<%@page import="sub1.FileVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%


List <FileVO> file = new ArrayList<>();
try{
	//1단계
	Context ctx = (Context) new InitialContext().lookup("java:comp/env");
	DataSource ds = (DataSource) ctx.lookup("jdbc/studydb");
	
	//2단계
	Connection conn = ds.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery("select * from `filetest`");
	
	while(rs.next()){
		FileVO vo = new FileVO();
		vo.setNo(rs.getInt(1));
		vo.setUid(rs.getString(2));
		vo.setName(rs.getString(3));
		vo.setOname(rs.getString(4));
		vo.setSname(rs.getString(5));
		vo.setRdate(rs.getString(6));
		
		file.add(vo);
		
	}
	conn.close();
	rs.close();
	stmt.close();
	
	
}catch(Exception e){
	e.printStackTrace();
	
}

%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>2.file Download</title>

	<!--
		날짜 : 2024.08.12
		이름 : 정지현
		내용 : 파일 다운로드 실습하기
	-->
</head>
<body>
	<h3>파일다운로드 테스트</h3>
	<table border = "1">
		<tr>
			<th>파일번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>원본파일명</th>
			<th>저장파일명</th>
			<th>등록일</th>
			<th>다운로드</th>
		</tr>
		
		<tr>
		
		<% for(FileVO vo : file){ %>
			<td><%=vo.getNo() %></td>	
			<td><%=vo.getUid() %></td>	
			<td><%=vo.getName() %></td>	
			<td><%=vo.getOname() %></td>	
			<td><%=vo.getSname() %></td>	
			<td><%=vo.getRdate().substring(0,10) %></td>	
			<td>
				<a href="./proc/fileDelete.jsp?no=<%=vo.getNo() %>&sname=<%=vo.getSname()%>">삭제</a>
				<a href=./proc/fileDownload.jsp?no=<%= vo.getNo() %>>다운로드</a>
			</td>	
		</tr>
	<% } %>
	</table>
</body>
</html>