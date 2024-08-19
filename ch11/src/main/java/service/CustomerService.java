package service;

import java.util.List;

import dao.CustomerDAO;
import dto.CustomerDTO;

public enum CustomerService {
	
	//열거타입으로 싱글톤 생성 dao로 넘어감
	INSTANCE;
	
	private CustomerDAO dao = CustomerDAO.getInstance();
	
	
	public void inserCustomer(CustomerDTO dto) {
		dao.inserCustomer(dto);
	}
		
	public CustomerDTO selectCustomer(String custId) {
		return dao.selectCustomer(custId);
	}
	public List<CustomerDTO> selectCustomers() {
		return dao.selectCustomers();
	}
	public void updateCustomer(CustomerDTO dto) {
		dao.updateCustomer(dto);
	}
	public void deleteCustomer(String custId) {
		dao.deleteCustomer(custId);
	}
	
	

}


