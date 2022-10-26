<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.Employee"%>
<%@page import="repository.EmployeeDao"%>
<%@page import="service.EmployeeService"%>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<%
	// 관리자로 로그인하고 'Y'인 관리자만 들어올 수 있게..
	System.out.println("!!!!EmployeeList!!!!");
	if (!(session.getAttribute("user").equals("Employee")&&session.getAttribute("login"+session.getAttribute("user")) != null)) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	int rowPerPage = 1; // 페이지에 보여줄 행의 갯수
	int currentPage = 1; // 현재 페이지
	int lastPage = 0;
	
	if(request.getParameter("currentPage") != null){ 
		//currentPage가 null이 아니면
		//currentPage값을 넣어준다.
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	
	EmployeeService employeeService = new EmployeeService();
	ArrayList<Employee> list = new ArrayList<Employee>();
	list = employeeService.getEmployeeList(rowPerPage, currentPage);	// list
%>
<div class="container">
	<div style=" text-align:center;">
		<div style="margin-bottom:40px">
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
	
			<table class="table table-hover" style="margin:auto;">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>UPDATE DATE</th>
						<th>CREATE DATE</th>
						<th>ACTIVE</th>
						<th>권한변경</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Employee e : list) {
					%>
					<tr>
						<td><%=e.getEmployeeId()%></td>
						<td><%=e.getEmployeeName()%></td>
						<td><%=e.getUpdateDate()%></td>
						<td><%=e.getCreateDate()%></td>
						<td><%=e.getActive()%></td>
						<td>
							<form action="<%=request.getContextPath()%>/employee/updateEmployeeAction.jsp" method="post">
								<input type="hidden" name="employeeId" value="<%=e.getEmployeeId()%>">
								<select name="active">
									<%
									if (e.getActive().equals("N")) { //active값이 N이면
									%>
									<option>Y</option>
									<option selected="selected">N</option>	<!-- 디폴트값은 N로 설정되어있게 -->
									<%
									} else { //active값이 Y이면
									%>
									<option selected="selected">Y</option> <!-- 디폴트값은 Y로 설정되어있게 -->
									<option>N</option>
									<%
									}
									%>
								</select>
							<button class="btn btn-light" type="submit" onclick="updateActive()" value="alert">권한변경</button>
						</form>
						</td>
						</tr>
						<%
						}
						%>
					
				</tbody>
			</table>
	
			<!-- 페이징 -->
			<%
			lastPage = employeeService.getlastPage(rowPerPage);					// 페이징
			System.out.println("employeeList.jsp lastPage : "+lastPage);
			System.out.println("employeeList.jsp currentPage : "+currentPage);
			System.out.println("!!!!2.EmployeeList!!!!");
			
			if (currentPage > 1) { //현재페이지가 1보다 클때
				System.out.println("이전페이지로");
			%>
			<a
				href="<%=request.getContextPath()%>/EmployeeList.jsp?currentPage=<%=currentPage-1%>">이전</a>
			<%
			}
	
			if (currentPage < lastPage) { //현재페이지가 마지막 페이지보다 작을때
				System.out.println("다음페이지로");
			%>
			<a
				href="<%=request.getContextPath()%>/EmployeeList.jsp?currentPage=<%=currentPage+1%>">다음</a>
	
			<%
			}
			%>
	
		</div>
	
	</div>
</div>

<script>
function updateActive()  {
  alert('alert!');
}
</script>