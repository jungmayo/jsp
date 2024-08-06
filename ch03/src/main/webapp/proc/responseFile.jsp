<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>response File</title>
</head>
<body>
	<h3>파일 다운로드</h3> <!--서버에서 클라이언트로  -->
	
	<%
		//해당 페이지를 브라우저에 출력하지 않고 파일 다운로드
		response.setHeader("Content-type", "application/octet-stream"); //부가정보 헤더 컨텐츠값이 어플이라서 다운로드로
	%>
</body>
</html>