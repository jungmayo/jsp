package service;

import java.util.List;

import dao.CommentDAO;
import dto.CommentDTO;

public enum CommentService {
	
	INSTANCE;
	
	private CommentDAO dao = CommentDAO.getInstance();
	
	
	public int insertComment(CommentDTO dto) {
		return dao.insertComment(dto);
	}
	
	public CommentDTO selectComment(int no) {
		return dao.selectComment(no);
	}
	
	public List<CommentDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	
	public int updateComment(CommentDTO dto) {
		return dao.updateComment(dto);
	}
	
	public int deleteComment(String no) {
		return dao.deleteComment(no);
	}

}
