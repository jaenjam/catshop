<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.CartService"%>
<%@ page import = "repository.*"%>
<%@ page import = "vo.*"%>
<%@ page import = "java.sql.*"%>
<%
	request.setCharacterEncoding("utf-8");

	System.out.println(request.getParameter("cartTotal"));
	
	int cartTotalcount = Integer.parseInt(request.getParameter("cartTotal"));
	int cartTotalprice = Integer.parseInt(request.getParameter("goodsPrice"))*Integer.parseInt(request.getParameter("cartTotal"));
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	String customerId = request.getParameter("customerId");
	
	if(goodsNo == 0){
		System.out.println("cartNo 값이 null");
		response.sendRedirect(request.getContextPath()+"/customer/cartListForm.jsp?customerId="+customerId);
	}
	
	Cart cart = new Cart();
	cart.setCartTotalcount(cartTotalcount);
	cart.setGoodsNo(goodsNo);
	cart.setCustomerId(customerId);
	cart.setCartTotalprice(cartTotalprice);
	
	CartService cartService = new CartService();
	
	cartService.updateCart(cart);
	
	System.out.println("업로드 성공");
	
	response.sendRedirect(request.getContextPath()+"/customer/cartListForm.jsp?customerId="+customerId);
%>
