package com.jboard.controller.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserSerivce;

@WebServlet("/user/checkUser.do")
public class checkUserController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserSerivce service = UserSerivce.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 수신 (입력한 정보가 뭔지)
		String type = req.getParameter("type");
		String value = req.getParameter("value");
		//String nick = req.getParameter("nick");
		//System.out.println(uid);
		logger.debug("value : " + value);
		logger.debug("type : " + type);
		//logger.debug("uid : " + uid);
		//logger.debug("nick : " + nick);
		
		
		if(type.equals("email")) {
			//이메일 인증번호 발송하기
		}
		
		
		//정보들 조회하기
		int result = service.selectCountUser(type,value);
		
		
		if(type.equals("email")&& result == 0) {
			
			//이메일 인증번호 발송하기
			String code = service.sendEmailCode(value); //서버에서 전송한 code를 받아옴 
			
			//세션 저장 (각 클라이언트마다 인증번호가 다르기 때문에 세션에 저장해야 함)
			HttpSession session = req.getSession();
			session.setAttribute("authCode", code);
		}
		
		
		
		//JSON으로 만들어야함
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		//json 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//JSON을 수신하는 방법
		BufferedReader reader = req.getReader();
		StringBuilder requestBody = new StringBuilder();
		
		String line; //bufferdReader가 한줄씩 읽어온 데이터를 저장하는데 사용됨
		while((line = reader.readLine()) != null){
			requestBody.append(line);
		}
		reader.close();
		
		//JSON 파싱
		Gson gson = new Gson();
		Properties prop = gson.fromJson(requestBody.toString(), Properties.class); //JSON문자열을 프로퍼티 객체로 변환
		String code = prop.getProperty("code"); //입력한 인증코드
		logger.debug(code);
		
		//인증코드 일치여부 확인
		HttpSession session = req.getSession();
		String authCode = (String)session.getAttribute("authCode"); //서버에서 생성한 인증코드
		
		JsonObject json = new JsonObject();
		if(authCode.equals(code)) {
			//JSON으로 받아서 다시 JSON으로 보내야 함
				json.addProperty("result", 1);
		}else {
				json.addProperty("result", 0);
			
		}
		PrintWriter writer = resp.getWriter();
		writer.print(json);
		
		
		
	}
	
}
