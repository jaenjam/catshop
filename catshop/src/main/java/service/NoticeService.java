package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import repository.DBUtil;
import repository.NoticeDao;
import vo.Notice;

public class NoticeService {
	
	public boolean adminUptdateNotice(Notice notice) {
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			
			//executeUpdate()실행시 자동커밋을 막음
			conn.setAutoCommit(false); 
			
			//딜리트
			NoticeDao noticeDao  = new NoticeDao();
			if(noticeDao.adminUpdateNotice(conn, notice)!=1) {
				throw new Exception();
			}
		
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
	
	//공지 숨기기 기능
	public int modifyNoticeAction(Notice noticeShow) {
		Connection conn = null;
		int show = 0;
		try {
		conn = new DBUtil().getConnection();
		conn.setAutoCommit(false); //executeUpdate()실행시 자동커밋을 막음
		
		//업데이트
		NoticeDao noticeDao  = new NoticeDao();
		show = noticeDao.updateNoticeShow(conn, noticeShow);
		
		System.out.println(noticeShow + " <-- noticeShow");
		
		if(show == 0) {//실패시 오류발생
			throw new Exception();
		}
		
		conn.commit();
		
		}catch(Exception e) {
			e.printStackTrace(); //console에 예외메세지 출력
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
			return show;
	}
		

/*
	public boolean adminDeleteNotice(Notice notice) {
		Connection conn = null;
		try {
		conn = new DBUtil().getConnection();
		conn.setAutoCommit(false); //executeUpdate()실행시 자동커밋을 막음
		
		//딜리트
		NoticeDao noticeDao  = new NoticeDao();
		if(noticeDao.adminDeleteNotice(conn, notice)!=1) {
			throw new Exception();
		}
		
		conn.commit();
		
		}catch(Exception e) {
			e.printStackTrace(); //console에 예외메세지 출력
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false; //탈퇴실패
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return true;
	}
*/
	
	public Notice getNoticeOne(int noticeNo){
		Notice notice = null;
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			System.out.println(conn + "<--NoticeService getNoticeOne");
			
			NoticeDao noticeDao = new NoticeDao();
			
			notice = noticeDao.selectNoticeOne(conn, noticeNo);
			
			if(notice == null) {
				throw new Exception();
			}
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace(); //console에 예외메세지 출력
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
			return notice;
	}
	
	public boolean addNotice(Notice notice) {
		System.out.println("!!!!NoticeService addNotice!!!!");
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //executeUpdate()실행시 자동커밋을 막음
			
			//딜리트
			NoticeDao noticeDao  = new NoticeDao();
			if(noticeDao.insertNotice(conn, notice)==0) {
				System.out.println("NoticeService -> Dao 갔다가 돌아올때 에러");
				throw new Exception();
			}
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace(); //console에 예외메세지 출력
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false; //공지추가실패
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return true; //공지추가성공
	}

	
	
	public ArrayList<Notice> getNoticeList(int rowPerPage, int currentPage){
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		Connection conn = null;
		
		System.out.println(currentPage + " <-- currentPage");
		int beginRow = (currentPage-1) * rowPerPage;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);			// 자동 커밋 막아줌
		
			NoticeDao noticeDao = new NoticeDao();
			
			System.out.println(beginRow + " <-- beginRow");
			
			list = noticeDao.selectNoticeList(conn, rowPerPage, beginRow);
			
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
	
	//마지막페이지 찾기
	public int getlastPage(int rowPerPage) {
		System.out.println("!!!!getlastPage!!!!");
		Connection conn = null;
		int totalCount = 0;
		int lastPage = 0;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			NoticeDao noticeDao = new NoticeDao();
			totalCount = noticeDao.lastPage(conn);
	

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
}
