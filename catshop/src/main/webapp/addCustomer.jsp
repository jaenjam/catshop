<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp"%>

<div style="text-align:center">
   <!-- id ck form -->
   <form action="<%=request.getContextPath()%>/idCheckAction.jsp" method="post">
      <div>
         ID 중복 체크
         <input type="text" name="ckid">
         <input type="hidden" name="c" id="c" value="Customer">
         <!-- hidden을 사용해서 화면에 보이지 않지만 값이 넘어갈 수 있게 Customer이라는 문자를 c라는 id값에 넣어줌 -->
         <button type="submit" class="btn btn-light" style="background-color: #7fad39; color:white;" type="submit">아이디중복검사</button>
      </div>
   </form>
   
   <!-- 고객가입 form -->
   <%
      String ckId = "";
      if(request.getParameter("ckId") != null) {ckId = request.getParameter("ckId");}
   %>
<div style="margin-top:20px">
   <form action="<%=request.getContextPath()%>/insertCustomerAction.jsp" method="post">
      <table style="margin:auto">
         <tr>
            <td>아이디</td>
            <td>
               <input type="text" name="customerId" id="customerId" 
                  readonly="readonly" value="<%=ckId%>"> <!-- ckId값이 중복되지 않으면 id값에 바로 넣어표시해주기 -->
                  
            </td>
         </tr>
         <tr>
         	<td>비밀번호</td>
         	<td><input type="password" name="customerPass" id="customerPass"></td>
         </tr>
         <tr>
         	<td>이름</td>
         	<td><input type="text" name="customerName" id="customerName"></td>
         </tr>
         <tr>
         	<td>주소</td>
         	<td><input type="text" name="customerAddress" id="customerAddress"></td>
         </tr>
         <tr>
         	<td>전화번호</td>
         	<td><input type="text" name="customerTelephone" id="customerTelephone"></td>
         </tr>
      </table>
      <br>
        <button type="submit" class="btn btn-light" style="background-color: #7fad39; color:white;">회원가입완료</button>
   </form>
</div>
</div>

<br>




<%@include file="/template/footer.jsp"%> 