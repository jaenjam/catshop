<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.CartService"%>
<%@ page import = "repository.*"%>
<%@ page import = "vo.*"%>
<%@ page import = "java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");

	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	String customerId = request.getParameter("customerId");
	
	if(goodsNo == 0){
		System.out.println("cartNo 값이 null");
		response.sendRedirect(request.getContextPath()+"/customer/cartListForm.jsp");
	}
	
	Cart cart = new Cart();
	cart.setGoodsNo(goodsNo);
	cart.setCustomerId(customerId);
	
	CartService cartService = new CartService();
	
	cartService.deleteCart(cart);
	
	System.out.println("삭제 성공");
	
	response.sendRedirect(request.getContextPath()+"/customer/cartListForm.jsp");
%>
