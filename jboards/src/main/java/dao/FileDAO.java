package dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import dto.FileDTO;
import util.DBHelper;
import util.SQL;



public class FileDAO extends DBHelper{
	
	private static FileDAO instance = new FileDAO();
	public static FileDAO getinstance() {
		return instance;
		
	}
	private FileDAO() {}
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public void insertFile(FileDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOname());
			psmt.setString(3, dto.getSname());
			psmt.executeUpdate();
			closeAll();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public FileDTO selectFile(int fno) {
		return null;
	}
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {}
	public void deleteFile(int fno) {}

}
