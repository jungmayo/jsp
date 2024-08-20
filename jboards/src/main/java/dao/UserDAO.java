package dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.UserDTO;
import util.DBHelper;
import util.SQL;

public class UserDAO extends DBHelper {
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	private UserDAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public int selectCountUser(String type, String value) {
		
		int result = 0;
		
		StringBuilder sql = new StringBuilder(SQL.SELECT_COUNT_USER);
		
		if(type.equals("uid")) {
			sql.append(SQL.WHERE_UID);
			
		}else if(type.equals("nick")) {
			sql.append(SQL.WHERE_NICK);
			
		}else if(type.equals("email")){
			sql.append(SQL.WHERE_EMAIL);
		
		}else if(type.equals("hp")) {
			sql.append(SQL.WHERE_HP);
		}
		
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql.toString()); //해당 객체에 담긴 문자열을 얻기 위해서 toString을 사용 (object클래스의 toString과는 다름)
			psmt.setString(1, value);
			rs = psmt.executeQuery();
			if(rs.next()) { //행이 있으면 true를 반환 , count 한 행만 조회하면 되니까 if문 사용
				result = rs.getInt(1); //첫번째 열을 의미/ 첫번째 열의 결과값을 result에 저장
			}
			closeAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/*int result = 0;
	try {
	    conn = getConnection();
	    psmt = conn.prepareStatement(SQL.SELECT_COUNT_USER);
	    psmt.setString(1, uid);
	    rs = psmt.executeQuery();
	    if(rs.next()) {
	        result = rs.getInt(1);
	    }
	    closeAll();*/
	
	
	
	public void insertUser(UserDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			psmt.executeUpdate();
			closeAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public UserDTO selectUser(String uid) {
		UserDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDTO();
				dto.setPass(rs.getString("pass"));
			}
			
			closeAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
		
		return dto;
	}
		
	
	
	public List<UserDTO> selectUsers() {
		return null;
	}
	public void updateUser(UserDTO dto) {}
	public void deleteUser(String uid) {}
	
	
	

}
