package dao;

import java.util.ArrayList;
import java.util.List;

import dto.TermsDTO;
import util.DBHelper;

public class TermsDAO extends DBHelper {
	

	private static TermsDAO instance = new TermsDAO();
	public static TermsDAO getInstance() {
		return instance;
	}
	private TermsDAO() {}
	
	public void insertTerms(TermsDTO dto){}
	
	
	
	
	
	public TermsDTO selectTerms() {
		TermsDTO dto = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `terms`");
			if(rs.next()) {
				dto = new TermsDTO();
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
	
	
	public List<TermsDTO> selectTermses() {
		List<TermsDTO> dto = new ArrayList<TermsDTO>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery("select * from `terms`");
			while(rs.next()) {
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateTerms(TermsDTO terms) {}
	
	public void deleteTerms(String terms) {}

}
