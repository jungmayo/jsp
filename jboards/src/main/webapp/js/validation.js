	//유효성 검사에 사용할 정규 표현식
	const reUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
	const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/;
	const reName  = /^[가-힣]{2,10}$/ 
	const reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
	const reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	const reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;
	
	//유효성 검사에 사용할 상태변수
	let isUidOk   = false;
	let isPassOk  = false;
	let isNameOk  = false;
	let isNickOk  = false;
	let isEmailOk = false;
	let isHpOk    = false;

window.onload = function(){
	const btnCheckUid = document.getElementById('btnCheckUid');
	const btnSendEmail = document.getElementById('btnSendEmail');
	const btnAuthEmail = document.getElementById('btnAuthEmail');
	const registerForm = document.getElementsByTagName('form')[0];
	const resultId = document.getElementsByClassName('resultId')[0];
	const resultPass = document.getElementsByClassName('resultPass')[0];
	const resultName = document.getElementsByClassName('resultName')[0];
	const resultNick = document.getElementsByClassName('resultNick')[0];
	const resultEmail = document.getElementsByClassName('resultEmail')[0];
	const auth = document.getElementsByClassName('auth')[0];
	const resultHp = document.getElementsByClassName('resultHp')[0];
	
	btnCheckUid.onclick = function(){
		
		const uid = registerForm.uid.value;
		
		// 1. 아이디 유효성 검사 
		if(!uid.match(reUid)){
			resultId.innerText = '아이디가 유효하지 않습니다.';
			resultId.style.color = 'red';
			return;
			
		}
		
		
		// 1-2. 중복 체크
		fetch('/jboards/user/checkUser.do?type=uid&value='+uid) //버튼을 눌렀을 때 해당주소로 uid를 전송
		.then(resp => resp.json())
		.then(data => {
			console.log(data);
			
			if(data.result>0){
				resultId.innerText = '이미 사용중인 아이디입니다.';
				resultId.style.color = 'red';
				isUidOk = false;
				
			}else{
				resultId.innerText = '사용 가능한 아이디입니다.';
				resultId.style.color = 'green';
				isUidOk = true;
			}
		})
		.catch(err => {
			console.log(err);
		});
		
	} //idonclick 끝
	
	
	//2. 비밀번호 유효성 검사
	registerForm.pass2.addEventListener('focusout' , function(){
		
		
		const pass1 = registerForm.pass1.value;
		const pass2 = registerForm.pass2.value;
		
		if(!pass1.match(rePass)){
			resultPass.innerText = "비밀번호가 유효하지 않습니다";
			resultPass.style.color = 'red';
			return;
		}
		
		if(pass1 == pass2){
			resultPass.innerText = "비밀번호가 일치 합니다"
				resultPass.style.color = 'green';
				isPassOk = true;
			
		}else{
			resultPass.innerText = "비밀번호가 일치하지 않습니다"
				resultPass.style.color = 'red';
				isPassOk = false;
			
		}
	});
	
	
	
	//3. 이름 유효성 검사
	registerForm.name.addEventListener('focusout' , function(){
		const name = registerForm.name.value;
		
		if(!name.match(reName)){
			resultName.innerText = "이름이 유효하지 않습니다."
			resultName.style.color = 'red';
			isNameOk = false;
		}else{
			resultName.innerText = "";
			isNameOk = true;
		}
		
	});
	
	
	//4. 별명 유효성 검사
	registerForm.nick.addEventListener('focusout' , function(){
		
		const nick = registerForm.nick.value;
		
		if(!nick.match(reNick)){
			resultNick.innerText = '별명이 유효하지 않습니다';
			resultNick.style.color = 'red';
			return;
			
		}
		
		fetch('/jboards/user/checkUser.do?type=nick&value='+nick) //type을 nick으로 추가해서 서버에 데이터를 전송
		.then(response => response.json())
		.then(data => {
			console.log(data);
			if(data.result>0){
				resultNick.innerText = '이미 사용중인 별명입니다.';
				resultNick.style.color = 'red';
				isNickOk = false;
			}else{
				resultNick.innerText = '사용 가능한 별명입니다.';
				resultNick.style.color = 'green';
				isNickOk = true;
			}
		})
		.catch(err => {
			console.log(err);
		});
		
	});
	
	//5. 이메일 유효성 검사
	
	let preventdblClick = false; 
	
	btnSendEmail.onclick = async function(){
		const email = registerForm.email.value;
		
		if(preventdblClick){ //이중클릭방지
			console.log('here1');
			return;
		}
		
		//이메일 유효성 검사
		if(!email.match(reEmail)){
			resultEmail.innerText = '유효한 이메일이 아닙니다.';
			resultEmail.style.color = 'red';
			return;
			
		}
		
		
		try{
			preventdblClick = true; //여기서 true로 만들어주기
			console.log('here2');
			
			
			const response = await fetch('/jboards/user/checkUser.do?type=email&value='+email); //await-> async 함수로
			const data = await response.json();
			console.log(data);
			if(data.result > 0){
				resultEmail.innerText = '이미 사용중인 이메일 입니다.';
				resultEmail.style.color = 'red';									
			}else{
				resultEmail.innerText = '이메일 인증코드를 확인하세요.'
				resultEmail.style.color = 'green';
				
				auth.style.display = 'block';
			}
			
		}catch(e){
			console.log(e);
		}
		
	};
	
	//인증코드 6자리 
	btnAuthEmail.onclick = function(){
		
		const code = registerForm.auth.value;
		
		fetch('/jboards/user/checkUser.do', {
			method : 'POST',
			body : JSON.stringify({"code" : code})
		})
		.then(resp => resp.json())
		.then(data => {
			console.log(data);
			
			if(data.result > 0){
				resultEmail.innerText = '이메일이 인증 되었습니다.';
				resultEmail.style.color = 'green';
				isEmailOk = true;
			}else{
				resultEmail.innerText = '유효한 인증코드가 아닙니다'
				resultEmail.style.color = 'red';
				isEmailOk = false;
			}
		})
		.catch(err => {
			console.log(err);
		});
	}
	
	
	//6. 핸드폰 유효성 검사
	registerForm.hp.addEventListener('focusout' , async function(){
		
		const hp = registerForm.hp.value;
		
		if(!hp.match(reHp)){
			resultHp.innerText = '유효하지 않은 휴대폰 번호입니다.';
			resultHp.style.color = 'red';
			
			return;
			
		}
		
		
		
		try{
		const response = await fetch('/jboards/user/checkUser.do?type=hp&value='+hp);
		const data = await response.json();
		
		console.log(data);
		
		if(data.result>0){
			resultHp.innerText = '이미 사용중인 휴대폰 번호입니다.';
			resultHp.style.color = 'red';
			isHpOk = false;
		}else{
			resultHp.innerText = '사용 가능한 휴대폰 번호입니다.'
			resultHp.style.color = 'green';
			isHpOk = true;
		}
		
		}catch(err){
			console.log(err);
		}
	});
	
	
	//최종 폼 전송 유효성 검사
	registerForm.onsubmit = function(){
		
		// 아이디 유효성 검사 완료 여부
			if(!isUidOk){
				alert('아이디가 유효하지 않습니다.');
				return false; //폼 전송 취소
			}
		// 비밀번호 유효성 검사 완료 여부
			if(!isPassOk){
				alert('비밀번호가 유효하지 않습니다.');
				return false; //폼 전송 취소
			}
		
		// 이름 유효성 검사 완료 여부
			if(!isNameOk){
				alert('이름이 유효하지 않습니다.');
				return false; //폼 전송 취소
			}
		// 닉네임 유효성 검사 완료 여부
			if(!isNickOk){
				alert('닉네임이 유효하지 않습니다.');
				return false; //폼 전송 취소
			}
		// 이메일 검사 완료 여부
			if(!isEmailOk){
				alert('이메일이 유효하지 않습니다.');
				return false; //폼 전송 취소
			}
		// 휴대폰 유효성 검사 완료 여부
			if(!isHpOk){
				alert('휴대폰번호가 유효하지 않습니다.');
				return false; //폼 전송 취소
			}
		return true; //폼 전송
	}
	
	
	
	
		
} //onload 끝