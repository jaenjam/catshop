<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.ReviewService"%>
<%@ page import="repository.*"%>
<%@ page import="vo.Review"%>
<%@ page import="java.util.*"%>

<%
	System.out.println("!!!!insetReviewAction!!!!");

	//인코딩
	request.setCharacterEncoding("utf-8");
	
	if (session.getAttribute("login"+session.getAttribute("user")) == null) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	String id = request.getParameter("id");
	
	System.out.println(id + " <-- id");
	
	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	String reviewContent = request.getParameter("reviewContent");
	String reviewPw = request.getParameter("reviewPw");
	
	Review review = new Review();
	ReviewService reviewService = new ReviewService();
	
	review.setGoodNo(goodsNo);
	review.setReviewContent(reviewContent);
	review.setReviewPw(reviewPw);
	review.setId(id);
	
	reviewService.insertReview(review);
	
	System.out.println("로그인성공");
	
	response.sendRedirect(request.getContextPath()+"/customer/customerGoodsOne.jsp?goods_no=" + goodsNo);

%>