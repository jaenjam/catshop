package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Comment;

public class CommentDao {

	public int insertComment(Connection conn, Comment comment) throws Exception {
		int row = 0;

		String sql = "INSERT INTO comment"
				+ " (notice_no"
				+ ", id"
				+ ", comment_content"
				+ ", comment_pw"
				+ ", create_date"
				+ ", update_date)"
				+ " VALUES(?,?,?,PASSWORD(?),NOW(),NOW())";
		
				
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, comment.getNoticeNo());
			stmt.setString(2, comment.getId());
			stmt.setString(3, comment.getCommentContent());
			stmt.setString(4, comment.getCommentPw());
			System.out.println(stmt + " <-- CommentDao insertComment stmt");
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
		
		System.out.println(row + " <-- CommentDao insertComment row");
		//성공하면 1을 반환 성공안하면 0을 반환
		return row;
	}

	public ArrayList<Comment> selectCommentList(Connection conn, int noticeNo) throws Exception {
		
		ArrayList<Comment> list = new ArrayList<Comment>();
		
		String sql = "SELECT comment_no"
				+ ", notice_no"
				+ ", id"
				+ ", comment_content"
				+ ", create_date"
				+ ", update_date"
				+ " FROM comment"
				+ " WHERE notice_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, noticeNo);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Comment comment = new Comment();
				
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setNoticeNo(rs.getInt("notice_no"));
				comment.setId(rs.getString("id"));
				comment.setCommentContent(rs.getString("comment_content"));
				comment.setCreateDate(rs.getString("create_date"));
				comment.setUpdateDate(rs.getString("update_date"));
				
				
				list.add(comment);
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			//if(conn!=null) {conn.close();}
			//Dao는 끝이지만 Service는 끝이나지 않았기 때문에 conn.close() 하면 안됨.
		}
		
		return list;
	}
/*	
	public int lastPage(Connection conn) throws Exception {
		String sql = "SELECT COUNT(*) FROM comment";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			totalCount = rs.getInt("COUNT(*)");
		}
		 if(rs!=null) {rs.close();}
		 if(stmt!=null) {stmt.close();}
		 
		 return totalCount;
	}
*/
}
