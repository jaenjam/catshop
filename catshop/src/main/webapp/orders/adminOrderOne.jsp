<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="service.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	System.out.println("!!!!adminOrderOne!!!!");	

	if (session.getAttribute("login"+session.getAttribute("user")) == null) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	if (!(session.getAttribute("user").equals("Employee"))) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	String id = request.getParameter("customerId");
	System.out.println(id + " <-- id");
	
	CartService cartService = new CartService();
	
	List<Map<String, Object>> list = cartService.getCartListOne(id);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="margin-bottom : 40px">
		<a href="<%=request.getContextPath()%>/index.jsp">홈으로</a>
		<a href="<%=request.getContextPath()%>/EmployeeList.jsp">사원관리</a>
		<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp">상품관리</a><!-- 상품목록/등록/수정/삭제(주문이 없는경우) -->
		<a href="<%=request.getContextPath()%>/orders/adminOrderList.jsp">주문관리</a><!-- 주문목록/수정 -->
		<a href="<%=request.getContextPath()%>/admin/adminCustomerList.jsp">고객관리</a><!-- 고객목록/강제탈퇴/비밀번호수정(전달구현x) -->
		<a href="<%=request.getContextPath()%>/notice/adminNoticeList.jsp">공지관리</a>
	</div>
	
	
	<div>
		<h1><%=session.getAttribute("id")%>님의 장바구니</h1>
		
		<div>
			<table border = "1">
				<thead>
					<tr>
						<th>상품번호</th>
						<th>상품명</th>
						<th>상품가격(1개)</th>
						<th>상품갯수</th>
						<th>상품총가격</th>
						<th>장바구니마지막수정날짜</th>
						<th>장바구니에처음담은날짜</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(Map<String, Object> m : list){
					%>
						<tr>
							<td><%=m.get("goodsNo")%></td>
							<td><%=m.get("goodsName")%></td>
							<td><%=m.get("goodsPrice")%></td>
							<td><%=m.get("cartTotalnum")%></td>
							<td><%=m.get("cartTotalprice")%></td>
							<td><%=m.get("updateDate")%></td>
							<td><%=m.get("createDate")%></td>
						</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>