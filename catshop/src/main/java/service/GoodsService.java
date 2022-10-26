package service;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import repository.DBUtil;
import repository.GoodsDao;
import repository.GoodsImgDao;
import repository.ReviewDao;
import vo.Goods;
import vo.GoodsImg;

//�듃�옒�젥�뀡 + action�씠�굹 dao媛� �빐�꽌�뒗 �븞�릺�뒗 �씪
public class GoodsService {
	private GoodsDao goodsDao;
	private DBUtil dbUtil;
	private GoodsImgDao goodsImgDao;
	private ReviewDao reviewDao;
	
	
	public int deleteGoods(Goods goods, GoodsImg goodsImg) {
		System.out.println("!!!!!deleteGoods!!!!!");
		
		Connection conn = null;
		int row = 0;
		
		this.dbUtil = new DBUtil();//�깉濡쒖슫 媛앹껜�깮�꽦
		this.goodsDao = new GoodsDao();
		
		try {
			conn = this.dbUtil.getConnection();
			System.out.println(conn + " <-- GoodsService deleteGoods conn");
			
			//Dao 媛앹껜�깮�꽦
			goodsDao = new GoodsDao();
			goodsImgDao = new GoodsImgDao();
			reviewDao = new ReviewDao();
			/*
			if(reviewDao.reviewCount(conn, goods.getGoodsNo()) != 0) {
				reviewDao.deleteReivew(conn, goods.getGoodsNo());
				
			}
			*/	
			if(goodsImgDao.deleteGoodsImg(conn, goodsImg)==0) {
					throw new Exception(); //�씠誘몄� �궘�젣 �떎�뙣�떆 媛뺤젣濡� rollback�릺寃�(catch�젅濡� �씠�룞)
					
			}
			System.out.println("goods_img �궘�젣�셿猷�");
			
			if(goodsDao.deleteGoods(conn, goods)==0) {
				throw new Exception();
			}
			System.out.println(goods.getGoodsNo() + "<-- goodsNo");
			
			
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback(); //濡ㅻ갚
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
		System.out.println(row + " <-- GoodsService delete �꽦怨�!");
		return goods.getGoodsNo();
	}
/////////////////////////////////////////////////////////////////////////////////////////////	
	
	//4-4. �긽�뭹�닔�젙
	public int updateGoods(Goods goods, GoodsImg goodsImg, int goodsNo) {
		Connection conn = null;
		int row = 0;
		
		this.dbUtil = new DBUtil();//�깉濡쒖슫 媛앹껜�깮�꽦
		this.goodsDao = new GoodsDao();
		
		try {
			conn = this.dbUtil.getConnection();
			System.out.println(conn + " <-- GoodsService updateGoods conn");
			
			//Dao 媛앹껜�깮�꽦
			goodsDao = new GoodsDao();
			goodsImgDao = new GoodsImgDao();
	
			if(goodsDao.updateGoods(conn, goodsNo, goods)==0) {
				throw new Exception();
			}
			System.out.println(goodsNo + "<-- goodsNo");
			
			
			if(goodsNo != 0) {//goodsDao�뿉 �엳�뒗 updateGoods �꽦怨�
				System.out.println("goodsDao�뿉 �엳�뒗 updateGoods �꽦怨�");
				goodsImg.setGoodsNo(goodsNo);
				
				if(goodsImgDao.updateGoodsImg(conn, goodsNo, goodsImg)==0) {
					throw new Exception(); //�씠誘몄� �엯�젰 �떎�뙣�떆 媛뺤젣濡� rollback�릺寃�(catch�젅濡� �씠�룞)
				}
			}
			
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback(); //濡ㅻ갚
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
		System.out.println(row + " <-- GoodsService update �꽦怨�!");
		return goodsNo;
	}
	
///////////////////////////////////////////////////////////////////////////////////////	
	
	//4-3. �긽�뭹&�씠誘몄� �엯�젰
	public int addGoods(Goods goods, GoodsImg goodsImg) {
		
		Connection conn = null;
		int row = 0;
		int goodsNo = 0;

		this.dbUtil = new DBUtil();//�깉濡쒖슫 媛앹껜�깮�꽦
		this.goodsDao = new GoodsDao();
		try {
			conn = this.dbUtil.getConnection();
			System.out.println(conn + " <-- GoodsService addGoods conn");
			
			//Dao 媛앹껜�깮�꽦
			goodsDao = new GoodsDao();
			goodsImgDao = new GoodsImgDao();
	
			goodsNo = goodsDao.insertGoods(conn, goods); //goodsNo媛� AI濡� �옄�룞�깮�꽦�릺�뼱 DB�엯�젰
			System.out.println(goodsNo + "<-- goodsNo");
			
			
			if(goodsNo != 0) {//goodsDao�뿉 �엳�뒗 insertGoods �꽦怨�
				System.out.println("goodsDao�뿉 �엳�뒗 insertGoods �꽦怨�");
				goodsImg.setGoodsNo(goodsNo);
				
				if(goodsImgDao.insertGoodsImg(conn, goodsNo, goodsImg)==0) {
					
					
					throw new Exception(); //�씠誘몄� �엯�젰 �떎�뙣�떆 媛뺤젣濡� rollback�릺寃�(catch�젅濡� �씠�룞)
				}
			}
			
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback(); //濡ㅻ갚
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
		return goodsNo;
	}
	
	//4-2. �젣�뭹�긽�꽭
	public Map<String, Object> getGoodsAndImgOne(int goodsNo) {
		Map<String, Object> map = null;
		
		this.dbUtil = new DBUtil();//�깉濡쒖슫 媛앹껜�깮�꽦
		this.goodsDao = new GoodsDao();
		
		Connection conn = null; //珥덇린�솕
		
		try {
			conn = this.dbUtil.getConnection();
			System.out.println(conn + " <-- GoodsService getGoodsAndImgOne conn");
			
			map = this.goodsDao.selectGoodsAndImgOne(conn, goodsNo);
		
		} catch (Exception e) {
			
		} finally {
			// DB �옄�썝�빐�젣
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("GoodsService �떎�뻾�꽦怨�!");
		return map;
	}
	
	

	public List<Map<String, Object>> getCustomerGoodsListByPage(int rowPerPage, int currentPage, int num){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		Connection conn = null;
		this.dbUtil = new DBUtil();//�깉濡쒖슫 媛앹껜�깮�꽦
		this.goodsDao = new GoodsDao();
		
		System.out.println(currentPage + " <-- currentPage");
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //�옄�룞而ㅻ컠留됱븘二쇨린
			
			System.out.println(beginRow + " <-- GoodsService getCustomerGoodsListByPage beginRow");
			
			//goodsDao.selectCustomerGoodsListByPage(conn, rowPerPage, beginRow) 媛� �꽔�뼱二쇨린
			list = goodsDao.selectCustomerGoodsListByPage(conn, rowPerPage, beginRow, num);
			
			
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
		
		//customerDao�샇異�
		return list;
	}
	
	
	//4-1. �긽�뭹紐⑸줉 List �럹�씠吏��꽕�젙
	public List<Goods> getGoodsListByPage(int rowPerPage, int currentPage){
		List<Goods> list  = new ArrayList<Goods>(); 
		this.goodsDao = new GoodsDao();
		Connection conn = null;
		
		System.out.println(currentPage + " <-- currentPage");
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //�옄�룞而ㅻ컠留됱븘二쇨린
			
			System.out.println(beginRow + " <-- GoodsService getGoodsListByPage beginRow");
			//goodsDao.selectGoodsListByPage(conn, rowPerPage, beginRow) 媛� �꽔�뼱二쇨린
			list = goodsDao.selectGoodsListByPage(conn, rowPerPage, beginRow);
			
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
		
		GoodsDao goodsDao = new GoodsDao();
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //�옄�룞而ㅻ컠留됱븘二쇨린
			
			totalCount = goodsDao.selectTotalCount(conn);
			lastPage = totalCount/rowPerPage;
			
			if(totalCount % rowPerPage != 0) {
				lastPage +=1;
				//�븞�굹�닠 �뼥�뼱吏�硫� �뜑�빐以�
			}
			
			System.out.println(rowPerPage + " <-- rowPerPage");
			
		if(rowPerPage == 0) {
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
		return lastPage;
	}
	
	public int getLastPage(int rowPerPage, String goodsMenu) {
		Connection conn = null;
		int totalCount = 0;
		int lastPage = 0;
		
		GoodsDao goodsDao = new GoodsDao();
		
		try {
			conn = new DBUtil().getConnection();
			conn.setAutoCommit(false); //�옄�룞而ㅻ컠留됱븘二쇨린
			
			totalCount = goodsDao.selectTotalCount(conn, goodsMenu);
			lastPage = totalCount/rowPerPage;
			
			if(totalCount % rowPerPage != 0) {
				lastPage +=1;
				//�븞�굹�닠 �뼥�뼱吏�硫� �뜑�빐以�
			}
			
			System.out.println(rowPerPage + " <-- rowPerPage");
			
		if(rowPerPage == 0) {
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
		return lastPage;
	}
	
	

}
