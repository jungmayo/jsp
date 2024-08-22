package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ArticleDTO;
import dto.FileDTO;
import util.DBHelper;
import util.SQL;

public class ArticleDAO extends DBHelper{
	
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	public int insertArticle(ArticleDTO dto) {
		int no = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false); //트랜잭션
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getFile());
			psmt.setString(4, dto.getWirter());
			psmt.setString(5, dto.getRegip());
			psmt.executeUpdate();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELET_MAX_NO);
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			conn.commit();
			
			closeAll();
				
		} catch (Exception e) {
			logger.error(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			closeAll();
		}
		return no;
	}
	
	public ArticleDTO selectArticle(String no) {
		ArticleDTO dto = null; //선언
		List<FileDTO> files = new ArrayList<FileDTO>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLE);
			psmt.setString(1, no);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				if(dto == null) { //한번만 반복하기 위해서 처음만 초기화하려고
				dto = new ArticleDTO(); //생성
				dto.setNo(rs.getInt(1));
				dto.setCate(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setComment(rs.getInt(5));
				dto.setFile(rs.getInt(6));
				dto.setHit(rs.getInt(7));
				dto.setWirter(rs.getString(8));
				dto.setRegip(rs.getString(9));
				dto.setRdate(rs.getString(10));
				}
				
				FileDTO fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(11));
				fileDto.setAno(rs.getInt(12));
				fileDto.setOname(rs.getString(13));
				fileDto.setSname(rs.getString(14));
				fileDto.setDownload(rs.getInt(15));
				fileDto.setRdate(rs.getString(16));
				files.add(fileDto);
			}
			
			dto.setFiles(files);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return dto;
		
	}
	public List<ArticleDTO> selectArticles(int start) {
		List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ARTICLES);
			psmt.setInt(1, start); //limit 현재 페이지수
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ArticleDTO dto = new ArticleDTO();
				dto.setNo(rs.getInt(1));
				dto.setCate(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setComment(rs.getInt(5));
				dto.setFile(rs.getInt(6));
				dto.setHit(rs.getInt(7));
				dto.setWirter(rs.getString(8));
				dto.setRegip(rs.getString(9));
				dto.setRdateSubString(rs.getString(10));
				dto.setNick(rs.getString(11)); //select_articles에서 join해서 user의 nick값을 가져와서 list에 넣을예정
				dto.setNumber(rs.getInt(12));
				articles.add(dto);

			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return articles;
		
	
	}
	public int selectCountTotal() {
		int total = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_COUNT_TOTAL);
			
			if(rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return total;
	}
	
	
	
	
	
	public void updateArticle(String no) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_ARTICLE);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	
	
	
	public void deleteArticle(int no) {}
	
	

}
