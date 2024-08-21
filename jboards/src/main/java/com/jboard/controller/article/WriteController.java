package com.jboard.controller.article;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ArticleDTO;
import dto.FileDTO;
import dto.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ArticleService;
import service.FileService;


@WebServlet("/article/write.do")
public class WriteController extends HttpServlet {

	private ArticleService articleservice = ArticleService.INSTANCE;
	private FileService fileservice = FileService.INSTANCE;
	
	
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/write.jsp");
			dispatcher.forward(req, resp);		 //list에서 글쓰기 버튼을 눌렀을때 호출되고 wirte.jsp를 표시함
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		//HttpSession session = req.getSession();
		//String writer = ((UserDTO)session.getAttribute("sessUser")).getUid(); -> 세션에서 user의 아이디 정보를 받아와서 writer에 저장
		String regip = req.getRemoteAddr();
		
		logger.debug(writer);
		
		//파일 업로드
		List<FileDTO> files = fileservice.fileUpload(req);
		
		
		ArticleDTO dto = new ArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWirter(writer);
		dto.setRegip(regip);
		dto.setFile(files.size()); //갯수는 fileupload에서 결정되므로 순서를 위로 올림
		
		logger.debug(regip);
		//글 등록
		int no = articleservice.insertArticle(dto);
		
		
		
		for(FileDTO filedto : files) {
			filedto.setAno(no);
			fileservice.insertFile(filedto); //2번첨부하면 2번실행
		}		
		
		resp.sendRedirect("/jboards/article/list.do");
		
	}

}
