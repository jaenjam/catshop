<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="vo.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.CartService"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>

<%
	System.out.println("!!!!cartListForm!!!!");

	if (session.getAttribute("id") == null) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	String id = request.getParameter("customerId");
	System.out.println(id + " <-- id");
	
	CartService cartService = new CartService();
	
	List<Map<String, Object>> list = cartService.getCartList(id);
	
%>

    <!-- Breadcrumb Section Begin -->
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
				<br><br>
					<div class="product__discount">
                        <div class="section-title related__product__title" style="margin:auto">
                            <h2>장바구니</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                	<th></th>
                                    <th class="shoping__product">상품명</th>
                                    <th>상품가격</th>
                                    <th>상품갯수</th>
                                    <th>총가격</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
						<%
							for(Map<String, Object> m : list){
						%>
                                <tr>
                                	<td><input type="checkbox" name="goodsNo" value="<%=m.get("goodsNo")%>"></td>
                                    <td class="shoping__cart__item">
                                        <img src="<%=request.getContextPath()%>/upload/<%=m.get("filename")%>" alt="">
                                        <h5><%=m.get("goodsName")%></h5>
                                    </td>
                                    <td class="shoping__cart__price">
                                        <%=m.get("goodsPrice")%>원
                                    </td>
                                    <td class="shoping__cart__quantity">
                                    <form action="<%=request.getContextPath()%>/cart/updateCartAction.jsp?goodsNo=<%=m.get("goodsNo")%>&customerId=<%=m.get("customerId")%>&goodsPrice=<%=m.get("goodsPrice")%>" method="post">
                                        <div class="quantity">
                                            <div class="pro-qty">
                                                <input type="text" value="<%=m.get("cartTotalcount")%>" id="cartTotal" name="cartTotal">
                                               
                                            </div>
                                             	<button type="submit">변경</button>
                                        </div>
                                    </form>
                                    </td>
                                    <td class="shoping__cart__total">
                                       <%=m.get("cartTotalprice")%>원
                                    </td>
                                    <td class="shoping__cart__item__close">
                                        <a href="<%=request.getContextPath()%>/orders/deleteCartAction.jsp?goodsNo=<%=m.get("goodsNo")%>&customerId=<%=m.get("customerId")%>">
                                        	<span class="icon_close"></span>
                                        </a>
                                    </td>
                                </tr>
                                
						<%
							}
						%>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?customerId=<%=session.getAttribute("id")%>" class="primary-btn cart-btn">쇼핑계속하기</a>
                        <a href="<%=request.getContextPath()%>/customer/cartListForm.jsp?customerId=<%=session.getAttribute("id")%>" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                            쇼핑카트 갱신하기</a>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="shoping__checkout">
                    <!-- 
                        <h5>Cart Total</h5>
                        <ul>
                            <li>Subtotal <span>$454.98</span></li>
                            <li>Total <span>$454.98</span></li>
                        </ul>
                     -->
                        <a href="<%=request.getContextPath()%>/orders/insertOrdersForm.jsp" class="primary-btn">주문하러가기</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->


<%@include file="/template/footer.jsp"%> 