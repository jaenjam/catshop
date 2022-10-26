package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Orders;


public class OrdersDao {
	
	//5-3. 주문상태값 수정
	public int updateOrdersState(Connection conn, Orders orders) throws Exception {
		int row = 0;
		
		String sql = "UPDATE orders SET order_state = ? WHERE order_no = ?";
		PreparedStatement stmt = null;
				
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, orders.getOrderState());
		stmt.setInt(2, orders.getOrderNo());
				
		row = stmt.executeUpdate();
				
		// 디버깅
		System.out.println("row : " + row);
			
		if(stmt!=null) {stmt.close();}		
				
		return row;
	}
	
	
	
	
	
	//5-2. 주문상세보기
	public Map<String, Object> selectOrdersOne(Connection conn, int odersNo){
		Map<String, Object> map = null;
		/*
		SELECT
		o. ,
		g. ,
		c. ,
		
		FROM orders o INNER JOIN goods g
		ON o.goods_no, g.goods_no
									INNER JOIN customer c
									ON o.customer_id = c.customer_id
		WHERE o.order_no = ?
		
		*/
		return map;
	}
	
	//5-1. 전체 주문 목록(관리자)
	public List<Map<String, Object>> selectOrdersList(Connection conn, int rowPerPage, int beginRow) throws Exception{
		
		System.out.println(beginRow + " <-- beginRow");
		
		List<Map<String, Object>> list = new ArrayList<>(); //다형성, 디커플링
		Map<String, Object> map = new HashMap<String, Object>(); 
		//list에 값을 넣어주기 위해 map을 만들어서 map값을 list에 넣는다.
		
		String sql = 
				"		SELECT o.order_no orderNo"
				+ "		, o.goods_no goodsNo"
				+ "		, o.customer_id customerId"
				+ "		, o.order_quantity orderQuantity"
				+ "		, o.order_price orderPrice"
				+ "		, o.order_address orderAddress"
				+ "		, o.order_state orderState"
				+ "		, o.update_date updateDateO"
				+ "		, o.create_date createDateO"
				+ "		, g.goods_no goodsNo"
				+ "		, g.goods_name goodsName"
				+ "		, g.goods_price goodsPrice"
				+ "		FROM orders o INNER JOIN goods g"
				+ "		ON o.goods_no = g.goods_no"
				+ "		ORDER BY o.create_date DESC"
				+ "		LIMIT ?, ?";
		/*
		SELECT o.order_no orderNo
		, o.goods_no goodsNo
		, o.customer_id customerId
		, o.order_quantity orderQuantity
		, o.order_price orderPrice
		, o.order_address orderAddress
		, o.order_state orderState
		, o.update_date updateDateO
		, o.create_date createDateO
		, g.goods_no goodsNo
		, g.goods_name goodsName
		, g.goods_price goodsPrice
		
		FROM orders o INNER JOIN goods g
		ON o.goods_no = g.goods_no
		ORDER BY o.create_date DESC
		LIMIT 1, 2;
		*/
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow); //시작페이지부터
		stmt.setInt(2, rowPerPage); //한페이지에 보이고 싶은 행의 갯수만큼까지 보여준다.
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			map = new HashMap<String, Object>(); //map에 값 넣어주기
			map.put("orderNo", rs.getInt("orderNo"));
			map.put("goodsNo", rs.getInt("goodsNo"));
			map.put("customerId", rs.getString("customerId"));
			map.put("orderQuantity", rs.getInt("orderQuantity"));
			map.put("orderPrice", rs.getInt("orderPrice"));
			map.put("orderAddress", rs.getString("orderAddress"));
			map.put("orderState", rs.getString("orderState"));
			map.put("updateDateO", rs.getString("updateDateO"));
			map.put("createDateO", rs.getString("createDateO"));
			map.put("goodsNo", rs.getInt("goodsNo"));
			map.put("goodsName", rs.getString("goodsName"));
			map.put("goodsPrice", rs.getInt("goodsPrice"));
			list.add(map);
			
			/*
			orders.setOrderNo(rs.getInt("ordersNo"));
			orders.setGoodsNo(rs.getInt("goodsNo"));
			orders.setCustomerId(rs.getString("customerId"));
			orders.setOderQuanitity(rs.getInt("orderQuantity"));
			orders.setOrderAddress(rs.getString("ordersAddress"));
			orders.setOrderState(rs.getString("ordersState"));
			orders.setUpdateDate(rs.getString("updateDateO"));
			orders.setCreateDate(rs.getString("createDateO"));
			orders.setGoodsNo(rs.getInt("goodsNo"));
			*/
			
			
		}
		
		if(rs != null) {rs.close();}
		if(stmt != null) {stmt.close();}
		
		return list;
	}
	
	public int selectTotalCount(Connection conn) throws Exception { //int媛� 由ы꽩
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM orders"; //goods 테이블의 행 갯수 찾기
		
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		
		try {
		stmt = conn.prepareStatement(sql); //sql 실행
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			totalCount = rs.getInt("COUNT(*)");
		}
		
		}finally {
		//null�씠 �븘�땲硫� �떕�븘二쇨린.
			if(rs != null) { rs.close();}
			if(stmt != null) { stmt.close();}
		}
		
		return totalCount;
	}
	
	
	
	public List<Map<String, Object>> selectOrdersListByCustomer(Connection conn, String customerId, int rowPerPage, int beginRow) throws Exception{
		// 리턴값
		List<Map<String, Object>> list = new ArrayList<>(); // 다형성
		/*
		 * SELECT o. FROM orders o INNER JOIN goods g ON o.goods_no = g.goods_no WHERE
		 * customer_id = ? ORDER BY create_date DESC LIMIT ?,?
		 */
		// 쿼리
		String sql = "SELECT o.order_no orderNo, o.goods_no goodsNo, o.order_quantity orderQuantity, o.order_price orderPrice, o.order_address orderAddress, o.order_state orderState, o.update_date updateDate, o.create_date createDate, g.goods_name goodsName, g.goods_price goodsPrice FROM orders o INNER JOIN goods g ON o.goods_no = g.goods_no WHERE o.customer_id = ? ORDER BY o.create_date DESC LIMIT ?,?";
		// 초기화
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 쿼리담기
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			// 디버깅
			System.out.println(stmt + "<-- selectOrdersListByCustomer stmt");
			// 쿼리실행
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orderNo", rs.getInt("orderNo"));
				map.put("goodsNo", rs.getInt("goodsNo"));
				map.put("orderQuantity", rs.getString("orderQuantity"));
				map.put("orderPrice", rs.getInt("orderPrice"));
				map.put("orderAddress", rs.getString("orderAddress"));
				map.put("orderState", rs.getString("orderState"));
				map.put("updateDate", rs.getString("updateDate"));
				map.put("createDate", rs.getString("createDate"));
				map.put("goodsName", rs.getString("goodsName"));
				map.put("goodsPrice", rs.getInt("goodsPrice"));
				list.add(map);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}
	
}
