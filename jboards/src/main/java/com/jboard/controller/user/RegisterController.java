package com.jboard.controller.user;

import java.io.IOException;

import dto.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserSerivce;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet {

	private UserSerivce service = UserSerivce.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/register.jsp");
		dispatcher.forward(req, resp); //화면보여주는
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass1");
		String name = req.getParameter("name");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		String hp = req.getParameter("hp");
		String zip = req.getParameter("zip");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String regip = req.getRemoteAddr();
		
		
		//DTO 생성
		UserDTO userDto = new UserDTO();
		userDto.setUid(uid);
		userDto.setPass(pass);
		userDto.setName(name);
		userDto.setNick(nick);
		userDto.setEmail(email);
		userDto.setHp(hp);
		userDto.setZip(zip);
		userDto.setAddr1(addr1);
		userDto.setAddr2(addr2);
		userDto.setRegip(regip);
		

		
		service.insertUser(userDto);
		
		resp.sendRedirect("/jboards/user/login.do?success=300");
	}
	
	

}
