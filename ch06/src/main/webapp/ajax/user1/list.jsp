<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax::user1</title>
	<script>
		window.onload = function(){
			
			const table = document.getElementsByTagName('table')[0];
			
			//사용자 데이터 요청
			fetch('./proc/getUsers.jsp')
			.then(response=>response.json())
			.then(data => {
				console.log(data);
				
				for(const user of data){
				let row = `<tr> 
							<td>\${user.uid}</td>
							<td>\${user.name}</td>
							<td>\${user.hp}</td>
							<td>\${user.age}</td>
							<td>\${user.adr}</td>
							<td>
								<a href ='#' class = 'modify'>수정</a>
								<a href ='#' class = 'delete'>삭제</a>
							</td>
							</tr>`
							table.insertAdjacentHTML('beforeend', row);
					
				}
			
			})				
			.catch(err => {
				console.log(err);
			});
		//삭제 클릭 이벤트(동적 이벤트 연결)
		document.body.addEventListener('click' , function(e){
			
			if(e.target.classList.contains('delete')){ //a태그 클래스이름
				e.preventDefault(); //a태그 클릭 삭제
				//alert('삭제!');
				
				//화면에서 동적 삭제
				const tr = e.target.closest('tr'); //target은 삭제버튼의 상위에 가장 가까이있는 tr을 의미
				const uid = tr.children[0].innerText;
	
				
				//삭제요청
				fetch('./proc/deleteProc.jsp?uid='+uid)
				.then(response => response.json())
				.then(data => {
					console.log(data);
					if(data.result>0){
						alert('삭제했습니다.');
						tr.remove();
					}
				})
				.catch(err => {
					console.log(err);
				});
			
			}
			
			//수정 클릭 이벤트
			if(e.target.classList.contains('modify')){
				e.preventDefault();
				const tr = e.target.closest('tr'); //target은 삭제버튼의 상위에 가장 가까이있는 tr을 의미
				const uid = tr.children[0].innerText;
				location.href = '/ch06/ajax/user1/proc/modify.jsp?uid='+uid;
			}
		
			
		});
		
		
		
		}//onload 끝
	
	</script>
</head>
<body>
	<h3>user18</h3>
	<a href = "/ch06/ajax/user1/register.jsp">등록하기</a>
	<table border ="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>휴대폰</th>
			<th>나이</th>
			<th>주소</th>
			<th>관리</th>
		</tr>
		
	</table>
</body>
</html>