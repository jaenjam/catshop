package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.GoodsImg;

public class GoodsImgDao {
	
	public int updateGoodsImg(Connection conn, int goodsNo, GoodsImg goodsImg) throws Exception {
		System.out.println("!!!!GoodsImgDao updateGoodsImg!!!!");
		int row = 0;
		String sql="UPDATE goods_img SET filename = ?"
				+ ", origin_filename = ?"
				+ ", content_type = ? "
				+ " WHERE goods_no = ?";
		PreparedStatement stmt = null;
		try {
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, goodsImg.getFilename());
		stmt.setString(2, goodsImg.getOriginalFilename());
		stmt.setString(3, goodsImg.getContentType());
		stmt.setInt(4, goodsNo);
		
		System.out.println(stmt + " <-- GoodsImgDao updateGoodsImg stmt");
		
		row = stmt.executeUpdate();
		
		}finally {
			if(stmt != null) {stmt.close();}
		}
		
		System.out.println(row + " <-- updateGoodsImg 성공 row 수");
		return row;
	}
	
	public int insertGoodsImg(Connection conn, int goodsNo, GoodsImg goodsImg) throws Exception {
		System.out.println("!!!!GoodsImgDao insertGoodsImg!!!!");
		int row=0;
		String sql="INSERT INTO goods_img(goods_no"
				+ ", filename"
				+ ", origin_filename"
				+ ", content_type"
				+ ", create_date) "
				+ "values(?,?,?,?,NOW())";
		
		PreparedStatement stmt = null;
		try {
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsNo);
		stmt.setString(2, goodsImg.getFilename());
		stmt.setString(3, goodsImg.getOriginalFilename());
		stmt.setString(4, goodsImg.getContentType());
		
		System.out.println(stmt + " <-- GoodsImgDao insertGoodsImg stmt");
		
		row = stmt.executeUpdate();
		
		}finally {
			if(stmt != null) {stmt.close();}
		}
		
		System.out.println(row + " <-- insert 성공한 row의 수값");
		return row;
	}

	public int deleteGoodsImg(Connection conn, GoodsImg goodsImg) throws Exception {
		int row = 0;
		String sql = "DELETE FROM goods_img WHERE goods_no = ?";
		
		PreparedStatement stmt = null;
		
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, goodsImg.getGoodsNo());
			row = stmt.executeUpdate();
			
		}finally {
			stmt.close();
		}
		return row;
	}
}
