<%@page import="user1.User1VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.mysql.cj.xdevapi.PreparableStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ajax::user1</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<script>

		window.onload = function(){
			// 주소 파라미터 파싱(수정 아이디 추출하기)
			const params = location.href.split('?')[1];
			const value = params.split('=')[1];
			
			//문서객체 생성
			const form = document.getElementsByTagName('form')[0];
			const btnSubmit = form.submit;
			
			//수정하기 버튼 이벤트
			/* btnSubmit.onclick = () => {
				fetch('./proc/modifyProc.jsp')
				.then()
				.then(data => {
					if(data.result >0){
						alert('수정되었습니다.');
						location.href='../list.jsp'
					}
				})
				.catch();
			} */	
			btnSubmit.onclick = (e) => { //수정하기 버튼
				e.preventDefault();
				
				const jsonData = {
						"uid" : form.uid.value,
						"name" : form.name.value,
						"hp" : form.hp.value,
						"age" : form.age.value,
						"adr" : form.adr.value
						
				};
				
				// POST 전송 --> jquery ajax()는 getparameter() 수신				
				$.ajax({
					method: 'POST',
					url: './modifyProc.jsp',
					data: jsonData,
					success: function(data){
						console.log(data);
						
						if(data.result > 0){
							alert('수정 되었습니다.');
							location.href = '/ch06/ajax/user1/list.jsp';
						}
					}
				});
			}
			
			
			//수정 데이터 요청하기
			fetch('./getUser.jsp?uid='+value)
			.then(response => response.json())
			.then(data => {
				console.log(data);
				
				//수정 데이터 출력
				form.uid.value = data.uid;
				form.name.value = data.name;
				form.hp.value = data.hp;
				form.age.value = data.age;
				form.adr.value = data.adr;
			})
			.catch(err => {
				//console.log(err);
			}); 
			
			
		} //onload 끝
	
	</script>
</head>
<body>
	<h3>user59 수정</h3>
	

	<a href="../list.jsp">목록이동</a>
	
	<form action="#" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="tel" name="hp"/></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="number" name="age"/></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="adr"/></td>
			</tr>
			<tr>
				<td colspan="2" align ="right">
					<input type="submit" name="submit" value="수정하기"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>