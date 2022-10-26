package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import repository.CartDao;
import repository.DBUtil;
import vo.Cart;

public class CartService {
	private DBUtil dbutil;
	private CartDao cartDao;
	
	public List<Map<String, Object>> getCartList(String id){
		List<Map<String, Object>> list = new ArrayList<>();
		
		Connection conn = null;

		this.dbutil = new DBUtil();
		this.cartDao = new CartDao();
		
		try {
			conn = dbutil.getConnection();
		
			System.out.println("CartService getCartList conn --> " + conn);
			
			// conn 자동커밋 해제
			conn.setAutoCommit(false);
			
			list = cartDao.selectCartList(conn, id);
			
			// �뵒踰꾧퉭
			System.out.println("list : " + list);
			
			if(list==null) {
				System.out.println("CartService getCartList실패");
				
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
		System.out.println("CartService �떎�뻾�꽦怨�!");
		return list;
	}
	
	public boolean insertCart(Cart cart){
		Connection conn = null;
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //executeUpdate()�떎�뻾�떆 �옄�룞而ㅻ컠�쓣 留됱쓬
			
			//�뵜由ы듃
			CartDao cartDao  = new CartDao();
			if(cartDao.insertCart(conn, cart)!=1) {
				cartDao.updateCart(conn, cart);
				throw new Exception();
			}
			
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace(); //console�뿉 �삁�쇅硫붿꽭吏� 異쒕젰
			try {
				conn.rollback();
			} catch (SQLException e1) { //sql �삤瑜섎궇 寃쎌슦
				System.out.println("�옣諛붽뎄�땲�뿉 �뱾�뼱媛��엳�뒿�땲�떎!");
				e1.printStackTrace();
			}
			return false; //�깉�눜�떎�뙣
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return true;
	}
	
	
	public boolean deleteCart(Cart paramcart) {
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //executeUpdate()�떎�뻾�떆 �옄�룞而ㅻ컠�쓣 留됱쓬
			
			CartDao cartDao = new CartDao();
			if(cartDao.deleteCart(conn, paramcart) != 1) {
				throw new Exception();
			}
			
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace(); //console�뿉 �삁�쇅硫붿꽭吏� 異쒕젰
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false; //�깉�눜�떎�뙣
			
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return true;
	}
	
	public boolean updateCart(Cart paramcart) {
		Connection conn = null;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //executeUpdate()�떎�뻾�떆 �옄�룞而ㅻ컠�쓣 留됱쓬
			
			CartDao cartDao = new CartDao();
			if(cartDao.updateCart(conn, paramcart) != 1) {
				throw new Exception();
			}
			
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace(); //console�뿉 �삁�쇅硫붿꽭吏� 異쒕젰
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false; //�깉�눜�떎�뙣
			
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
