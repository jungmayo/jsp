package com.jboard.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.CommentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CommentService;

@WebServlet("/comment/write.do")
public class WriteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CommentService service = CommentService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//폼데이터 수신 parent,comment,writer는 parameter로 수신, input hidden으로 html을 새롭게 생성해서 가져옴
		String parent = req.getParameter("parent");
		String comment = req.getParameter("comment");
		String writer = req.getParameter("writer"); //세션열어서 가져올수도있음
		String regip = req.getRemoteAddr();
		
		//DTO생성
		CommentDTO dto = new CommentDTO();
		dto.setParent(parent); //댓글의 부모 글 번호
		dto.setContent(comment);
		dto.setWriter(writer);
		dto.setRegip(regip);
		logger.debug(dto.toString());
		
		// 댓글 등록
		int pk = service.insertComment(dto); //error면 0 , insert가 성공하면 1
		CommentDTO commentDto = service.selectComment(pk); //댓글 입력과 조회를 동시에 view.jsp로 전달

		logger.debug(commentDto.toString());
		//JSON 생성 및 출력
		/*JsonObject json = new JsonObject();
		json.addProperty("nick", commentDto.getNick());
		json.addProperty("rdate", commentDto.getRdate());
		json.addProperty("content", commentDto.getContent());*/
		
		
		//Gson생성 , commentDto가 해당 필드를 다 가지고 있기 때문에 위와 같이  출력하지 않고 Gson으로 사용
		Gson gson = new Gson();
		String json = gson.toJson(commentDto); // json문자열로 변환해서 던지기
		
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.print(json);
		
		// 게시글로 리다이렉트
		//resp.sendRedirect("/jboards/article/view.do?no="+parent);
	}
}
