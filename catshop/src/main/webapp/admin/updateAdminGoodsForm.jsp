<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="service.GoodsService"%>
<%@page import="repository.GoodsDao"%>
<%@page import="vo.Goods"%>
<%@page import="java.util.Map"%>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<%
	if(session.getAttribute("login"+session.getAttribute("user")) == null){
			response.sendRedirect(request.getContextPath() + "/loginForm.jsp?errorMsg = login first");
			return;
	}

	int goodsNo = Integer.parseInt(request.getParameter("goodsNo"));
	System.out.println("updateAdminGoodsForm.jsp goodsNo : " + goodsNo);
	
	GoodsService goodsService = new GoodsService();// 생성
	
	Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);
	
	System.out.println("updateAdminGoodsForm.jsp map : " + map.toString());
	
%>
<div class="container">
<br><br>

<div>
	<a href="./adminGoodsList.jsp">
		<button class="btn btn-light" type="button">
			상품관리로
		</button>
	</a>
</div>
	<div style=" text-align:center;">
		
	<br><br>

<div style="padding: 50px 100px 50px;">
	<form action="<%=request.getContextPath()%>/admin/updateAdminGoodsAndImgAction.jsp"
				method="post" enctype="multipart/form-data">
   <table class="table table-hover" style="margin:auto; text-align:center">
      <tr>
         <td>상품이미지</td>
         <td><img src="<%=request.getContextPath()%>/upload/<%=map.get("fileName")%>" style="width:500px; height:500px;"></td>
      </tr>
      <tr>
         <td>번호</td>
         <td>
         	<input type="text" class="form-control" name="goodsNo" 
        	 value="<%=map.get("goodsNo")%>"readonly="readonly">
         </td>
      </tr>
      <tr>
         <td>상품이름</td>
         <td>
         	<input type="text" class="form-control" name="goodsName" value="<%=map.get("goodsName")%>">
         </td>
      </tr>
      <tr>
         <td>상품가격</td>
         <td>
         	<input type="text" class="form-control" name="goodsPrice" value="<%=map.get("goodsPrice")%>">
         </td>
      </tr>
      <tr>
         <td>상품매진여부</td>
         <td>
         	<select class="form-control" name = "soldOut">
         		<%
         			if(map.get("soldOut").equals("N")){
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
         </td>
      </tr>
      <tr>
         <td>이미지사진파일</td>
         <td>
         <input type="hidden" name="f" value="<%=map.get("fileName")%>">
         <input class="form-control-file border" type="file" name="file" <%=map.get("originalFilename")%>>
         </td>
      </tr>      
   </table>
   <div style="float:right">
	   <button type="submit" class="btn btn-success">입력</button>
	   <button type="reset" class="btn btn-danger">초기화</button>
   </div>
</form>
</div>
</div>
</div>