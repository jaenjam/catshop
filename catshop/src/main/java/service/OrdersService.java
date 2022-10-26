package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import repository.DBUtil;
import repository.OrdersDao;
import vo.Orders;

public class OrdersService {
	
	//5-3. 二쇰Ц�긽�깭媛� �닔�젙
	public int modifyOrdersState(Orders ordersState) {
		Connection conn = null;
		int state = 0;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
		
			OrdersDao ordersDao = new OrdersDao();
			state = ordersDao.updateOrdersState(conn, ordersState);
			
			// �뵒踰꾧퉭
			System.out.println(state + " <-- state");
			
			if(state == 0) {	// �떎�뙣 �삁�쇅泥섎━
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
		
		return state;
	}
	
	//List �럹�씠吏� 蹂댁씠寃� �븯湲�
	public List<Map<String, Object>> getOrdersList(int rowPerPage, int currentPage){
		List<Map<String, Object>> list = new ArrayList<>(); //�떎�삎�꽦
		
		OrdersDao ordersDao = new OrdersDao();
		Connection conn = null;
		
		System.out.println(currentPage + " <-- currentPage");
		int beginRow = (currentPage-1) * rowPerPage;
		
		
		try {
			
		conn = new DBUtil().getConnection();
		conn.setAutoCommit(false); //�옄�룞而ㅻ컠留됱븘二쇨린
		System.out.println(beginRow + " <-- beginRow");
		//ordersDao.selectOrdersList(Connection conn, int rowPerPage, int beginRow) 媛� �꽔�뼱二쇨린
		list = ordersDao.selectOrdersList(conn, rowPerPage, beginRow);
		
		System.out.println(list + " <-- list");
		
		if(list == null) {
			throw new Exception();
		}
		conn.commit();
		
	}catch(Exception e){
		e.printStackTrace();
		try {
			conn.rollback();
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}finally {
		try {
			conn.close();
		}catch(SQLException s) {
			s.printStackTrace();
	}
	
	
}
	return list;
}
	
	public int getLastPage(int rowPerPage) {
		Connection conn = null;
		int totalCount = 0;
		int lastPage = 0;
		
		OrdersDao ordersDao = new OrdersDao();
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //�옄�룞而ㅻ컠留됱븘二쇨린
			
			totalCount = ordersDao.selectTotalCount(conn);
			lastPage = totalCount/rowPerPage;
			
			if(totalCount % rowPerPage != 0) {
				lastPage +=1;
				//�븞�굹�닠 �뼥�뼱吏�硫� �뜑�빐以�
			}
			
			System.out.println(rowPerPage + " <-- rowPerPage");
			
			
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException s) {
				s.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			}catch(SQLException s) {
				s.printStackTrace();
		}
		
		
	}
		return lastPage;
	}
	
	
	// 고객 한명의 주문 리스트(관리자, 고객 페이지 둘다)
	public List<Map<String, Object>> getOrdersListByCustomer(int rowPerPage, int currentPage, String customerId) {
		// 리턴값
		List<Map<String, Object>> list = new ArrayList<>();
		// 초기화
		Connection conn = null;
		OrdersDao ordersDao = new OrdersDao();
		
		int beginRow = (currentPage - 1) * rowPerPage;

		try {
			conn = new DBUtil().getConnection();
			// 디버깅
			System.out.println(conn + "<-- getOrdersList conn");
			// 메서드실행
			list = ordersDao.selectOrdersListByCustomer(conn, customerId, rowPerPage, beginRow);
			if (list != null) {
				System.out.println("실행 성공");
			} else {
				System.out.println("실행 실패");
				throw new Exception(); // 예외처리
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
}
