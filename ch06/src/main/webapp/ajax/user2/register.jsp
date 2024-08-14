<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onload = function(){
		const btnSubmit = document.getElementsByName('submit')[0];
		const formUser = document.getElementsByTagName('form')[0]; // 문서객체생성
		
		
		btnSubmit.onclick = (e)=>{
			e.preventDefault(); // form제출 방지
			
			//json데이터생성
			const uid = formUser.uid.value;
			const name = formUser.name.value;
			const birth = formUser.birth.value;
			const adrr = formUser.adrr.value;
			
			const jsonData = { //받은 데이터를 jsonData에 저장, POST방식으로 보낼때 활용될 예정
					"uid" : uid,
					"name" : name,
					"birth" : birth,
					"adrr" : adrr
			};
			console.log(jsonData); //확인작업
			
			//서버전송 registerProc로 정보를 보내고 받아올 예정
			fetch('./registerProc.jsp', {
				method: 'POST',
				headers : {'Content-Type' : 'application/json'}, //클라이언트에서 서버로 가는 데이터
				body : JSON.stringify(jsonData) //반드시 JSON객체를 문자열로 변환
			})
			.then(response => response.json())
			.then(data => {   //서버에서 결과 데이터 수신 (서버에서 보낸 데이터) proc의 result데이터가 여기로 들어옴
				console.log(jsonData);
				if(data.result > 0){
					alert('등록성공');
				}else{
					alert('등록실패');
				}
			}) 
			
			.catch(err => {console.log(err)});
			
		}
	
		
	}//onload끝

</script>
</head>
<body>
<h3>ajax user2 등록</h3>
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
				<td>생일</td>
				<td><input type="text" name="birth"/></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="adrr"/></td>
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