<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<%
	System.out.println("!!!!addNotices!!!!");
	if (!(session.getAttribute("user").equals("Employee")&&session.getAttribute("login"+session.getAttribute("user")) != null)) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
%>
<div class="container">
	<div style=" text-align:center;">
		
	<br><br>
		
	<div>
		<a href="<%=request.getContextPath()%>/notice/adminNoticeList.jsp"><h1>NoticeList</h1></a>
	</div>
	
	<br><br>
	
	<div>
	<h1> 판매상품 등록 </h1>
	<form action="<%=request.getContextPath()%>/notice/addNoticesAction.jsp" method="post">
		<table class="table table-hover" style="margin:auto; text-align:center">
			<tr>
				<td>공지제목</td>
				<td>
					<input type="text" class="form-control"  name="noticeTitle" id="noticeTitle">
				</td>
			</tr>	
			<tr>
				<td>공지내용</td>
				<td>
					<textarea class="form-control" rows="5" name="noticeContent" id="noticeContent"></textarea>
					<input type="hidden" id="noticeId" name="noticeId" value="<%=session.getAttribute("id")%>">
				</td>
			</tr>

		</table>
		<div style="float:right">
	        <button type="submit" class="btn btn-success">공지등록</button>
			<button type="reset" class="btn btn-danger">글전체초기화</button>
		</div>
	</form>
	</div>
</div>
</div>
<br>
<br>	