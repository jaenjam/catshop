package repository;

import java.sql.*;

import vo.*;

public class SignDao {
	public String idCheck(Connection conn, String id) throws Exception {
		String ckId = null;
		System.out.println("SignDao");
		String sql = "SELECT t.id FROM (SELECT customer_id id FROM customer UNION SELECT employee_id id FROM employee UNION SELECT out_id id FROM outid)t WHERE t.id = ?";
		/*
		 	SELECT t.id
		 	FROM (SELECT customer_id id FROM customer
		 			UNION
		 			SELECT employ_id id FROM employee
		 			UNION
		 			SELECT out_id id FROM out_id) t
		 	WHERE t.id = ?
		 	--> null일때 사용가능한 아이디
		 	
			if(rs != null) {
				rs.close();
			}
		 */
		
		//null이면 사용가능
		//null이 아니면 사용불가능
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			//중복검사하려고 하는 id값을 넣어서 Customer, Employee, OutId 중 어딘가에 있는지 확인
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()) {//rs.next()가 참일때
				//ckId에 sql을 실행한 결과, t.id(Customer, Employee, OutId에 있는 아이디값)을 넣어준다.
				ckId = rs.getString("t.id");
			}
			
		}finally {
			//만약 rs가 null이 아니라면 rs.close()한다.
			if(rs != null) {rs.close();}
			//만약 stmt가 null이 아니라면 stmt.close()한다.
			if(stmt != null) {stmt.close();}
		}
		//ckId값이 null이면 현재 사용하려는 아이디 값이 중복되지 않은것, 즉 사용가능한 아이디
		//null이아니고 문자열이나 숫자가 들어가 있다면, 
		//그 값으로 된 아이디가 Customer, Employee, OutId에 있어서 사용못하는 아이디
		System.out.println(ckId);
		return ckId;
	}
}
