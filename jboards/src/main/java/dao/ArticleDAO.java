package dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.ArticleDTO;
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
			psmt.setString(3, dto.getWirter());
			psmt.setString(4, dto.getRegip());
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
		}
		return no;
	}
	
	public ArticleDTO selectArticle() {
		return null;
	}
	public List<ArticleDTO> selectArticles() {
		return null;
	}
	public void updateArticle(ArticleDTO dto) {}
	public void deleteArticle(int no) {}
	

}
