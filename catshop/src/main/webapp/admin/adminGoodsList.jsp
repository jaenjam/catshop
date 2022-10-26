<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.Goods"%>
<%@page import="java.util.List"%>
<%@page import="service.GoodsService"%>

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

	int rowPerPage = 5; //한페이지당 보여줄 행 수 
	int currentPage = 1; //처음 시작할 현재 페이지
	
	
	if(request.getParameter("currentPage") != null){ 
		//currentPage가 null이 아니면
		//currentPage값을 넣어준다.
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int lastPage = 0;
	
	GoodsService goodsService = new GoodsService();
	lastPage = goodsService.getLastPage(rowPerPage);
	
	List<Goods> list = new ArrayList<Goods>();
	list = goodsService.getGoodsListByPage(rowPerPage, currentPage); //list보여주기
	
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
		<div>
			<form action="<%=request.getContextPath()%>/admin/addGoods.jsp">
			<table class="table table-hover" style="margin:auto;">
				<thead>
					<tr>
						<th>NO</th>
						<th>MENU</th>
						<th>NAME</th>
						<th>PRICE</th>
						<th>UPDATEDATE</th>
						<th>CREATEDATE</th>
						<th>SOLDOUT</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(Goods g : list){
					%>
					<tr>
						<td><%=g.getGoodsNo()%></td>
						<td><%=g.getGoodsMenu()%></td>
						<td>
						<a href="<%=request.getContextPath()%>/admin/adminGoodsOne.jsp?goods_no=<%=g.getGoodsNo()%>"><%=g.getGoodsName()%>
						</a>
						</td>
						<td><%=g.getGoodsPrice()%></td>
						<td><%=g.getUpdateDate()%></td>
						<td><%=g.getCreateDate()%></td>
						<td><%=g.getSoldOut()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<!-- 페이지 이전 다음 -->
			<br><br>
	
			<%
				if(currentPage > 1){ //현재페이지가 1보다 클 경우(1페이지가 아닐경우)
			%>
				<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp?currentPage=<%=currentPage-1%>">
					<button class="btn btn-light" type="button" style="background-color: #7fad39; color:white;">
						이전
					</button>
				</a>
			<%
				}
				
				if(currentPage < lastPage){ //현재페이지가 마지막페이지보다 전일 경우
			%>
				<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp?currentPage=<%=currentPage+1%>">
					<button class="btn btn-light" type="button" style="background-color: #7fad39; color:white;">
						다음
					</button>
				</a>
			<%
				}
			%>
			<br>
			<br>
			
				<button class="btn btn-light" type="submit" style="float:right">상품추가</button>
			</form>
		</div>
	</div>
</div>
<br><br>
