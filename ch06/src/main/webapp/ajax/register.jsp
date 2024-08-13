<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax::user1</title>
<script>
	window.onload = function(){
		const btnSubmit = document.getElementsByName('submit')[0];
		
		const formUser = document.getElementsByTagName('form')[0];
		
		btnSubmit.onclick = (e)=>{
			e.preventDefault();
			
			//json 데이터 생성
			const uid = formUser.uid.value;
			const name = formUser.name.value;
			const hp = formUser.hp.value;
			const age = formUser.age.value;
			const adr = formUser.adr.value;
			
			const jsonData = {
					"uid" : uid,
					"name" : name,
					"hp" : hp,
					"age" : age,
					"adr" : adr
			};
			console.log(jsonData);
			//서버 전송
			fetch('./registerProc.jsp', {
				method: 'POST',
				headers : {'Content-Type' : 'application/json'}, //클라이언트에서 서버로 가는 데이터
				body : JSON.stringify(jsonData)
			})
			.then(response => response.json())
			.then(data => {   //서버에서 결과 데이터 수신 (서버에서 보낸 데이터)
				console.log(jsonData);
				if(data.result > 0){
					alert('등록성공');
				}else{
					alert('등록실패');
				}
			}) 
			
			
			
			
			.catch(err => {console.log(err)});
			
		}
	}
</script>
</head>
<body>
	<h3>user1 등록</h3>
	<form action="#" method="post">
		<table border="1">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid"/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td>휴대폰</td>
				<td><input type="text" name="hp"/></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="text" name="age"/></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="adr"/></td>
			</tr>
			<tr>
				<td colspan="2" align ="right">
					<input type="submit" name="submit" value="등록하기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>