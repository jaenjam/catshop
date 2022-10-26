<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>

<style>
.nav-pills .nav-link{
    color: #7fad39;
}

.nav{
text-align: center;
}

.nav-pills .nav-link.active, .nav-pills .show>.nav-link{
    color: white;
	background-color: #7fad39;
	
}

</style>

<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container">
<div class="loginform_login">
<br>
  <!-- Nav pills -->
  
  <ul class="nav nav-pills" role="tablist">
    <li class="nav-item">
      <a class="nav-link active" data-bs-toggle="pill" href="#customer">Customer</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" data-bs-toggle="pill" href="#employee">Employee</a>
    </li>

  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div id="customer" class="container tab-pane active"><br>
    	<div class="row">
    		<div class="col-sm-6">
			  <form id="customerForm" method="post" action="<%=request.getContextPath()%>/customer/customerLoginAction.jsp" class="was-validated">
			    <div class="mb-3 mt-3">
			      <label for="customer_id" class="form-label">ID:</label>
				      <input type="text" class="form-control" id="customerId" placeholder="아이디" name="customerId" value="guest" required>
				      <div class="valid-feedback">입력되었습니다.</div>
				      <div class="invalid-feedback">아이디를 입력해주세요.</div>
			    </div>
			    <div class="mb-3">
			      <label for="customer_pw" class="form-label">Password:</label>
				      <input type="password" class="form-control" id="customerPass" placeholder="비밀번호" name="customerPass" value="1234" required>
				      <div class="valid-feedback">입력되었습니다.</div>
				      <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
			    </div>
			      <button type="submit" class="site-btn" id="customerBtn">login</button>
		      </form>
	      </div>
      	</div>
     </div>






    <div id="employee" class="container tab-pane fade"><br>
    	<div class="row">
    		<div class="col-sm-6">
			  <form id="employeeForm" method="post" action="<%=request.getContextPath()%>/employee/employeeLoginAction.jsp" class="was-validated">
			    <div class="mb-3 mt-3">
			      <label for="employee_id" class="form-label">ID:</label>
				      <input type="text" class="form-control" id="employeeId" placeholder="아이디" name="employeeId" value="admin" required>
				      <div class="valid-feedback">입력되었습니다.</div>
				      <div class="invalid-feedback">아이디를 입력해주세요.</div>
			    </div>
			    <div class="mb-3">
			      <label for="employee_pw" class="form-label">Password:</label>
				      <input type="password" class="form-control" id="employeePass" placeholder="비밀번호" name="employeePass" value="1234" required>
				      <div class="valid-feedback">입력되었습니다.</div>
				      <div class="invalid-feedback">비밀번호를 입력해주세요.</div>
			    </div>
			      <button type="submit" class="site-btn" id="employeeBtn">login</button>
		      </form>
	      </div>
      	</div>
      </div>
      
      
      

  </div>


</div>
</div> 


<script>
   $('#customerBtn').click(function(){
      if($('#customerId').val() == '') {
         alert('고객 아이디를 입력하세요');
      } else if($('#customerPass').val() == '') {
         alert('고객 패스워드를 입력하세요');
      } else {
         customerForm.submit();
      }
   });
   
   
   $('#employeeBtn').click(function(){
      if($('#employeeId').val() == '') {
         alert('스텝 아이디를 입력하세요');
      } else if($('#employeePass').val() == '') {
         alert('스텝 패스워드를 입력하세요');
      } else {
         employeeForm.submit();
      }
   });
</script>

<br><br>
 
<%@include file="/template/footer.jsp"%> 