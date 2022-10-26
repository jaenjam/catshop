<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "repository.*"%>
<%@ page import = "vo.*"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>

<%
	Customer loginCustomer2 = (Customer)session.getAttribute("loginCustomer");
	CustomerDao customerDao  = new CustomerDao();
	Customer customer = customerDao.selectCusetomerOne(loginCustomer2.getCustomerId());
%>

	<div>
		<div class="container" style="text-align:center;">
			<div>
				<a href="<%=request.getContextPath()%>/customer/CustomerDetail.jsp">
					<button type="submit" class="btn btn-light" style="float:right">
						상세페이지로
					</button>
				</a>
			</div>
			
			<br><br>
			
			<form action="<%=request.getContextPath()%>/pw/UpdateCustomerPwAction.jsp" method="post">
				<table class="table table-hover" style="margin:auto">
					<tr>
						<td>ID</td>
						 <td><input type="text" name="id" value="<%=customer.getCustomerId()%>" readonly = "readonly"></td>
					</tr>
					<tr>
				         <td>PW</td>
				         <td><input type="password" name="pw"></td>
			      	</tr>
			     	<tr>
				         <td>NEWPW</td>
				         <td><input type="password" name="newPw"></td>
		      		</tr>
				</table>
				<button type="submit" class="btn btn-light" style="float:right">비밀번호 변경</button>
			</form>
		</div>
		<br><br><br>
	</div>


<%@include file="/template/footer.jsp"%> 