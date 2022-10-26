<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.oreilly.servlet.MultipartRequest"%>
<%@ page import = "com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import = "service.*"%>
<%@ page import = "java.net.URLEncoder"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "repository.*"%>
<%@ page import = "vo.*"%>
<%@ page import = "java.io.File"%>
<%
	//Goods
	GoodsService goodsService = new GoodsService();
	Goods goods = new Goods();
	
	//GoodsImg
	GoodsImgDao goodsImgDao = new GoodsImgDao();
	GoodsImg goodsImg = new GoodsImg();
	
	request.setCharacterEncoding("utf-8"); //인코딩
	
	String dir = request.getServletContext().getRealPath("/upload"); //업로드 할 폴더 위치지정
	System.out.println(dir);
	
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	
	String fileName = request.getParameter("fileName");
	
	System.out.println(fileName + " <-- fileName");
	
	goods.setGoodsNo(goodsNo);
	
	goodsImg.setGoodsNo(goodsNo);
	
	goodsService.deleteGoods(goods, goodsImg);
	
	File f = new File(dir + "\\" + fileName);
	
	if(f.exists()){//파일까지 삭제하고 새로 업로드 해야되기때문에, 수정란에도 필요
		f.delete();
	}
	
	response.sendRedirect(request.getContextPath()+"/admin/adminGoodsList.jsp");
	
	
%>