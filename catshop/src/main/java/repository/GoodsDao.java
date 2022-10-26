package repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import vo.Goods;

public class GoodsDao {
	
	
	//고객 상품리스트 페이지에서 사용
	//customerGoodsList.jsp
	public List<Map<String, Object>> selectCustomerGoodsListByPage(Connection conn, int rowPerPage, int beginRow, int num) throws SQLException{
		System.out.println("GoodsDao selectCustomerGoodsListByPage �떆�옉");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "";

	
		//스크레쳐
		if(num==10) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '스크레쳐'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		
		//장난감
		if(num==9) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '장난감'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		
		//고양이간식&영양제
		if(num==8) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '고양이간식&영양제'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		
		//습식사료
		if(num==7) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '습식사료'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		
		//건식사료
		if(num==6) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '건식사료'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		//배변용품
		if(num==5) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '배변용품'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		//미용/목욕용품
		if(num==4) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '목욕/미용용품'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		//생활/리빙용품
		if(num==3) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '생활/리빙용품'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		//캣타워
		if(num==2) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N' AND g.goods_menu = '캣타워'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
		
		//신상품
		if(num==1) {
			sql = "			     SELECT  g.goods_no 		goodsNo"
					+ "					, g.goods_menu 		goodsMenu"
					+ "					, g.goods_name 		goodsName"
					+ "					, g.goods_price 	goodsPrice"
					+ "					, g.update_date 	updateDate"
					+ "					, g.create_date 	createDate"
					+ "					, g.sold_out 		soldOut"
					+ "					, i.filename	 	fileName"
					+ "					, i.origin_filename originalFilename"
					+ "					, i.content_type 	contentType"
					+ "					, i.create_date 	iCreateDate"
					
					+ "			FROM  goods g INNER JOIN goods_img i"
					+ "			ON g.goods_no = i.goods_no"
					
					+ "			WHERE g.sold_out = 'N'"
					+ "			ORDER BY g.create_date desc LIMIT ?, ?";
			
			
		}
			/*
		//怨좉컼�씠 �뙋留ㅻ웾�닔 留롮�寃� 遺��꽣
		sql = "		SELECT g.goods_no goodsNo"
				+ "				, g.goods_menu goodsMenu"
				+ "             , g.goods_name goodsName"
				+ "             , g.goods_price goodsPrice"
				+ "             , gi.filename filename"
				+ "       FROM"
				+ "       goods g LEFT JOIN (SELECT goods_no, SUM(order_quantity) sumNum"
				+ "                      FROM orders\r\n"
				+ "                      GROUP BY goods_no) t"
				+ "                      ON g.goods_no = t.goods_no"
				+ "                         INNER JOIN goods_img gi"
				+ "                         ON g.goods_no = gi.goods_no"
				+ "							WHERE g.sold_out = 'N'"
				+ "       ORDER BY IFNULL(t.sumNUm, 0) DESC LIMIT ?, ?";
		}
		*/
		
		/*
		SELECT g.goods_no goodsNo
             , g.goods_name goodsName
             , g.goods_price goodsPrice
             , gi.filename filename
       FROM
       goods g LEFT JOIN (SELECT goods_no, SUM(order_quantity) sumNum
                      FROM orders
                      GROUP BY goods_no) t
                      ON g.goods_no = t.goods_no
                         INNER JOIN goods_img gi
                         ON g.goods_no = gi.goods_no
       ORDER BY IFNULL(t.sumNUm, 0) DESC LIMIT ?, ?
		*/
		
		
		//모든상품
		if(num == 0) {
		sql = "SELECT  g.goods_no goodsNo"
				+ "		, g.goods_menu goodsMenu"
				+ "		, g.goods_name goodsName"
				+ "		, g.goods_price goodsPrice"
				+ "		, g.update_date updateDate"
				+ "		, g.create_date createDate"
				+ "		, g.sold_out soldOut"
				+ "		, i.filename fileName"
				+ "		, i.origin_filename originalFilename"
				+ "		, i.content_type contentType"
				+ "		, i.create_date iCreateDate"
				+ "		 from  goods g INNER JOIN goods_img i"
				+ "		  ON g.goods_no = i.goods_no"
				+ "			WHERE g.sold_out = 'N'"
				+ "		  ORDER BY g.goods_no LIMIT ?, ?";
		
		
		
		
		}
		
		stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		
		System.out.println(stmt + " <-- stmt");
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("goodsNo", rs.getInt("goods_no"));
			map.put("goodsName", rs.getString("goods_name"));
			map.put("goodsPrice", rs.getInt("goods_price"));
			map.put("soldOut", rs.getString("sold_out"));
			map.put("fileName", rs.getString("fileName"));
			
			list.add(map);
		}
		
		if(rs != null) {rs.close();}
		if(stmt != null) {stmt.close();}
		
		System.out.println("GoodsDao selectCustomerGoodsListByPage �걹");
		return list;
	}
	
	//4-4. 상품수정
	public int updateGoods(Connection conn, int goodsNo, Goods goods) throws Exception {
		System.out.println("!!!!GoodsDao updateGoods!!!!");
		int row = 0;
		PreparedStatement stmt = null;
		String sql = "UPDATE goods SET goods_name = ?"
				+ ", goods_price = ?"
				+ ", update_date = NOW()"
				+ ", sold_out = ?"
				+ " WHERE goods_no = ?";

		stmt = conn.prepareStatement(sql); 
		
			stmt.setString(1, goods.getGoodsName());
			stmt.setInt(2, goods.getGoodsPrice());
			stmt.setString(3, goods.getSoldOut());
			stmt.setInt(4, goodsNo);

		
		row = stmt.executeUpdate(); // insert 성공한 row의 수값
		// 1) insert가 실행이 되었다면
		
		System.out.println("row : " + row);
		
		if(stmt!=null)  {stmt.close();}
		
		return row;
	}
	
//////////////////////////////////////////////////////////////////////////////////////
	
	//4-3. 상품&이미지 입력
	//반환값 : key값 (jdbc api)
	public int insertGoods(Connection conn, Goods goods) throws Exception{
		System.out.println("!!!!GoodsDao insertGoods!!!!");
		int keyId = 0;
		int row = 0;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
		stmt = conn.prepareStatement("INSERT INTO goods(goods_menu"
						+ ", goods_name"
						+ ", goods_price"
						+ ", update_date"
						+ ", create_date)"
						+ " VALUES(?,?,?,NOW(),NOW())"
						, Statement.RETURN_GENERATED_KEYS); 
		
		//soldOut 기본값 n값으로 설정해놨기에 insert할 필요 x
		stmt.setString(1, goods.getGoodsMenu());
		stmt.setString(2, goods.getGoodsName());
		stmt.setInt(3, goods.getGoodsPrice());
		
		row = stmt.executeUpdate(); // insert 성공한 row의 수값
		// 1) insert가 실행이 되었다면
		
		System.out.println(stmt + " <-- GoodsDao insertGoods stmt");
		if(row != 0) {
			System.out.println(row + " <-- insert 성공한 row의 수값");
		
			// 2) select last_ai_key from... 키 값 가져오기
			rs = stmt.getGeneratedKeys(); //select last_key
		
		if(rs.next()) {
			System.out.println("GoodsDao rs.next()");
			keyId = rs.getInt(1);
				}
			}
		
		}finally {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
		}
		System.out.println(keyId + " <-- keyId");
		return keyId;
	}
	
	//4-2. �젣�뭹�긽�꽭
	public Map<String, Object> selectGoodsAndImgOne(Connection conn, int goodsNo) throws SQLException{
		System.out.println("GoodsDao selectGoodsAndImgOne");
		Map<String, Object> map = new HashMap<String, Object>();
		
		String sql = "SELECT  g.goods_no goodsNo, g.goods_menu goodsMenu, g.goods_name goodsName, g.goods_price goodsPrice, g.update_date updateDate, g.create_date createDate, g.sold_out soldOut, i.filename fileName, i.origin_filename originalFilename, i.content_type contentType, i.create_date iCreateDate from  goods g INNER JOIN goods_img i ON g.goods_no = i.goods_no WHERE g.goods_no=?";
		
		/*
		
		SELECT g.*, gi.*
		FROM goods g INNER JOIN goods_img gi
		ON g.goods_no = gi.goods_no
		WHERE g.goods_no = ?
		
		
		while(rs.next()) {
			Map<String, Object> m = new Map<String, Object>();
			m.put("goodsNo", rs.getInt("goodsNo"));
		}	
			// 쿼리에서 where 조건이 없다면 반환타입 List<Map<String, Object>> list
			// 행 전체를 리턴
		
		*/
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		//run
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsNo);
		
		System.out.println(stmt + " <-- GoodsDao selectGoodsAndImgOne stmt");
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			map = new HashMap<String , Object>();//map에 값 넣어주기
			
			map.put("goodsNo", rs.getInt("goodsNo"));
			map.put("goodsMenu", rs.getString("goodsMenu"));
			map.put("goodsName", rs.getString("goodsName"));
			map.put("goodsPrice", rs.getInt("goodsPrice"));
			map.put("updateDate", rs.getString("updateDate"));
			map.put("createDate", rs.getString("createDate"));
			map.put("soldOut", rs.getString("soldOut"));
			map.put("fileName", rs.getString("fileName"));
			map.put("originalFilename", rs.getString("originalFilename"));
			map.put("contentType", rs.getString("contentType"));
			map.put("iCreateDate", rs.getString("iCreateDate"));
		}
		
		if(rs != null) {rs.close();}
		if(stmt != null) {stmt.close();}
		
		System.out.println("GoodsDao selectGoodsAndImgOne �떎�뻾�셿猷�");
		System.out.println(map + " <-- map");
		return map;//map 전체 리턴
	}
		

	
	//4-1. 상품목록
	public List<Goods> selectGoodsListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception{//final �긽�닔�솕 �빐二쇨린
		System.out.println(beginRow + " <-- beginRow");
		System.out.println(rowPerPage + " <-- rowPerPage");
		List<Goods> list = new ArrayList<Goods>();
		
		String sql  = "SELECT goods_no goodsNo"
				+ ", goods_menu	goodsMenu"
				+ ", goods_name goodsName"
				+ ", goods_price goodsPrice"
				+ ", update_date updateDate"
				+ ", create_date createDate"
				+ ", sold_out soldOut "
				+ "	FROM goods "
				+ "	ORDER BY goods_no DESC"
				+ " LIMIT ?, ?";
		
		/*
			SELECT goods_no goodsNo, goods_name goodsName, goods_price goodsPrice, update_date updateDate, create_date createDate, sold_out soldOut
			FROM goods
			ORDER BY goods_no DESC
			LIMIT ?, ?
		*/
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);  //시작페이지부터
		stmt.setInt(2, rowPerPage); //한페이지에 보이고 싶은 행의 갯수만큼 보이게 한다.
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			Goods goods = new Goods();
			goods.setGoodsNo(rs.getInt("goodsNo"));
			goods.setGoodsMenu(rs.getString("goodsMenu"));
			goods.setGoodsName(rs.getString("goodsName"));
			goods.setGoodsPrice(rs.getInt("goodsPrice"));
			goods.setUpdateDate(rs.getString("updateDate"));
			goods.setCreateDate(rs.getString("createDate"));
			goods.setSoldOut(rs.getString("soldOut"));
			list.add(goods); //리스트에 넣기
		}
		
		if(rs != null) {rs.close();}
		if(stmt != null) {stmt.close();}
		
		return list; //리스트에 담아서 리스트를 리턴
	}
	
	public int selectTotalCount(Connection conn) throws Exception { //int媛� 由ы꽩
		int totalCount = 0;
		
		
		String sql = "SELECT COUNT(*) FROM goods"; //goods 테이블의 행 갯수 찾기
		
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		try {
		stmt = conn.prepareStatement(sql); //sql 실행
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			totalCount = rs.getInt("COUNT(*)");
		}
		
		}finally {
			//null이 아니면 닫아주기.
			if(rs != null) { rs.close();}
			if(stmt != null) { stmt.close();}
		}
		
		return totalCount;
	}
	
	public int selectTotalCount(Connection conn, String goodsMenu) throws Exception { //int媛� 由ы꽩
		int totalCount = 0;
		
		
		String sql = "";
		
		if(goodsMenu.equals("All")||goodsMenu.equals("New")) {
			sql = "SELECT COUNT(*) FROM goods";
		}else if(goodsMenu.equals("고양이간식")) {
			sql = "SELECT COUNT(*) FROM goods WHERE goods_menu = '고양이간식&영양제'";
		}else{
			sql = "SELECT COUNT(*) FROM goods WHERE goods_menu = " + goodsMenu;
		}
		
		PreparedStatement stmt = null;
		ResultSet rs  = null;
		try {
		stmt = conn.prepareStatement(sql); //sql 실행
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			totalCount = rs.getInt("COUNT(*)");
		}
		
		}finally {
		//null이 아니면 닫아주기
			if(rs != null) { rs.close();}
			if(stmt != null) { stmt.close();}
		}
		
		return totalCount;
	}
	
	public int deleteGoods(Connection conn, Goods goods) throws Exception {
		int row = 0;
		String sql = "DELETE FROM goods WHERE goods_no = ?";
		
		PreparedStatement stmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, goods.getGoodsNo());
			row = stmt.executeUpdate();
		
		}finally {
			stmt.close();
		}
		
		return row;
	}

}
