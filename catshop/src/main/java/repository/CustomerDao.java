package repository;

import vo.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDao {
	
	public int adminUpdateCustomerPw(Connection conn, String customerId) throws Exception {
		System.out.println("!!!!CustomerDao adminUpdateCustomerPw!!!!");
		int row = 0;
		String sql = "UPDATE customer"
				+ " SET customer_pass = PASSWORD(1234)"
				+ " WHERE customer_id = ?";
		
		PreparedStatement stmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, customerId);
			row = stmt.executeUpdate();
			System.out.println(row + "<-- row");
			
		}finally {
			stmt.close();
			conn.close();
		}
		
		return row;
	}
	
	
	public int adminDeleteCustomer(Connection conn, Customer customer) throws Exception {
		int row = 0;
		
		String sql = "DELETE FROM customer WHERE customer_id = ?";
		
		PreparedStatement stmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customer.getCustomerId());
			row = stmt.executeUpdate();
		}finally {
			stmt.close();
		}
		
		return row;
	}
	
	public ArrayList<Customer> selectCustomerList(Connection conn, int rowPerPage, int beginRow) throws Exception{
		System.out.println("!!!!CustomerDao selectCustomerList!!!!");
		
		ArrayList<Customer> list = new ArrayList<Customer>();
		
		String sql = "SELECT customer_id"
				+ ", customer_pass"
				+ ", customer_name"
				+ ", customer_address"
				+ ", customer_telephone"
				+ ", update_date"
				+ ", create_date"
				+ " FROM customer"
				+ " LIMIT ?, ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
			
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getString("customer_id"));
			customer.setCustomerName(rs.getString("customer_name"));
			customer.setCustomerPass(rs.getString("customer_pass"));
			customer.setCustomerAddress(rs.getString("customer_address"));
			customer.setCustomerTelephone(rs.getString("customer_telephone"));
			customer.setUpdateDate(rs.getString("update_date"));
			customer.setCreateDate(rs.getString("create_date"));
			
			list.add(customer);
			
			System.out.println(list + " <-- list");
		}
		}finally {
			if(rs!=null)   {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
		
		return list;
	} // end selectCustomerList
	
	public int lastPage(Connection conn) throws Exception {
		String sql = "SELECT COUNT(*) FROM customer";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		
		stmt = conn.prepareStatement(sql);
	    rs = stmt.executeQuery();
		
		 if(rs.next()) {
	            totalCount = rs.getInt("COUNT(*)");
	         }
		
		 if(rs!=null)   {rs.close();}
		 if(stmt!=null)   {stmt.close();}
		 
		return totalCount;
	}
	
	//pw변경
	public int UpdateCustomerPw(Connection conn, String newPw, String id, String pw) throws Exception {
		int row = 0;
		String sql = "UPDATE customer SET customer_pass = PASSWORD(?) WHERE customer_id = ? AND customer_pass = PASSWORD(?)";
		PreparedStatement stmt = null;
		try {
			/*
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			*/
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, newPw);
			stmt.setString(2, id);
			stmt.setString(3, pw);
			row = stmt.executeUpdate();
			
		}finally {
			stmt.close();
			conn.close();
		}
		return row;
	}
	
	
	public int insertCustomer(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "INSERT INTO customer"
				+ "(customer_id"
				+ ", customer_pass"
				+ ", customer_name"
				+ ", customer_address"
				+ ", customer_telephone"
				+ ", update_date"
				+ ", create_date)"
				+ " VALUES(?,PASSWORD(?),?,?,?,NOW(),NOW())";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, customer.getCustomerId());
			stmt.setString(2, customer.getCustomerPass());
			stmt.setString(3, customer.getCustomerName());
			stmt.setString(4, customer.getCustomerAddress());
			stmt.setString(5, customer.getCustomerTelephone());
			
			row = stmt.executeUpdate();
			
		} finally {
			if(stmt!=null) {stmt.close();}
		}

		return row;
	}
	
	//탈퇴
	//CustomerSerive.removeCustomer(Customer paramCustomer)가 호출
	public int deleteCustomer(Connection conn, Customer paramCustomer) throws Exception {
		int row = 0;
		String sql = "DELETE FROM customer WHERE customer_id = ? AND customer_pass=PASSWORD(?)";
		
		PreparedStatement stmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, paramCustomer.getCustomerId());
			stmt.setString(2, paramCustomer.getCustomerPass());
			
			row = stmt.executeUpdate();
			
		}finally {
			if(stmt!=null) {stmt.close();}
		}
		// 동일한 conn을 사용해야됨
		// conn.close() X
		return row;
	}
	
	
	
	public Customer selectCusetomerOne(String customerId) throws Exception {
		Customer customer1 = null;
		String sql="SELECT customer_id, customer_pass, customer_name, customer_address, customer_telephone, create_date FROM customer WHERE customer_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, customerId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				customer1 = new Customer();
				customer1.setCustomerId(rs.getString("customer_id"));
				customer1.setCustomerName(rs.getString("customer_name"));
				customer1.setCustomerAddress(rs.getString("customer_address"));
				customer1.setCustomerTelephone(rs.getString("customer_telephone"));
				customer1.setCreateDate(rs.getString("create_date"));
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
		return customer1;
	}
	
	
	//CustomerService가 호출
	public Customer login(Customer customer) throws Exception {
		Customer loginCustomer = null;
		String sql = "SELECT customer_id, customer_name FROM customer WHERE customer_id=? AND customer_pass=PASSWORD(?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, customer.getCustomerId());
			stmt.setString(2, customer.getCustomerPass());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				loginCustomer = new Customer();
				loginCustomer.setCustomerId(rs.getString("customer_id"));
				loginCustomer.setCustomerName(rs.getString("customer_name"));
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
		return loginCustomer;
	}
	
}
