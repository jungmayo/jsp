<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>3.Loop</title>
	<%--
		날짜 : 2024.08.05
		이름 : 정지현
		내용 : 반복문 실습하기 
	--%>
</head>
<body>
	<h3>3.반복문</h3>
	<h4>for</h4>
	<%
	 for(int i=1; i<=3; i++){
		 out.println("<P>i : " + i + "</P>");	 
	 }
	%>
	<% 
		for(int j=1; j<=3; j++){
	%>
		<p>j : <%= j %> </p>	
	<% 
		}
	%>	
	
	
	<h4>while</h4>
		<%
		int k = 1;
		while(k <= 3) {
		%>
		<p>k : <%= k %></p>
		<%
			k++;
		}
		%>
	<h4>구구단</h4>
	<table border="1">
		<tr>
		<th>2단</th>
		<th>3단</th>
		<th>4단</th>
		<th>5단</th>
		<th>6단</th>
		<th>7단</th>
		<th>8단</th>
		<th>9단</th>
		</tr>
		
			<%for(int y=1; y<=9; y++){ %>
		<tr>
			<% for(int x=2; x<=9; x++){ %>
			<td><%= x %> X <%= y %> = <%= y*x %></td>
			<% } %>
		</tr>
		<% } %>
	</table>
		
		
		<% 
		int z = 0;
		for (int a=2; a<=9; a++){
		%>
		<%	for (int y=1; y<=9; y++){ %>
			<%z = a * y; %>
			<p>	<%=z %></p>
		<% } %>	
		<% }%>
		

</body>
</html>