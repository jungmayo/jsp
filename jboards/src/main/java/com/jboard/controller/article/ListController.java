package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import dto.ArticleDTO;
import dto.PageGroupDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ArticleService;

@WebServlet("/article/list.do")
public class ListController extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private ArticleService articleservice = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			
			String pg = req.getParameter("pg");
			//현재 페이지 번호 구하기
			int currentPage = articleservice.getCurrentPage(pg); //현재페이지, 인트로 변환하기 위해서
			
			//현재 페이지 그룹 구하기
			PageGroupDTO pageGroup = articleservice.getCurrentPageGroup(currentPage);
		
			//전체 게시물 갯수 구하기
			int total = articleservice.selectCountTotal();
			
			//마지막 페이지 번호 구하기
			int lastPageNum = articleservice.getLastPageNum(total);
		
			//페이지 시작 번호 구하기
			int start = articleservice.getStartNum(currentPage);
			
			//데이터조회
			List<ArticleDTO> articles = articleservice.selectArticles(start);
			
			//공유참조
			req.setAttribute("articles", articles);
			req.setAttribute("lastPageNum", lastPageNum);
			req.setAttribute("pageGroup", pageGroup);
			req.setAttribute("currentPage", currentPage);
			
			//포워드
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/list.jsp");
			dispatcher.forward(req, resp);
			
			
			
		}
		
		
	}
	
	
