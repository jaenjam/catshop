<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<%
	System.out.println("!!!!addGoods!!!!");
	if (!(session.getAttribute("user").equals("Employee")&&session.getAttribute("login"+session.getAttribute("user")) != null)) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
%>

<div class="container">
	<div style="text-align:center;">
	
	<br><br>
	
	<div>
		<a href="<%=request.getContextPath()%>/admin/adminGoodsList.jsp"><h1>GoodsList</h1></a>
	</div>
	
	<br><br>
	
	<div>
	<h1> 판매상품 등록 </h1>
	<form action="<%=request.getContextPath()%>/admin/addGoodsAction.jsp" method="post" enctype="multipart/form-data">
		<table class="table table-hover" style="margin:auto; text-align:center">
			<tr>
				<th>상품분류</th>
				<td>
					<select class="form-control" name="goodsMenu">
						<option>캣타워</option>
						<option>생활/리빙용품</option>
						<option>목욕/미용용품</option>
						<option>배변용품</option>
						<option>건식사료</option>
						<option>습식사료</option>
						<option>고양이간식&영양제</option>
						<option>장난감</option>
						<option>스크레쳐</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<input type="text" class="form-control" name="goodsName" id="goodsName">
				</td>
			</tr>	
			<tr>
				<th>상품가격</th>
				<td>
					<input type="text" class="form-control" name="goodsPrice" id="goodsPrice">
				</td>
			</tr>
			<tr>
				<th>상품사진파일</th>
				<td>
					<input class="form-control-file border" type="file" name="goodsImg">
				</td>
			</tr>

		</table>
		<div style="float:right">
	        <button type="submit" class="btn btn-success">상품판매등록</button>
			<button type="reset" class="btn btn-danger">초기화</button>
		</div>
	</form>
	</div>
</div>
</div>
<br>
<br>	
