package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import repository.DBUtil;
import repository.ReviewDao;
import vo.Review;

public class ReviewService {
	public boolean insertReview(Review review) {
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			
			//executeUpdate()실행시 자동커밋을 막음
			conn.setAutoCommit(false); 
			
			ReviewDao reviewDao  = new ReviewDao();
			
			//commentNo가 자동으로 생성되는데, 그 값이 DB에 입력됨.
			reviewDao.insertReview(conn, review);
		
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
	
	public ArrayList<Review> getReviewList(int goodsNo){
		ArrayList<Review> list = new ArrayList<Review>();
		
		System.out.println("!!! ReviewSerview getReviewList!!!");
		
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);			// 자동 커밋 막아줌
		
			ReviewDao reviewDao = new ReviewDao();
			
			list = reviewDao.selectReviewList(conn, goodsNo);
			
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
}
