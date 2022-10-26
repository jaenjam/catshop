<%@page import="java.util.HashMap"%>
<%@page import="vo.Orders"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="service.OrdersService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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

	int rowPerPage = 2; //페이지당 보여줄 행의 수  
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
	list = ordersService.getOrdersList(rowPerPage, currentPage);
%>
<div class="container">
	<div style=" text-align:center;">
		<div style="margin-bottom : 40px">
		<br><br>
			 <div class="btn-group">
		   		<a href="<%=request.getContextPath()%>/login.jsp">
		   			<button type="button" class="btn btn-light" style="background-color: #7fad39; color:white;">
		   				로그인홈
		   			</button>
		   		</a>
		   		<a href="<%=request.getContextPath()%>/employee/EmployeeList.jsp">
		   			<button type="button" class="btn btn-light" style="background-color: #7fad39; color:white;">
		   				사원관리
		   			</button>
		   		</a>
		   		<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp">
		   			<button type="button" class="btn btn-light" style="background-color: #7fad39; color:white;">
		   				상품관리
		   			</button>
		   		</a>
		   		<a href="<%=request.getContextPath()%>/orders/adminOrderList.jsp">
		   			<button type="button" class="btn btn-light" style="background-color: #7fad39; color:white;">
		   				주문관리
		   			</button>
		   		</a>
		   		<a href="<%=request.getContextPath()%>/admin/adminCustomerList.jsp">
		   			<button type="button" class="btn btn-light" style="background-color: #7fad39; color:white;">
		   				고객관리
		   			</button>
		   		</a>
		   		<a href="<%=request.getContextPath()%>/notice/adminNoticeList.jsp">
		   			<button type="button" class="btn btn-light" style="background-color: #7fad39; color:white;">
		   				공지관리
		   			</button>
		   		</a>
  			</div><!-- button group end -->
		</div>
		<div  style="width: 100%; overflow: auto;">
			
			<table class="table table-hover" style="margin:auto;white-space: nowrap;">
				<thead>
					<tr>
						<th>주문번호</th>
						<th>주문자아이디</th>
						<th>상품번호</th>
						<th>상품이름</th>
						<th>상품가격</th>						
						<th>주문갯수</th>
						<th>주문가격</th>
						<th>배송주소</th>
						<th>주문진행상태</th>
						<th>주문한날짜</th>
						<th>주문수정날짜</th>

					</tr>
				</thead>
				<tbody>
					<%
						for(Map<String, Object> map : list){
					%>
					<tr>
						<td><%=map.get("orderNo")%></td>
						<td><a href="<%=request.getContextPath()%>/orders/adminOrderOne.jsp?customerId=<%=map.get("customerId")%>"><%=map.get("customerId")%></a></td>
						<td><%=map.get("goodsNo")%></td>
						<td><%=map.get("goodsName")%></td>
						<td><%=map.get("goodsPrice")%>원</td>
						<td><%=map.get("orderQuantity")%>개</td>
						<td>총 <%=map.get("orderPrice")%>원</td>
						<td><%=map.get("orderAddress")%></td>
						<td>
							<form action="<%=request.getContextPath()%>/orders/updateOrderStateAction.jsp" method="post">
								<input type="hidden" name="orderNo" value="<%=map.get("orderNo")%>">
								<select name="state">
									<%
										if(map.get("orderState").equals("배송전")){
									%>
										<option selected="selected">배송전</option>	<!-- 디폴트값은 배송전로 설정되어있게 -->
										<option>배송중</option>
										<option>배송완료</option>
										<option>주문취소</option>
									<%
										} else if (map.get("orderState").equals("배송중")) { //active값이 배송전이면
									%>
										<option>배송전</option>
										<option selected="selected">배송중</option>	<!-- 디폴트값은 배송중로 설정되어있게 -->
										<option>배송완료</option>
										<option>주문취소</option>
									<%
										}else if (map.get("orderState").equals("배송완료")) {
									%>
										<option>배송전</option>
										<option>배송중</option>
										<option selected="selected">배송완료</option>	<!-- 디폴트값은 배송완료로 설정되어있게 -->
										<option>주문취소</option>
									<%
										}else{
									%>
										<option>배송전</option>
										<option>배송중</option>
										<option>배송완료</option>
										<option selected="selected">주문취소</option>	<!-- 디폴트값은 주문취소로 설정되어있게 -->
									<%
										}
									%>
								</select>
								<button type="submit">변경</button>
							</form>
						</td>
						
						
						<td><%=map.get("createDateO")%></td>
						<td><%=map.get("updateDateO")%></td>
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
				<a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp?currentPage=<%=currentPage-1%>">이전</a>
			<%
				}
				
				if(currentPage < lastPage){ //현재페이지가 마지막페이지보다 전일 경우
			%>
				<a href="<%=request.getContextPath()%>/admin/adminOrderList.jsp?currentPage=<%=currentPage+1%>">다음</a>
			<%
				}
			%>
			</form>
		</div>
	</div>
</div>