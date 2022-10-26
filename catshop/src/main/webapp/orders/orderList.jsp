<%@page import="java.util.HashMap"%>
<%@page import="vo.Orders"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.OrdersService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>


<%
	if(session.getAttribute("login"+session.getAttribute("user")) == null){
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
		return;
	}

	int rowPerPage = 10; //페이지당 보여줄 행의 수  
	int currentPage = 1; //처음 시작할 현재 페이지
	
	if(request.getParameter("currentPage") != null){ 
		//currentPage가 null이 아니면
		//currentPage값을 넣어준다.
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int lastPage = 0;
	OrdersService ordersService = new OrdersService();
	lastPage = ordersService.getLastPage(rowPerPage);
	List<Map<String, Object>> list = new ArrayList<>();
	list = ordersService.getOrdersListByCustomer(rowPerPage, currentPage, (String)session.getAttribute("id"));
%>
	<div class="container">
			
			<table class="table table-hover" style="margin:auto;">
				<thead>
					<tr>
						<th>주문번호</th>
						<th>상품번호</th>
						<th>상품이름</th>
						<th>상품가격</th>
						<th>주문갯수</th>
						<th>주문가격</th>
						<th>배송주소</th>
						<th>주문상태</th>
						
					</tr>
				</thead>
				<tbody>
					<%
						for(Map<String, Object> map : list){
					%>
					<tr>
						<td><%=map.get("orderNo")%></td>
						<td><%=map.get("goodsNo")%></td>
						<td><%=map.get("goodsName")%></td>
						<td><%=map.get("goodsPrice")%>원</td>
						<td><%=map.get("orderQuantity")%>개</td>
						<td>총 <%=map.get("orderPrice")%>원</td>
						<td><%=map.get("orderAddress")%></td>
						<td><%=map.get("orderState")%></td>
						
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<!-- 페이지 이전 다음 -->
	
			<%
				if(currentPage > 1){ //현재페이지가 1보다 클 경우(1페이지가 아닐경우)
			%>
				<a href="<%=request.getContextPath()%>/orders/orderList.jsp?currentPage=<%=currentPage-1%>">이전</a>
			<%
				}
				
				if(currentPage < lastPage){ //현재페이지가 마지막페이지보다 전일 경우
			%>
				<a href="<%=request.getContextPath()%>/orders/orderList.jsp?currentPage=<%=currentPage+1%>">다음</a>
			<%
				}
			%>
		</div>
		
		<br><br><br>


<%@include file="/template/footer.jsp"%> 