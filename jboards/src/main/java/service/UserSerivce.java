package service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.UserDAO;
import dto.UserDTO;

public enum UserSerivce {
	
	INSTANCE;
	
	
	
	
	private UserDAO dao = UserDAO.getInstance();
	
	
	public String sendEmailCode(String email) {
		
		
		//인증코드 생성
		int code =ThreadLocalRandom.current().nextInt(100000, 1000000);
		
		
		//이메일 기본정보
		String title = "FarmStory 이메일 인증코드입니다.";
		String content = "<h1>인증코드는 " +code+ "입니다.</h1>";
		String sender = "wlgus5946@gmail.com";
		String appPass = "dqys begj jhxb ffhs"; //Google 앱 비밀번호
		
		// gmail SMTP 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		//gmail session 생성
		Session gmailSession = Session.getInstance(props, new Authenticator(){
			
			@Override // sender, appPass 반환
			protected javax.mail.PasswordAuthentication getPasswordAuthentication(){ //서버에 로그인시 사용자의 이메일 주소와 비밀번호를 제공하는 역할
				return new PasswordAuthentication(sender, appPass);
			}
		});
		
		//메일발송
		Message message = new MimeMessage(gmailSession);
		try{
			message.setFrom(new InternetAddress(sender, "FarmStory", "UTF-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=utf-8");
			
			Transport.send(message);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ""+code; //string으로 받기 위해서
	}
	public void insertUser(UserDTO dto) {
		dao.insertUser(dto);
	}
	public UserDTO selectUser(String uid, String pass) {
		return dao.selectUser(uid,pass);
	}
	public List<UserDTO> selectUsers() {
		return dao.selectUsers();
	}
	public void updateUser(UserDTO dto) {
		dao.updateUser(dto);
	}
	public void deleteUser(String uid) {
		dao.deleteUser(uid);
	}
	public int selectCountUser(String type, String value) {
		return dao.selectCountUser(type, value);
	}
	

}
