<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="repository.*"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.*"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>

<%
	if(session.getAttribute("login"+session.getAttribute("user")) == null){
			response.sendRedirect(request.getContextPath() + "/loginForm.jsp?errorMsg = login first");
			return;
	}
	int goodsNo = Integer.parseInt(request.getParameter("goods_no"));
	System.out.println("customerGoodsOne.jsp goodsNo : " + goodsNo);
	
	GoodsService goodsService = new GoodsService();// 생성
	
	Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);
	
	System.out.println("customerGoodsOne.jsp map : " + map.toString());
	
/////////////////////////////////////////////////////////////////////////////////////
	
	ReviewService reviewService = new ReviewService();
	List<Review>list = new ArrayList<Review>();
	list = reviewService.getReviewList(goodsNo);
	
%>

    <!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img class="product__details__pic__item--large"
                                src="<%=request.getContextPath()%>/upload/<%=map.get("fileName")%>" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3><%=map.get("goodsName")%></h3>
                        <div class="product__details__rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star-half-o"></i>
                            <span>(18 reviews)</span>
                        </div>
                        <div class="product__details__price"><%=map.get("goodsPrice")%> 원</div>
			      	 	
			      	 	<form action="<%=request.getContextPath()%>/customer/insertCustomerGoodsAction.jsp" method="post">
				      	 	<input type="hidden" name="goodsNo" value="<%=map.get("goodsNo")%>">
				      	 	<input type="hidden" name="goodsPrice" value="<%=map.get("goodsPrice")%>">
	                        <input type="hidden" name="id" value="<%=session.getAttribute("id")%>">
	                        <div class="product__details__quantity">
	                            <div class="quantity">
	                                <div class="pro-qty">
	                                    <input type="text" name="cartTotalcount" value="1">
	                                </div>
	                            </div>
	                        </div>
	                        <button class="primary-btn">장바구니추가</button>
                        </form>
                        <ul>
                            <li><b>배송비</b> <span>지금사시면 무료!</span></li>
                            <li><b>Share on</b>
                                <div class="share">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-3" role="tab"
                                    aria-selected="false">Reviews <span>(1)</span></a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane" id="tabs-3" role="tabpanel">
                                <div class="product__details__tab__desc">
                                    <h6>Products Infomation</h6>
                                    <p>Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui.
                                        Pellentesque in ipsum id orci porta dapibus. Proin eget tortor risus.
                                        Vivamus suscipit tortor eget felis porttitor volutpat. Vestibulum ac diam
                                        sit amet quam vehicula elementum sed sit amet dui. Donec rutrum congue leo
                                        eget malesuada. Vivamus suscipit tortor eget felis porttitor volutpat.
                                        Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem. Praesent
                                        sapien massa, convallis a pellentesque nec, egestas non nisi. Vestibulum ac
                                        diam sit amet quam vehicula elementum sed sit amet dui. Vestibulum ante
                                        ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;
                                        Donec velit neque, auctor sit amet aliquam vel, ullamcorper sit amet ligula.
                                        Proin eget tortor risus.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->


<%@include file="/template/footer.jsp"%> 