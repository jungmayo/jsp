<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>등록하기</h3>
	<a href="/ch06/user2/list.jsp">목록</a>
	
	<form action="/ch06/user2/registerProc.jsp" method="post">
	<table border ="1">
	
	<tr>
	<td>아이디</td>
	<td><input type="text" name="uid"></td>
	</tr>
	
	<tr>
	<td>이름</td>
	<td><input type="text" name="name"></td>
	</tr>
	
	<tr>
	<td>생년월일</td>
	<td><input type="text" name="birth"></td>
	</tr>
	
	<tr>
	<td>주소</td>
	<td><input type="text" name="adrr"></td>
	</tr>
	
	<tr>
	<td colspan="2" align ="right">
	<input type="submit" value="등록하기">
	</td>
	</tr>
	</table>
	</form>
</body>
</html>