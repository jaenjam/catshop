<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="repository.*"%>
<%@ page import="vo.*"%>
<%
	if(session.getAttribute("login"+session.getAttribute("user")) == null){
			response.sendRedirect(request.getContextPath() + "/loginForm.jsp?errorMsg = login first");
			return;
	}
	

	//전페이지에서 입력받은, 넘긴 값 받아오기
	//int count = Integer.parseInt(request.getParameter("cartCount"));
	int cartTotalcount = Integer.parseInt(request.getParameter("cartTotalcount"));;
	int cartTotalprice = Integer.parseInt(request.getParameter("goodsPrice"))*Integer.parseInt(request.getParameter("cartTotalcount"));
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	String id = request.getParameter("id");
	
	//값 확인
	System.out.println("insertCustomerGoodsAction.jsp cartTotalprice : " + cartTotalprice);
	System.out.println("insertCustomerGoodsAction.jsp cartTotalcount : " + cartTotalcount);
	System.out.println("insertCustomerGoodsAction.jsp goodsNo : " + goodsNo);
	
	//인코딩
	request.setCharacterEncoding("utf-8"); 
	
	//생성자 생성
	Cart cart = new Cart();
	
	//값 넣어주기
	cart.setGoodsNo(goodsNo);
	cart.setCartTotalcount(cartTotalcount);
	cart.setCartTotalprice(cartTotalprice);
	
	//지금현재 접속한 id값 id을 customerId값에 넣어준다.
	cart.setCustomerId((String)session.getAttribute("id"));
	
	CartService cartService = new CartService();
	cartService.insertCart(cart);
	
	//성공
	System.out.println("장바구니에 추가 완료");
	
	//상세보기 페이지로 이동한다.
	response.sendRedirect(request.getContextPath()+"/customer/cartListForm.jsp?customerId="+id);
	
	
	
%>
