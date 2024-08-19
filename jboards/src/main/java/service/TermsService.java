package service;

import java.util.List;

import dao.TermsDAO;
import dto.TermsDTO;


public enum TermsService {
	
	//열거타입으로 싱글톤 생성 dao로 넘어감
	INSTANCE;
	
	private TermsDAO dao = TermsDAO.getInstance();
	
	
	public void inserCustomer(TermsDTO dto) {
		dao.insertTerms(dto);
	}
		
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
	public List<TermsDTO> selectTermses() {
		return dao.selectTermses();
	}
	public void updateCustomer(TermsDTO dto) {
		dao.updateTerms(dto);
	}
	public void deleteCustomer(String custId) {
		dao.deleteTerms(custId);
	}
	
	

}


