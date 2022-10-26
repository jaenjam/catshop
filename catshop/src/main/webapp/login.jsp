<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>



<div style="text-align:center">
	<!-- customer / employee -->
	<h2><%=session.getAttribute("user")%></h2>
	
	<table style="margin:auto">
		<tr>
			<th>id</th>
			<td>
				<input type="hidden" name="<%=session.getAttribute("user")%>Id" id="<%=session.getAttribute("user")%>Id" value="<%=session.getAttribute("id")%>">
   				<%=session.getAttribute("id")%>
			</td>
		</tr>
		<tr>
			<th>name</th>
			<td>
				<%=session.getAttribute("name")%>
			</td>
		</tr>
	</table>
	<br>
	<div>
		<a href="<%=request.getContextPath()%>/logout.jsp" style="color:"><button type="button" class="btn btn-light" id="logout">로그아웃</button></a>
  		<a href="<%=request.getContextPath()%>/<%=session.getAttribute("user")%>Detail.jsp"><button type="button" class="btn btn-light">상세정보보기</button></a>
	
	<%
		if(session.getAttribute("user").equals("Customer")){
	%>
		
  		<a href="<%=request.getContextPath()%>/orders/orderList.jsp"><button type="button" class="btn btn-light">주문내역</button></a>
	<%
		}
	%>
	</div>
	<br>
	
	   <!-- employee index -->
	   
	   <%
	  	 if(session.getAttribute("user").equals("Employee") && session.getAttribute("active").equals("Y")){//user값이 Employee일때 실행
	   %>
	   <div>
	   		<div class="btn-group">
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
  			</div>
	   </div>
	   <%
	   	}
	   %>
	   
	
</div>
<br>




<%@include file="/template/footer.jsp"%> 