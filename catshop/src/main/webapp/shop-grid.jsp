<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@page import="java.util.*"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>

<%
	//Controller : java class <-- Servet
	int rowPerPage = 20;
	int num = 0;  
	if(request.getParameter("rowPerPage") != null){
		rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
	}
	
	int currentPage = 1;
	if(request.getParameter("currentPage") != null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	GoodsService goodsService = new GoodsService();
	
	//list

%>

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-3">
                    <div class="product__discount">
                        <div class="section-title product__discount__title">
                            <h2>Sale Off</h2>
                        </div>
                    </div>
                    <div class="row">
			<%
				
				List<Map<String, Object>> list = goodsService.getCustomerGoodsListByPage(rowPerPage, currentPage, num);
			
			
				int i = 1;
				for(Map<String, Object> m : list){
			%>
			
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" 
                                data-setbg="<%=request.getContextPath()%>/upload/<%=m.get("fileName")%>">
                                    <ul class="product__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                        <li><a href="<%=request.getContextPath()%>/customer/customerGoodsOne.jsp?goods_no=<%=m.get("goodsNo")%>"><i class="fa fa-retweet"></i></a></li>
                                        <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="#"><%=m.get("goodsName")%></a></h6>
                                    <h5><%=m.get("goodsPrice")%>원</h5>
                                </div>
                            </div>
                        </div>
			<%
				}
			%>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

<%@include file="/template/footer.jsp"%> 