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
	
	int lastPage = 0;
	
	GoodsService goodsService = new GoodsService();
	
	//list
	String goodsMenu = request.getParameter("goodsMenu");
%>
<%
	
	if(goodsMenu.equals("All")){
	//모든상품
	num = 0;	
	}

	//신상품
	else if(goodsMenu.equals("New")){
		num = 1;
	}
	
	//캣타워
	else if(goodsMenu.equals("캣타워")){
		num = 2;
	}

	//생활/리빙용품
	else if(goodsMenu.equals("생활/리빙용품")){
		num = 3;
	}
	
	//미용/목욕용품
	else if(goodsMenu.equals("목욕/미용용품")){
		num = 4;
	}
	
	//배변용품
	else if(goodsMenu.equals("배변용품")){
		num = 5;
	}
	
	//건식사료
	else if(goodsMenu.equals("건식사료")){
		num = 6;
	}
	
	//습식사료
	else if(goodsMenu.equals("습식사료")){
		num = 7;
	}
	
	//고양이간식&영양제
	else if(goodsMenu.equals("고양이간식")){
		num = 8;
	}
	
	//장난감
	else if(goodsMenu.equals("장난감")){
		num = 9;
	}
	
	//스크레쳐
	else if(goodsMenu.equals("스크레쳐")){
		num = 10;
	}
	


	List<Map<String, Object>> list = goodsService.getCustomerGoodsListByPage(rowPerPage, currentPage, num);

	
	lastPage = goodsService.getLastPage(rowPerPage, goodsMenu);
%>
    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="product__discount">
                        <div class="section-title related__product__title">
                            
                            <%
                            	if(num==0){
                            %>
                            	<h2>상품목록</h2>
                            <%
                            	}else if(goodsMenu.equals("New")){
                            		
                            %>
                            	<h2>신상품</h2>
                            <%
                            	}else if(goodsMenu.equals("고양이간식")){
                            %>
                            	<h2>고양이간식&영양제</h2>
                            <%
                            	}else{
                            %>
                            	<h2><%=goodsMenu%></h2>
                            <%
                            	}
                            %>
                            
                            
                        </div>
                    </div>
                    <div class="row">
                    <div class="col-lg-12">
                    <span style="float:right;">
                    <select id="rowPerPage">
                		<option value="20">
                		20
                		</option>
                		<option value="40">
                			40
                		</option>
                    	<option value="100">
                			100
                		</option>
                	</select>
                	</span>
                	</div>
                	<br><br>
			<%
			
			
				int i = 1;
				for(Map<String, Object> m : list){
			%>
			
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" 
                                data-setbg="<%=request.getContextPath()%>/upload/<%=m.get("fileName")%>">
                                    <ul class="product__item__pic__hover">
                                        <li><a href="<%=request.getContextPath()%>/customer/customerGoodsOne.jsp?goods_no=<%=m.get("goodsNo")%>"><i class="fa fa-retweet"></i></a></li>
                                        
                                        <%
                                    	if (session.getAttribute("id") == null) {
											
                                        %>
                                            <li><a href="<%=request.getContextPath()%>/loginForm.jsp">
                                        		<i class="fa fa-shopping-cart"></i>
                                        	</a></li>
                                        <%
											}
                                        %>
                                        
                                       	<li>
                                        	<a href="<%=request.getContextPath()%>/customer/insertCustomerGoodsAction.jsp?goodsNo=<%=m.get("goodsNo")%>&goodsPrice=<%=m.get("goodsPrice")%>&id=<%=session.getAttribute("id")%>&cartTotalcount=1">
                                        		<i class="fa fa-shopping-cart"></i>
                                        	</a>
                                       	</li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="<%=request.getContextPath()%>/customer/customerGoodsOne.jsp?goods_no=<%=m.get("goodsNo")%>"><%=m.get("goodsName")%></a></h6>
                                    <h5><%=m.get("goodsPrice")%>원</h5>
                                </div>
                            </div>
                        </div>
			<%
				}
			%>

                    </div>
			<div style="text-align:center">
				
			<%
				if(currentPage > 1){ //현재페이지가 1보다 클 경우(1페이지가 아닐경우)
			%>
				<a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=<%=goodsMenu%>&currentPage=<%=currentPage-1%>">
					<button class="btn btn-light" type="button" style="background-color: #7fad39; color:white;">
						이전
					</button>
				</a>
			<%
				}
				
				if(currentPage < lastPage){ //현재페이지가 마지막페이지보다 전일 경우
			%>
				<a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=<%=goodsMenu%>&currentPage=<%=currentPage+1%>">
					<button class="btn btn-light" type="button" style="background-color: #7fad39; color:white;">
						다음
					</button>
				</a>
			<%
				}
			%>
			<br>
			<br>
			</div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

<%@include file="/template/footer.jsp"%> 