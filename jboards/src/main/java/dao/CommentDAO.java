package dao;


import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.CommentDTO;
import util.DBHelper;
import util.SQL;

public class CommentDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static CommentDAO instance = new CommentDAO();
	public static CommentDAO getInstance() {
		return instance;
	}
	
	
	public int insertComment(CommentDTO dto) {
		
		int pk = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT, Statement.RETURN_GENERATED_KEYS); //두번째자리 : 옵션값 , INSERT 실행 후 자동 생성되는 pk 값을 리턴하는 옵션
			psmt.setInt(1, dto.getParent());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			psmt.setString(4, dto.getRegip());
			psmt.executeUpdate();
			
			rs = psmt.getGeneratedKeys(); //옵션으로 선택한 해당 키를 가져올 수 있음
				if(rs.next()) {
					pk = rs.getInt(1); //트랜잭션 불필요
				}
				
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return pk;
	}
	
	public CommentDTO selectComment(int no) {
		CommentDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENT);
			psmt.setInt(1,no);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto = new CommentDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setRegip(rs.getString(5));
				dto.setRdate(rs.getString(6));
				dto.setNick(rs.getString(7));
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return dto;
	}
	
	public List<CommentDTO> selectComments(String parent) {
		List<CommentDTO> comments = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COMMENTS);
			psmt.setString(1, parent);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setNo(rs.getInt(1));
				dto.setParent(rs.getInt(2));
				dto.setContent(rs.getString(3));
				dto.setWriter(rs.getString(4));
				dto.setRegip(rs.getString(5));
				dto.setRdate(rs.getString(6));
				dto.setNick(rs.getString(7));
				comments.add(dto);
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			closeAll();
		}
		return comments;
	}
	
	public int updateComment(CommentDTO dto) {
		
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_COMMENT);
			psmt.setString(1, dto.getContent());
			psmt.setInt(2, dto.getNo());
			result = psmt.executeUpdate();
			
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	
	}
	
	
	
	public int deleteComment(String no) {
		
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
			psmt.setString(1, no);
			result = psmt.executeUpdate();
			
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return result;
	}


}
