package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OutIdDao {
	//탈퇴 회원의 아이디를 입력
	//CustomerSerive.removeCustomer(Customer paramCustomer)가 호출
	public int insertOutId(Connection conn, String customerId) throws Exception {
		// 동일한 conn을 사용해야됨
		// conn.close() X
		
		System.out.println("!!!!insertOutId!!!!");
		
		int row = 0;
		String sql = "INSERT INTO outid(out_id, out_date) VALUE(?,NOW())";
		
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			
			row = stmt.executeUpdate();
			
		}finally {
			stmt.close();
		}
		
		return row;
	}
}
