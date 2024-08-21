package com.jboard.filter;

import java.io.IOException;

import dto.UserDTO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/article/list.do", "/article/write.do", "/article/view.do", "/article/modify.do"})
public class LoginCheckForArticleFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		//로그인여부확인
		HttpServletRequest req = (HttpServletRequest) arg0;
		
		
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		//로그인을 하지 않으면 로그인 페이지로 이동
		if(sessUser == null) {
			//게시판 목록 이동
			HttpServletResponse resp = (HttpServletResponse) arg1;
			resp.sendRedirect("/jboards/user/login.do?success=102");
		
		//로그인 상태면
		}else {
			//로그인 상태이면 다음 필터로 이동(controller 요청)
			arg2.doFilter(arg0, arg1);
			
		}
		
	}

}
