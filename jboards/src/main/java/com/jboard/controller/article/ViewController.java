package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import dto.ArticleDTO;
import dto.CommentDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ArticleService;
import service.CommentService;
import service.FileService;
@WebServlet("/article/view.do")
public class ViewController extends HttpServlet {

	private ArticleService service = ArticleService.INSTANCE;
	private CommentService commentService = CommentService.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no"); //list에서 no를 담아서 전달
		String pg = req.getParameter("pg"); //글을 눌렀을때 pg도 함께 전달 받음
		
		service.updateHitCount(no);
		
		//데이터 조회
		ArticleDTO articleDto = service.selectArticle(no);
		
		//댓글 조회
		List<CommentDTO> comments = commentService.selectComments(no);
		
		//공유참조
		req.setAttribute("articleDto", articleDto);
		req.setAttribute("pg", pg); //해당 pg를 view로 전달
		req.setAttribute("comments", comments);
		
		
		//포워드(화면출력)
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/view.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	

}
