package repository;

import vo.*;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao {
	
	public int insertEmployee(Connection conn, Employee employee) throws Exception {
		int row = 0;
		String sql = "INSERT INTO employee(employee_id, employee_pass, employee_name, update_date, create_date) VALUES(?,PASSWORD(?),?,NOW(),NOW())";

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, employee.getEmployeeId());
			stmt.setString(2, employee.getEmployeePw());
			stmt.setString(3, employee.getEmployeeName());
			
			row = stmt.executeUpdate();
			
		} finally {
			stmt.close();
		}

		return row;
	}
	
	public int deleteEmployee(Connection conn, Employee paramEmployee) throws Exception {
		
		// 동일한 conn
		// conn.close()X
		int row = 0;
		String sql = "DELETE FROM employee WHERE employee_id= ? AND employee_pass=PASSWORD(?)";
		
		 PreparedStatement stmt=null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramEmployee.getEmployeeId());
			stmt.setString(2, paramEmployee.getEmployeePw());
			row = stmt.executeUpdate();
		 
		
		}finally {
			stmt.close();
		}
	
		return row;		
	}
	
	
	public Employee selectEmployeeOne(String EmployeeId) throws Exception {
		Employee employee1 = null;
		String sql="SELECT employee_id, employee_pass, employee_name, create_date, active FROM employee WHERE employee_id = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, EmployeeId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				employee1 = new Employee();
				employee1.setEmployeeId(rs.getString("employee_id"));
				employee1.setEmployeeName(rs.getString("employee_name"));
				employee1.setCreateDate(rs.getString("create_date"));
				employee1.setActive(rs.getString("active"));
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
		}
		return employee1;
	}
	
	
	public Employee login(Employee employee) throws Exception {
		Employee loginEmployee = null;
		String sql = "SELECT employee_id, employee_name, active FROM employee WHERE employee_id=? AND employee_pass=PASSWORD(?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, employee.getEmployeeId());
			stmt.setString(2, employee.getEmployeePw());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				loginEmployee = new Employee();
				loginEmployee.setEmployeeId(rs.getString("employee_id"));
				loginEmployee.setEmployeeName(rs.getString("employee_name"));
				loginEmployee.setActive(rs.getString("active"));
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
		}
		return loginEmployee;
	}
	// employeeList.jsp
	public ArrayList<Employee> selectEmployeeList(Connection conn, final int rowPerPage, int beginRow) throws SQLException{

		ArrayList<Employee> list = new ArrayList<Employee>();


		String sql = "SELECT employee_id"
				+ ", employee_pass"
				+ ", employee_name"
				+ ", update_date"
				+ ", create_date"
				+ ", active "
				+ "FROM employee"
				+ " LIMIT ?,?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getString("employee_id"));
				employee.setEmployeeName(rs.getString("employee_name"));
				employee.setEmployeePw(rs.getString("employee_pass"));
				employee.setUpdateDate(rs.getString("update_date"));
				employee.setCreateDate(rs.getString("create_date"));
				employee.setActive(rs.getString("active"));
				list.add(employee);
			}
			
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
		}
		return list;
		
	}	// end selectEmployeeList
	
	public int lastPage(Connection conn) throws SQLException {
		
		String sql = "SELECT COUNT(*) FROM employee";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		
		stmt = conn.prepareStatement(sql);
	    rs = stmt.executeQuery();
		
		 if(rs.next()) {
	            totalCount = rs.getInt("COUNT(*)");
	         }
		
		 if(rs!=null)   {
				rs.close();
			}
		 if(stmt!=null)   {
				stmt.close();
			}
		 
		return totalCount;
	}
	
	
	
	//3-2. 활성화 값변경 active 변경
	public int updateEmployeeActive (Connection conn,Employee employee) throws SQLException {
		
		String sql = "UPDATE employee SET active=? WHERE employee_id=?";
		PreparedStatement stmt = null;
		int row = 0;
		
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, employee.getActive());
		stmt.setString(2, employee.getEmployeeId());
		
		row = stmt.executeUpdate();
		
		// 디버깅
		System.out.println("row : " + row);
		
		if(stmt!=null)   {
			stmt.close();
		}
		
		return row;
		
	}	// end updateEmployeeActive
	
}	// end class
