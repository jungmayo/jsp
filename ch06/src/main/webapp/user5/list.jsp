<%@page import="user5.User5VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

//목록 전체보기//
String host = "jdbc:mysql://127.0.0.1:3306/studydb";
String user = "root";
String pass = "1234";

List <User5VO> users = new ArrayList<>();

try{
	Class.forName("com.mysql.cj.jdbc.Driver");
	//1단계
	Connection conn = DriverManager.getConnection(host,user,pass);
	//2단계
	String sql = "select * from `user5`";
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	while(rs.next()){
		User5VO vo = new User5VO();
		vo.setSeq(rs.getInt(1));
		vo.setName(rs.getString(2));
		vo.setGender(rs.getString(3));
		vo.setAge(rs.getInt(4));
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

	<script>
		window.onload = function(){
			const del = document.querySelectorAll('.del');
			
			//리스트에 삭제링크가 여러개이기 때문에 문서객체 리스트를 순회하면서 이벤트 처리
			//고전 문서객체 선택함수(getElement~ )는 forEach지원을 안함
			del.forEach(function(item){
				item.onclick = function(e){
					const result = confirm('정말 삭제 하시겠습니까?');
					
					if(!result){
						e.preventDefault(); // 삭제취소
					}
				}
			});
		}
	</script>
</head>
<body>
 <h3>user2 목록</h3>
 <a href="/ch06/user5/register.jsp">등록하기</a>
 
 <table border="1">
 <tr>
 <th>순서</th>
 <th>이름</th>
 <th>성별</th>
 <th>나이</th>
 <th>주소</th>
 <th>관리</th>
 </tr>
 
 
<% for(User5VO us : users){ %>
 	<tr>
	 	<td><%= us.getSeq()%></td>
 		<td><%= us.getName()%></td>
	 	<td><%= us.getGender()%></td>
	 	<td><%= us.getAge()%></td>
	 	<td><%= us.getAddr()%></td>
 		<td>
			<a href="/ch06/user5/modify.jsp?seq=<%=us.getSeq()%>">수정</a>
			<a href="/ch06/user5/delete.jsp?seq=<%=us.getSeq()%>" class="del">삭제</a>
		</td>
 	</tr>
 
 <% }%>

 
 </table>
 
</body>
</html>