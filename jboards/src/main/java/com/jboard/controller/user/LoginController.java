package com.jboard.controller.user;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserSerivce;


@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {
	

	private UserSerivce service = UserSerivce.INSTANCE;
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String success = req.getParameter("success"); //로그인 실패시 여기로 옴
		req.setAttribute("success", success);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/login.jsp");
		dispatcher.forward(req, resp);
		}
		
		
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		logger.debug("uid : " + uid + ", pass : " + pass);
		
		UserDTO user = service.selectUser(uid, pass);
		logger.debug("uid : " + uid);
		
		if(user != null) {
			//회원이 맞을 경우 -> 세션처리 후 리다이렉트
			//dopost req 객체로 가져옴
			HttpSession session = req.getSession();
			session.setAttribute("sessUser", user);
			
			resp.sendRedirect("/jboards/article/list.do");
		}else {
			//회원이 아닐 경우
			resp.sendRedirect("/jboards/user/login.do?success=100");
		}
		
	}
}

	
	


