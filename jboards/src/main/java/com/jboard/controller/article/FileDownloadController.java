package com.jboard.controller.article;

import java.io.IOException;

import dto.FileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FileService;
@WebServlet("/article/fileDownload.do")
public class FileDownloadController extends HttpServlet {

	private FileService service = FileService.INSTANCE;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fno = req.getParameter("fno");
		
		//파일 조회
		FileDTO filedto = service.selectFile(fno);
		
		//파일 다운로드 카운트 업데이트 
		service.updateFileDownloadCount(fno);
		//공유참조
		req.setAttribute("fileDto", filedto);
		
		//파일 다운로드
	
		service.fileDownload(req,resp);
	
		
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/article/modify.jsp");
		//dispatcher.forward(req, resp); //포워드 X 다운로드해야함

	

}
}