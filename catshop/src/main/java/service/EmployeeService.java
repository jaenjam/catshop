package service;

import java.sql.*;
import java.util.ArrayList;

import repository.DBUtil;
import repository.EmployeeDao;
import repository.OutIdDao;
import vo.Employee;

public class EmployeeService {
	//service에서 트레젝션처리
	// employeeList.jsp 페이징 처리
	public ArrayList<Employee> getEmployeeList(int rowPerPage, int currentPage){
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		Connection conn = null;
		
		System.out.println(currentPage + " <-- currentPage");
		int beginRow = (currentPage-1) * rowPerPage;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);			// 자동 커밋 막아줌
		
			EmployeeDao employeeDao = new EmployeeDao();
			
			System.out.println(beginRow + " <-- beginRow");
			
			list = employeeDao.selectEmployeeList(conn, rowPerPage, beginRow);
			
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
	}	// end getEmployeeList
	
	
	//마지막페이지 찾기
	public int getlastPage(int rowPerPage) {
		System.out.println("!!!!getlastPage!!!!");
		Connection conn = null;
		int totalCount = 0;
		int lastPage = 0;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
			
			EmployeeDao employeeDao = new EmployeeDao();
			totalCount = employeeDao.lastPage(conn);
	

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
	
	//3-2. 활성화 값변경
	// active 값 변경구간
	public int modifyEmployeeActive(Employee employeeActive) {
		
		Connection conn = null;
		int active = 0;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false);
		
			EmployeeDao employeeDao = new EmployeeDao();
			active = employeeDao.updateEmployeeActive(conn, employeeActive);
			
			// 디버깅
			System.out.println("active : " + active);
			
			if(active == 0) {	// 실패 예외처리
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
		return active;
		
	}
	
	
	public boolean insertEmployee(Employee paramEmployee) {
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //executeUpdate()실행시 자동커밋을 막음
			
			//딜리트
			EmployeeDao employeeDao  = new EmployeeDao();
			if(employeeDao.insertEmployee(conn, paramEmployee)!=1) {
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
	public Employee login(Employee employee) throws Exception {
		EmployeeDao employeeDao = new EmployeeDao();
		Employee employeee = employeeDao.login(employee);
		return employeee;
	}
	
	public boolean removeEmployee(Employee paramEmployee) {
		
			Connection conn = null;
			try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //executeUpdate()실행시 자동커밋을 막음
			
			//딜리트
			EmployeeDao employeeDao  = new EmployeeDao();
			if(employeeDao.deleteEmployee(conn, paramEmployee)!=1) {
				throw new Exception();
			}
			OutIdDao OutIdDao = new OutIdDao();
			if(OutIdDao.insertOutId(conn, paramEmployee.getEmployeeId()) != 1) {
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
