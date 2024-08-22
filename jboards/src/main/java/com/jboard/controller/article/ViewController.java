package com.jboard.controller.article;

import java.io.IOException;

import dto.ArticleDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ArticleService;
import service.FileService;
@WebServlet("/article/view.do")
public class ViewController extends HttpServlet {

	private ArticleService service = ArticleService.INSTANCE;
	private FileService fileservice = FileService.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no"); //list에서 no를 담아서 전달
		
		service.updateArticle(no);
		
		//데이터 조회
		ArticleDTO articleDto = service.selectArticle(no);
		
		
		//공유참조
		req.setAttribute("articleDto", articleDto);
		
		
		
		//포워드(화면출력)
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/view.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	

}
