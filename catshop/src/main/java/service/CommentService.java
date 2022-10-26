package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import repository.DBUtil;
import repository.CommentDao;
import vo.Comment;

public class CommentService {
	
	//insert
	public boolean insertComment(Comment comment) {
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			
			//executeUpdate()실행시 자동커밋을 막음
			conn.setAutoCommit(false); 
			
			CommentDao commentDao  = new CommentDao();
			
			//commentNo가 자동으로 생성되는데, 그 값이 DB에 입력됨.
			commentDao.insertComment(conn, comment);
			
		
			conn.commit();
			
			//Exception이 생기면
		}catch(Exception e) {
			
			//console에 예외메세지 출력
			e.printStackTrace();
			try {
				//그리고 롤백해준다. 이전 상태로 돌려주기
				conn.rollback();
				
				//sql에서 오류가 발생하면
			} catch (SQLException e1) {
				
				//exception 부분을 출력해서 보여준다.
				e1.printStackTrace();
			}
			return false; //공지수정실패
			
		} finally {//마지막으로
			try {
				//차례대로 connection.close 닫아주기
				conn.close();
				
				//sql 오류가 나면
			} catch (SQLException e) {
				//console에 예외메세지를 출력해준다.
				e.printStackTrace();
			}
		}
			return true;//공지수정성공
	}
	
	public ArrayList<Comment> getCommentList(int noticeNo){
		ArrayList<Comment> list = new ArrayList<Comment>();
		
		Connection conn = null;
		
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);			// 자동 커밋 막아줌
		
			CommentDao commentDao = new CommentDao();
			
			list = commentDao.selectCommentList(conn, noticeNo);
			
			// 디버깅
			System.out.println("list : " + list);
			
			if(list==null) {	
				throw new Exception();
			}
			
			conn.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return list;
	}
/*	
	public int getlastPage(int rowPerPage) {
		System.out.println("!!!!getlastPage!!!!");
		Connection conn = null;
		int totalCount = 0;
		int lastPage = 0;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			CommentDao commentDao = new CommentDao();
			totalCount = commentDao.lastPage(conn);
	

			lastPage = totalCount/rowPerPage;
			
			if(totalCount % rowPerPage != 0) {
				lastPage +=1;
				//안나눠 떨어지면 더해줌
			}
			
			System.out.println(rowPerPage + " <-- rowPerPage");
			
	     if(rowPerPage == 0) {	// 실패시 예외처리
	    	 throw new Exception();
	     }
				conn.commit();
				
			} catch(Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
	      return lastPage;
	}
*/
}
