<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="repository.*"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.*"%>

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

	int goodsNo = Integer.parseInt(request.getParameter("goods_no"));
	System.out.println("adminGoodsOne.jsp goodsNo : " + goodsNo);
	
	GoodsService goodsService = new GoodsService();// 생성
	
	Map<String, Object> map = goodsService.getGoodsAndImgOne(goodsNo);
	
	System.out.println("adminGoodsOne.jsp map : " + map.toString());
	
/////////////////////////////////////////////////////////////////////////////////////
	
	ReviewService reviewService = new ReviewService();
	List<Review>list = new ArrayList<Review>();
	list = reviewService.getReviewList(goodsNo);
	
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

   <table class="table table-hover" style="margin:auto; text-align:center">
      <tr>
         <td>상품이미지</td>
         <td><img src="<%=request.getContextPath()%>/upload/<%=map.get("fileName")%>" style="width:500px; height:500px;"></td>
      </tr>
      <tr>
         <td>번호</td>
         <td><%=map.get("goodsNo")%></td>
      </tr>
      <tr>
      	<td>상품분류</td>
      	<td><%=map.get("goodsMenu")%></td>
      </tr>
      <tr>
         <td>상품이름</td>
         <td><%=map.get("goodsName")%></td>
      </tr>
      <tr>
         <td>상품가격</td>
         <td><%=map.get("goodsPrice")%></td>
      </tr>
      <tr>
         <td>마지막수정날짜</td>
         <td><%=map.get("updateDate")%></td>
      </tr>
      <tr>
         <td>작성날짜</td>
         <td><%=map.get("createDate")%></td>
      </tr>
      <tr>
         <td>상품매진여부</td>
         <td><%=map.get("soldOut")%></td>
      </tr>
      <tr>
         <td>이미지제목</td>
         <td><%=map.get("fileName")%></td>
      </tr>
      <tr>
         <td>이미지원본제목</td>
         <td><%=map.get("originalFilename")%></td>
      </tr>      
      <tr>
         <td>이미지타입</td>
         <td><%=map.get("contentType")%></td>
      </tr>
      <tr>
         <td>
            <a href="./updateAdminGoodsForm.jsp?goodsNo=<%=map.get("goodsNo")%>">
            	<button class="btn btn-light" type="button" style="background-color: #7fad39; color:white;">
            		수정
            	</button>
            </a>
         </td>
         <td>
            <a href="./deleteAdminGoodsAction.jsp?goodsNo=<%=map.get("goodsNo")%>&fileName=<%=map.get("fileName")%>">
            	<button class="btn btn-light" type="button" style="background-color: #7fad39; color:white;">
            		삭제
            	</button>
            </a>
         </td>
      </tr>
   </table>
 
   <br><br>
 
   <hr/>
   
   <br><br>   	
   
   		<!-- review -->
   		<div class="table-responsive">
	   		<table class="table table-hover" style="margin:auto; text-align:center">
	   			<tr>
	   				<th>번호</th>
	   				<th>작성자</th>
	   				<th>내용</th>
	   				<th>생성날짜</th>
	   				<th>마지막수정날짜</th>
	   				<th>비밀번호입력</th>
	   				<th>수정</th>
	   				<th>삭제</th>
	   			</tr>
	   			<%
	   				for(Review r: list){
	   			%>
	   				<tr>
	   					<td><%=r.getReviewNo()%></td>
	   					<td><%=r.getId()%></td>
	   					<td><%=r.getReviewContent()%></td>
	   					<td><%=r.getCreateDate()%></td>
	   					<td><%=r.getUpdateDate()%></td>
	   					<td><input type="password" class="form-control" name="reviewPw"></td>
	   					<td>
	   						<a href="<%=request.getContextPath()%>/review/updateReviewAction.jsp?reviewNo=<%=r.getReviewNo()%>">
	   							<button class="btn btn-light" type="button" style="background-color: #7fad39; color:white;">
	   								수정
	   							</button>
	   						</a>
	   					</td>
						<td>
							<a href="<%=request.getContextPath()%>/review/deleteReviewAction.jsp?reviewNo=<%=r.getReviewNo()%>">
								<button class="btn btn-light" type="button" style="background-color: #7fad39; color:white;">
									삭제
								</button>
							</a>
						</td>
	   				</tr>
	   			<%
	   				}
	   			%>
	   		</table>
   		</div>
   <br><br>
   		
   		<hr/>
   
   <br><br>
   
   <!-- insertReview -->
   <div>
		<form method="post" action="<%=request.getContextPath()%>/review/insertReviewAction.jsp?goodsNo=<%=map.get("goodsNo")%>&id=<%=session.getAttribute("name")%>">
			<table class="table table-hover" style="margin:auto; text-align:center">
				<tr>
					<th>댓글</th>
					<td>
						<textarea class="form-control" rows="2" cols="100" name="reviewContent" id="reviewContent"></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호설정</th>
					<td><input type="password" class="form-control" name="reviewPw"></td>
				</tr>
			</table>
			<div style="float:right">
				<button type="submit" class="btn btn-success">댓글등록</button>
				<button type="reset" class="btn btn-danger">댓글초기화</button>	
			</div>			
		</form>   
   </div>
  </div> 
</div>
</div>
<br>
<br>
