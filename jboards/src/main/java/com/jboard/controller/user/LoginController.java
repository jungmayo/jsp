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


@WebServlet("/user/login.do")
public class LoginController extends HttpServlet {
	

	private UserSerivce service = UserSerivce.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/login.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		
		UserDTO user = service.selectUser(uid);
	    if (user != null && pass.equals(user.getPass())) {
	        resp.sendRedirect("/jboards/article/list.do");
	    } else {
	        resp.sendRedirect("/jboards/user/login.jsp");
	    }
	}
}

	
	


