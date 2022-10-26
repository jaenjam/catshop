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


<div class="container">
	<div style="text-align:center;">
		<table class="table table-hover" style="margin:auto">
			<tr>
				<td>ID</td>
				<td>
					<input type="hidden" value="Customer" name="CustomerId" id="CustomerId">
					<%=customer.getCustomerId()%>
				</td>
			</tr>
			<tr>
				<td>NAME</td>
				<td><%=customer.getCustomerName()%></td>
			</tr>
			<tr>
				<td>ADDRESS</td>
				<td><%=customer.getCustomerAddress()%></td>
			</tr>
			<tr>
				<td>TELEPHONE</td>
				<td><%=customer.getCustomerTelephone()%></td>
			</tr>
			<tr>
				<td>CREATEDATE</td>
				<td><%=customer.getCreateDate()%></td>
			</tr>
		</table>
		
		<br>
		
		<a href="<%=request.getContextPath()%>/pw/UpdateCustomerPwForm.jsp?CustomerId=<%=customer.getCustomerId()%>">
			<button type="button" class="btn btn-light">
				비밀번호 변경
			</button>
		</a>
		<a href="./">
			<button type="button" class="btn btn-light">
				계정정보 수정
			</button>
		</a>
		<!--
		<a href="<%=request.getContextPath()%>/customer/deleteCustomerAction.jsp?CustomerId=<%=customer.getCustomerId()%>">
		-->
			<button type="button" class="btn btn-light" onclick="deleteClick()" value="prompt">
				계정정보 삭제
			</button>
		<!--
		</a>
		-->
	</div>
</div>	
<br>
<br>
	



<script>
	function deleteClick(){
		let greeting = prompt("비밀번호를 입력하세요.");
		document.getElementByPw('CustomerPw').innerText = password;
	}
</script>



<%@include file="/template/footer.jsp"%> 