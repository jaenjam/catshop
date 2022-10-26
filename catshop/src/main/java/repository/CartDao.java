package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Cart;

public class CartDao {

	public int insertCart(Connection conn, Cart cart) throws Exception {
		int row = 0;
		String sql = "INSERT INTO cart"
				+ " (goods_no"
				+ ", customer_id"
				+ ", cart_totalcount"
				+ ", cart_totalprice"
				+ ", update_date"
				+ ", create_date)"
				+ " VALUES(?,?,?,?,NOW(),NOW())";
		
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, cart.getGoodsNo());
			stmt.setString(2, cart.getCustomerId());
			stmt.setInt(3, cart.getCartTotalcount());
			stmt.setInt(4, cart.getCartTotalprice());
			
			row = stmt.executeUpdate();
			
		}finally {
			if(stmt!=null) {stmt.close();}
		}
		
		return row;
	}

	
	//장바구니 속 개수 변경
	public int updateCart(Connection conn, Cart cart) throws Exception {
		int row = 0;
		String sql = "UPDATE cart"
				+ " SET cart_totalcount = ?"
				+ ", cart_totalprice = ?"
				+ ", update_date = NOW()"
				+ " WHERE goods_no = ?"
				+ " AND customer_id = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, cart.getCartTotalcount());
			stmt.setInt(2, cart.getCartTotalprice());
			stmt.setInt(3, cart.getGoodsNo());
			stmt.setString(4, cart.getCustomerId());
			
			System.out.println(stmt + " <-- stmt");
			row = stmt.executeUpdate();
			
			System.out.println(row +" <-- CartDao updateCart row");
			
		}finally {
			stmt.close();
		}
		
		return row;
	}

	//장바구니 보여주기
	public List<Map<String, Object>> selectCartList(Connection conn, String id) throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		//goods_no, goods_name, customer_id, cart_totalnum
		//, goods_price, cart_total price, create_date
		
		String sql = "SELECT c.goods_no 		goodsNo"
				+ "		, c.customer_id			customerId"
				+ "		, g.goods_name 			goodsName"
				+ "		, c.cart_totalcount 	cartTotalcount"
				+ "		, g.goods_price			goodsPrice"
				+ "		, c.cart_totalprice 	cartTotalprice"
				+ "		, c.update_date			updateDate"
				+ "		, c.create_date 		createDate"
				+ "		, gi.filename			filename"
				+ "		 FROM cart c INNER JOIN goods g"
				+ "		 ON g.goods_no = c.goods_no"
				+ "		 INNER JOIN goods_img gi"
				+ "		 ON g.goods_no = gi.goods_no"
				+ "		 WHERE customer_id = ?"
				+ "		 ORDER BY c.create_date DESC";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			
			System.out.println(stmt + " <-- stmt");
			
			if(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				
				map.put("goodsNo", rs.getInt("goods_no"));
				map.put("customerId", rs.getString("customerId"));
				map.put("goodsName", rs.getString("goods_name"));
				map.put("cartTotalcount", rs.getInt("cart_totalcount"));
				map.put("goodsPrice", rs.getInt("goods_price"));
				map.put("cartTotalprice", rs.getInt("cart_totalprice"));
				map.put("updateDate", rs.getString("update_date"));
				map.put("createDate", rs.getString("create_date"));
				map.put("filename", rs.getString("filename"));
				list.add(map);
				
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
		}
		
		System.out.println("CartDao selectCartList 끝");
		
		return list;
	}

	
	//장바구니에서 제거하기
	public int deleteCart(Connection conn, Cart cart) throws Exception {
		
		int row = 0;
		String sql = "DELETE FROM cart WHERE goods_no = ? AND customer_id = ?";
		
		PreparedStatement stmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cart.getGoodsNo());
			stmt.setString(2, cart.getCustomerId());
			row = stmt.executeUpdate();
		
		}finally {
			stmt.close();
		}
		
		return row;
	}

}
