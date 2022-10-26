<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.Customer"%>
<%@page import="java.util.List"%>
<%@page import="service.CustomerService"%>
<%@page import="repository.CustomerDao"%>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<%
	// 관리자로 로그인하고 'Y'인 관리자만 들어올 수 있게..
	System.out.println("!!!!adminCustomerList!!!!");
	if(session.getAttribute("login"+session.getAttribute("user")) == null){
		response.sendRedirect(request.getContextPath()+"/loginForm.jsp");
		return;
	}

	if (!(session.getAttribute("user").equals("Employee"))) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}


	int rowPerPage = 5; //한페이지당 보여줄 행 수 
	int currentPage = 1; //처음 시작할 현재 페이지
	int lastPage = 0;
	
	if(request.getParameter("currentPage") != null){ 
		//currentPage가 null이 아니면
		//currentPage값을 넣어준다.
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	
	
	CustomerService customerService = new CustomerService();
	List<Customer>list = new ArrayList<Customer>();
	list = customerService.getCustomerList(rowPerPage, currentPage); //list보여주기
	
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
			<table class="table table-hover" style="margin:auto;">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>ADDRESS</th>
						<th>TELEPHONE</th>
						<th>UPDATE DATE</th>
						<th>CREATE DATE</th>
						<th>계정강제삭제</th>
						<th>비밀번호초기화</th>
					</tr>
				</thead>
				<tbody>
					<%
						for(Customer c : list){
					%>
					<tr>
						<td><%=c.getCustomerId()%></td>
						<td><%=c.getCustomerName()%></td>
						<td><%=c.getCustomerAddress()%></td>
						<td><%=c.getCustomerTelephone()%></td>
						<td><%=c.getUpdateDate()%></td>
						<td><%=c.getCreateDate()%></td>
						<td>
							<!-- 
							<a href="<%=request.getContextPath()%>/admin/adminCustomerDeleteAction.jsp?customerId=<%=c.getCustomerId()%>">
							 -->	
								<button class="btn btn-light" type="button" onclick="deleteClick()" value="prompt">
									계정강제삭제
								</button>
							<!-- 
							</a>
							 -->
						</td>
						<td>
							<a href="<%=request.getContextPath()%>/admin/adminCustomerPwUpdateAction.jsp?customerId=<%=c.getCustomerId()%>">
								<button class="btn btn-light" type="button">
									비밀번호초기화
								</button>
							</a>
						</td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			
			<!-- 페이징 -->
			<%
				lastPage = customerService.getlastPage(rowPerPage);
				System.out.println("adminCustomerList.jsp lastPage : "+lastPage);
				System.out.println("adminCustomerList.jsp currentPage : "+currentPage);
				System.out.println("!!!!adminCustomerList!!!!");
				
				if(currentPage > 1){ //현재페이지가 1보다 클때
					System.out.println("이전페이지로");
			%>
			
				<a href = "<%=request.getContextPath()%>/customer/adminCustomerList.jsp?currentPage=<%=currentPage-1%>">
					<button class="btn btn-light" type="button">
						이전
					</button>
				</a>
			
			<%
				}
				
				if(currentPage < lastPage){
					System.out.println("다음페이지로");
			%>
			
				<a href = "<%=request.getContextPath()%>/customer/adminCustomerList.jsp?currentPage=<%=currentPage+1%>">
					<button class="btn btn-light" type="button">
						다음
					</button>
				</a>
			
			<%
				}
			%>
		</div>
	</div>
</div>
<br><br>


<script>
	function deleteClick(){
		let greeting = prompt("강제삭제 막아둠.");
		document.getElementByPw('CustomerPw').innerText = password;
	}
</script>