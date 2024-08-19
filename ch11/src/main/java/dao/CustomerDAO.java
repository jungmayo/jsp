package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.CustomerDTO;
import util.DBHelper;

public class CustomerDAO extends DBHelper { //싱글톤
	
	private static CustomerDAO instance = new CustomerDAO();
	public static CustomerDAO getInstance() {
		return instance;
	}
	private CustomerDAO() {}
	
	//로거 생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void inserCustomer(CustomerDTO dto) {
		
		String sql = "insert into customer values (?,?,?,?,?)";
		
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getCustId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setString(4, dto.getAddr());
			psmt.setString(5, dto.getRdate());
			psmt.executeUpdate();
			closeAll();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	public CustomerDTO selectCustomer(String custId) {
		
		CustomerDTO dto = null;
		String sql = "select * from `customer` where `custid`=?";
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, custId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CustomerDTO();
				dto.setCustId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAddr(rs.getString(4));
				dto.setRdate(rs.getString(5));
				
			}
			closeAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
	public List<CustomerDTO> selectCustomers() {
		
		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
		try {
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from `customer`");
			
			while(rs.next()) {
				CustomerDTO dto = new CustomerDTO();
				dto.setCustId(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAddr(rs.getString(4));
				dto.setRdate(rs.getString(5));
				customers.add(dto);
			}

			closeAll();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
		return customers;
	}
	public void updateCustomer(CustomerDTO dto) {
		
		String sql = "update `customer` set `name`=?,`hp`=?,`addr`=?,`rdate`=? where `custid`=?";
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getRdate());
			psmt.setString(5, dto.getCustId());
			logger.debug(dto.toString());
			psmt.executeUpdate();
			closeAll();
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public void deleteCustomer(String custId) {
		String sql = "delete from `customer` where `custid`=?";
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, custId);
			psmt.executeUpdate();
			closeAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
