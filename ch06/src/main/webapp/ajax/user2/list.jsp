<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
	window.onload = function(){
		const table = document.getElementsByTagName('table')[0]; //테이블 정의
	
	//사용자 데이터 요청후 테이블에 삽입작업
	fetch('./proc/getUsers.jsp') //해당 jsp로 데이터 요청
		.then(response => response.json()) // 데이터 응답
		.then(data => {
			console.log(data); //데이터를 받아옴
			
			for(const user of data){ //받아온 데이터로 for문을 돌려서 db의 모든 데이터를 조회할 예정
				let row = `<tr>
								<td>\${user.uid}</td>
								<td>\${user.name}</td>
								<td>\${user.birth}</td>
								<td>\${user.adrr}</td>
								<td>
									<a href ='#' class = 'modify'>수정</a>
									<a href ='#' class = 'delete'>삭제</a>
								</td>
								</tr>`
					table.insertAdjacentHTML('beforeend', row); //테이블에 insert하기, beforeend자리에 row의 값을 ( 키,벨류)
			}
		})
		.catch(err => {
			console.log(err);
		});
		
		//삭제 클릭했을 때(deleteProc에 요청한 정보를 받아와서 삭제할 예정)
		document.body.addEventListener('click' , function(e){
			if(e.target.classList.contains('delete')){ //만약 delete라는 클래스이름인 링크를 눌렀을 때
				e.preventDefault(); //a태그 클릭 막기
				
				const tr = e.target.closest('tr'); //해당 링크에 가장 가까운 tr을 의미
				const uid = tr.children[0].innerText; // tr의 첫번째 열의 내용(첫번째 td내용)
				
				fetch('./proc/deleteProc.jsp?uid='+uid)//uid를 실어서 전달
				.then(response => response.json()) //데이터 응답
				.then(data => { //받아온 데이터
					console.log(data);
					if(data.result>0){ //result 0 이상일때 데이터 값을 삭제
						alert('삭제완료'); 
						tr.remove(); // 행도 삭제
					}
				})
				.catch(err => {
					console.log(err);
				});
			
			
			
			} //if문 끝
			
			//수정 클릭 이벤트
			if(e.target.classList.contains('modify')){ //modify이름을 가진 링크를 클릭했을 경우
				e.preventDefault(); //a태그 방지
				const tr = e.target.closest('tr'); //누른 링크에 가장 가까이 있는 tr을 의미
				const uid = tr.children[0].innerText; // 그 tr의 첫번째 열의 내용을 uid에 담음
				location.href = '/ch06/ajax/user2/proc/modify.jsp?uid='+uid; //uid 정보를 가지고 modify에서 해결할 예정
			}

			
			
			
	
		}); //클릭이벤트 끝
	

	}//onload 끝

</script>
</head>
<body>
<h3>user2</h3>
	<a href = "/ch06/ajax/user2/register.jsp">등록하기</a>
	<table border ="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>생일</th>
			<th>주소</th>
			<th>관리</th>
		</tr>
	</table>
</body>
</html>