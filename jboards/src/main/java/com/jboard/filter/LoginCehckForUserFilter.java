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

@WebFilter(urlPatterns = {"/user/login.do","/user/register.do","/user/terms.do"})
public class LoginCehckForUserFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		//로그인여부확인
		HttpServletRequest req = (HttpServletRequest) arg0;
		
		
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		//로그인을 했으면
		if(sessUser != null) {
			//게시판 목록 이동
			HttpServletResponse resp = (HttpServletResponse) arg1;
			resp.sendRedirect("/jboards/article/list.do");
		
		//로그인을 하지않았으면
		}else {
			arg2.doFilter(arg0, arg1);
			
		}
		
	}

}
