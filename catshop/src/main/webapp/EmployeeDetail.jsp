<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "repository.*"%>
<%@ page import = "vo.*"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>

<%
	Employee loginEmployee2 = (Employee)session.getAttribute("loginEmployee");
	EmployeeDao employeeDao  = new EmployeeDao();
	Employee employee = employeeDao.selectEmployeeOne(loginEmployee2.getEmployeeId());
%>

<div class="container">
	<div style="text-align:center;">
		<table class="table table-hover" style="margin:auto">
			<tr>
				<td>ID</td>
				<td>
					<input type="hidden" value="Employee" name="user" id="user">
					<%=employee.getEmployeeId()%>
				</td>
			</tr>
			<tr>
				<td>NAME</td>
				<td><%=employee.getEmployeeName()%></td>
			</tr>

			<tr>
				<td>CREATEDATE</td>
				<td><%=employee.getCreateDate()%></td>
			</tr>
		</table>
		
		<br>
		
		<a href="./">
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
		<a href="<%=request.getContextPath()%>/Employeedelete.jsp?EmployeeId=<%=employee.getEmployeeId()%>">
		-->	
			<button type="button" class="btn btn-light"  onclick="deleteClick()" value="prompt">
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
		document.getElementByPw('EmployeePw').innerText = password;
	}
</script>

<%@include file="/template/footer.jsp"%> 