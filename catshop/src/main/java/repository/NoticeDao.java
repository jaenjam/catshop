package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Notice;

public class NoticeDao {

	public int adminUpdateNotice(Connection conn, Notice notice) throws Exception {
		int row = 0;
		String sql = "Update notice SET"
				+ " notice_title = ?"
				+ ", notice_content = ?"
				+ ", update_date = NOW()"
				+ " WHERE notice_no = ?"
				+ " AND notice_id = ?";
		
		PreparedStatement stmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, notice.getNoticeTitle());
			stmt.setString(2, notice.getNoticeContent());
			stmt.setInt(3, notice.getNoticeNo());
			stmt.setString(4, notice.getNoticeId());
			
			row = stmt.executeUpdate();
			
		}finally {
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
		}
		return row;
	}
	
	//공지 보여주기 숨기기기능
	public int updateNoticeShow(Connection conn, Notice notice) throws Exception {
		
		String sql = "UPDATE notice SET notice_show=?, update_date=NOW() WHERE notice_no=?";
		PreparedStatement stmt = null;
		int row = 0;
		
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, notice.getNoticeShow());
		stmt.setInt(2, notice.getNoticeNo());
		System.out.println(stmt + " <-- NoticeDao updateNoticeShow stmt");
		
		row = stmt.executeUpdate();
		
		// 디버깅
		System.out.println("row : " + row);
		
		if(stmt!=null)   {
			stmt.close();
		}
		
		return row;
	}
	
	
/*
	public int adminDeleteNotice(Connection conn, Notice notice) throws Exception {
		int row = 0;
		
		String sql = "DELETE FROM notice WHERE notice_no = ?";
		
		PreparedStatement stmt = null;
		try {
			DBUtil dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, notice.getNoticeNo());
			
			System.out.println(stmt + " <-- stmt");
			
			row = stmt.executeUpdate();
		}finally {
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
		}
		
		return row;
	}
*/
	
	public Notice selectNoticeOne(Connection conn, int noticeNo) throws Exception {
		System.out.println("NoticeDao selectNoticeOne");
		Notice notice = null;
		
		String sql = "SELECT notice_no"
				+ ", notice_title"
				+ ", notice_content"
				+ ", notice_id"
				+ ", notice_show"
				+ ", update_date"
				+ ", create_date"
				+ " FROM notice"
				+ " WHERE notice_no = ?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, noticeNo);
			
			System.out.println(stmt + " <-- NoticeDao selectNoticeOne stmt");
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeId(rs.getString("notice_id"));
				notice.setNoticeShow(rs.getString("notice_show"));
				notice.setUpdateDate(rs.getString("update_date"));
				notice.setCreateDate(rs.getString("create_date"));
			}
			
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			if(conn!=null) {conn.close();}
		}
		
		System.out.println("NoticeDao selectNoticeOne 실행완료");
		System.out.println(notice + " <-- notice");
		
		return notice;
	}
	
	public int insertNotice(Connection conn, Notice notice) throws Exception {
		System.out.println("!!!!NoticeDao insertNotice!!!!");
		
		int row = 0;
		String sql  = "INSERT INTO notice"
				+ "(notice_title"
				+ ", notice_content"
				+ ", notice_id"
				+ ", update_date"
				+ ", create_date)"
				+ " VALUES(?,?,?,NOW(),NOW())";
		
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, notice.getNoticeTitle());
			stmt.setString(2, notice.getNoticeContent());
			stmt.setString(3, notice.getNoticeId());
			System.out.println(stmt + " <-- NoticeDao insertNotice stmt");
			//stmt 실행된 파라미터확인
			
			row  = stmt.executeUpdate();
			//업데이트
			
		}finally {
			if(stmt!=null) {stmt.close();}
			//if(conn!=null) {conn.close();}
			//Dao는 끝이지만 Service는 끝이나지 않았기 때문에 conn.close() 하면 안됨.			
		}
		
		System.out.println(row + " <-- NoticeDao insertNotice row");
		//성공하면 1을 반환 성공안하면 0을 반환
		return row;
	}
	
	public ArrayList<Notice> selectNoticeList(Connection conn, int rowPerPage, int beginRow) throws Exception {
		
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		String sql = "SELECT notice_no"
				+ ", notice_title"
				+ ", notice_content"
				+ ", notice_id"
				+ ", update_date"
				+ ", create_date"
				+ " FROM notice"
				+ " LIMIT ?,?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeId(rs.getString("notice_id"));
				notice.setUpdateDate(rs.getString("update_date"));
				notice.setCreateDate(rs.getString("create_date"));
				
				list.add(notice);
			}
		}finally {
			if(rs!=null) {rs.close();}
			if(stmt!=null) {stmt.close();}
			//if(conn!=null) {conn.close();}
			//Dao는 끝이지만 Service는 끝이나지 않았기 때문에 conn.close() 하면 안됨.
		}
		
		return list;
	}
	
	public int lastPage(Connection conn) throws Exception {
		
		String sql = "SELECT COUNT(*) FROM notice";
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


}
