package service;

import java.sql.Connection;
import java.sql.SQLException;

import repository.DBUtil;
import repository.SignDao;

public class SignService {
   private SignDao signDao;
   // return 
   // true : 사용가능한 아이디
   // false : 사용불가능한 아이디
   public boolean idCheck(String id) throws Exception { 
      boolean result = false; // result의 기본 설정값 false
      Connection conn = null;
      
      System.out.println("SignService");
      //SignService 경로확인
      
      try {
    	  conn = new DBUtil().getConnection();
    	  //생성자 생성하고 get.connection()
	      conn.setAutoCommit(result); //execute실행시 자동커밋을 막음
	      
	      this.signDao = new SignDao();
	      
         if(signDao.idCheck(conn, id) == null) {
        	//signDao에 있는 idCheck 결과가 null일 경우 중복되지 않는 사용가능한 아이디임으로
            //result에 true를 넣어준다.
        	 result = true;
         }
         conn.commit();
         
      } catch (Exception e) {
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
      //result값 반환해주기
      return result;
   }
}