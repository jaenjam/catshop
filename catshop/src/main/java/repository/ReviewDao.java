package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Review;

public class ReviewDao {

	public int insertReview(Connection conn, Review review) throws Exception {
		int row = 0;

		String sql = "INSERT INTO review"
				+ " (good_no"
				+ ", id"
				+ ", review_content"
				+ ", review_pw"
				+ ", create_date"
				+ ", update_date)"
				+ " VALUES(?,?,?,PASSWORD(?),NOW(),NOW())";
		
				
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, review.getGoodNo());
			stmt.setString(2, review.getId());
			stmt.setString(3, review.getReviewContent());
			stmt.setString(4, review.getReviewPw());
			
			System.out.println(stmt + " <-- ReviewDao insertReview stmt");
			//stmt 실행된 파라미터확인
			row  = stmt.executeUpdate();
			//업데이트
			if(row !=0) {
				System.out.println(row + " <-- insert 성공한 row의 수값");
			}
			
			
		}finally {
			if(stmt!=null) {stmt.close();}
			//if(conn!=null) {conn.close();}
			//Dao는 끝이지만 Service는 끝이나지 않았기 때문에 conn.close() 하면 안됨.			
		}
		
		System.out.println(row + " <-- ReviewDao insertReview row");
		//성공하면 1을 반환 성공안하면 0을 반환
		return row;
	}

	public ArrayList<Review> selectReviewList(Connection conn, int goodsNo) throws Exception {
		
		ArrayList<Review> list = new ArrayList<Review>();
		
		System.out.println("!!! ReviewDao selectReviewList!!!");
		
		String sql = "SELECT review_no"
				+ ", good_no"
				+ ", id"
				+ ", review_content"
				+ ", create_date"
				+ ", update_date"
				+ " FROM review "
				+ " WHERE good_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, goodsNo);
			
			rs = stmt.executeQuery();
			
			System.out.println(stmt + " <-- stmt");
			
			while(rs.next()) {
				Review review = new Review();
				
				review.setReviewNo(rs.getInt("review_no"));
				review.setGoodNo(rs.getInt("good_no"));
				review.setId(rs.getString("id"));
				review.setReviewContent(rs.getString("review_content"));
				review.setCreateDate(rs.getString("create_date"));
				review.setUpdateDate(rs.getString("update_date"));
				
				list.add(review);
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			//if(conn!=null) {conn.close();}
			//Dao는 끝이지만 Service는 끝이나지 않았기 때문에 conn.close() 하면 안됨.
		}
		
		return list;
	}

	public int reviewCount(Connection conn, int goodsNo) throws Exception {
		
		String sql = "SELECT COUNT(*) FROM review WHERE good_no = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		
		stmt = conn.prepareStatement(sql);
	    rs = stmt.executeQuery();
		System.out.println(rs + " <-- rs");
	    
	    
		 if(rs.next()) {
	            totalCount = rs.getInt("COUNT(*)");
	         }
		
		 if(rs!=null)   {
				rs.close();
			}
		 if(stmt!=null)   {
				stmt.close();
			}
		 
		 System.out.println(totalCount + " <-- totalCount");
		 
		return totalCount;
	}

	public int deleteReivew(Connection conn, int goodsNo) throws Exception {
		int row = 0;
		String sql = "DELETE FROM review WHERE good_no = ?";
		
		PreparedStatement stmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, goodsNo);
			row = stmt.executeUpdate();
		
		}finally {
			stmt.close();
		}
		
		System.out.println(row + " <-- row");
		return row;
		
	}
	
	

}
