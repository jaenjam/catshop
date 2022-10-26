package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import repository.CustomerDao;
import repository.DBUtil;
import repository.OutIdDao;
import vo.Customer;

public class CustomerService {
	
	
	//2-3. 관리자 고객관리 - 비밀번호수정
	//고객이 비밀번호 요청주면 관리자가 비밀번호 기본값으로 설정
	public boolean adminUpdateCustomerPw(String customerId){
		System.out.println("!!!!CustomerService adminUpdateCustomerPw!!!!");
		
		Connection conn = null;
		int row = 0;
		
		try {
				conn = new DBUtil().getConnection();
				conn.setAutoCommit(false); //자동커밋막아주기
				
				
				CustomerDao customerDao  = new CustomerDao();
				if(customerDao.adminUpdateCustomerPw(conn, customerId)!=1) {
					throw new Exception();
				}
				conn.commit();
				
				
			if(row == 0) {
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
				return false; //탈퇴실패
				
			}finally {
				try {
					conn.close();
				}catch(SQLException s) {
					s.printStackTrace();
			}
			
			
		}
			return true;
		}
	
	
	//2-2. 관리자 고객관리 - 고객정보강제삭제
	//관리자페이지에서 Customer강제삭제
	public boolean adminDeleteCustomer(Customer customer) {
		Connection conn = null;
		try {
		conn = new DBUtil().getConnection();
		conn.setAutoCommit(false); //executeUpdate()실행시 자동커밋을 막음
		
		//딜리트
		CustomerDao customerDao  = new CustomerDao();
		if(customerDao.adminDeleteCustomer(conn, customer)!=1) {
			throw new Exception();
		}
		
		OutIdDao OutIdDao = new OutIdDao();
		if(OutIdDao.insertOutId(conn, customer.getCustomerId()) != 1) {
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
	
	
	//2-1. 관리자 고객관리 - 고객목록
	public ArrayList<Customer> getCustomerList(int rowPerPage, int currentPage) {
		
		ArrayList<Customer> list = new ArrayList<Customer>();
		
		Connection conn = null;
		
		System.out.println(currentPage + " <-- currentPage");
		int beginRow = (currentPage-1) * rowPerPage;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);			// 자동 커밋 막아줌
		
			CustomerDao customerDao = new CustomerDao();
			
			System.out.println(beginRow + " <-- beginRow");
			
			list = customerDao.selectCustomerList(conn, rowPerPage, beginRow);
			
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
	
	public int getlastPage(int rowPerPage) {
		System.out.println("!!!!getlastPage!!!!");
		Connection conn = null;
		int totalCount = 0;
		int lastPage = 0;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			CustomerDao customerDao = new CustomerDao();
			totalCount = customerDao.lastPage(conn);
	

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
	
	public boolean UpdateCustomerPw(String newPw, String id, String pw) {
		Connection conn = null;
		int row = 0;
		
		try {
				conn = new DBUtil().getConnection();
				conn.setAutoCommit(false); //자동커밋막아주기
				
				
				CustomerDao customerDao  = new CustomerDao();
				if(customerDao.UpdateCustomerPw(conn, newPw, id, pw)!=1) {
					throw new Exception();
				}
				conn.commit();
				
				
			if(row == 0) {
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
				return false; //탈퇴실패
				
			}finally {
				try {
					conn.close();
				}catch(SQLException s) {
					s.printStackTrace();
			}
			
			
		}
			return true;
		}
		
	
	//service에서 트레젝션처리
	public boolean insertCustomer(Customer paramcustomer) {
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //executeUpdate()실행시 자동커밋을 막음
			
			//딜리트
			CustomerDao customerDao  = new CustomerDao();
			if(customerDao.insertCustomer(conn, paramcustomer)!=1) {
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

	
	
	//loginAction.jsp 호출
	public Customer login(Customer paramCustomer) throws Exception {
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.login(paramCustomer);
		return customer;
	}
	
	public boolean removeCustomer(Customer paramCustomer) {
		
			Connection conn = null;
			try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //executeUpdate()실행시 자동커밋을 막음
			
			//딜리트
			CustomerDao customerDao  = new CustomerDao();
			if(customerDao.deleteCustomer(conn, paramCustomer)!=1) {
				throw new Exception();
			}
			OutIdDao OutIdDao = new OutIdDao();
			if(OutIdDao.insertOutId(conn, paramCustomer.getCustomerId()) != 1) {
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
}
