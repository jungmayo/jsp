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
	}
	public FileDTO selectFile(String fno) {
		FileDTO fileDto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				fileDto = new FileDTO();
				fileDto.setFno(rs.getInt(1));
				fileDto.setAno(rs.getInt(2));
				fileDto.setOname(rs.getString(3));
				fileDto.setSname(rs.getString(4));
				fileDto.setDownload(rs.getInt(5));
				fileDto.setRdate(rs.getString(6));
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
		return fileDto;
	}
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {}
	public void updateFileDownloadCount(String fno) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_FILE_DOWNLOAD_COUNT);
			psmt.setString(1, fno);
			psmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally {
			closeAll();
		}
	}
	public void deleteFile(int fno) {}

}
