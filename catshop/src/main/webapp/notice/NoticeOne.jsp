<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/template/header.jsp"%>

<%@include file="/template/all_departments.jsp" %>


<%@ page import="service.*"%>
<%@ page import="repository.*"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.*"%>

<%
	System.out.println("!!!!adminNoticeOne!!!!");

	int noticeNo  =Integer.parseInt(request.getParameter("noticeNo"));
	System.out.println(noticeNo + " <-- noticeNo");
	
	NoticeService noticeService = new NoticeService();
	Notice notice = null;
	notice = noticeService.getNoticeOne(noticeNo);
	
//////////////////////////////////////////////////////////////////////////////////
/*
	int rowPerPage = 5;
	int currentPage = 1;
	int lastPage = 0;
	
	if(request.getParameter("currentPage") != null){ 
		//currentPage가 null이 아니면
		//currentPage값을 넣어준다.
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
*/	
	CommentService commentService = new CommentService();	
	List<Comment>list = new ArrayList<Comment>();
	list = commentService.getCommentList(noticeNo);
%>


<div class="container">
	<div>
				<table class="table table-hover" style="margin:auto;">
					<tr>
						<th style="width:25%">공지제목</th>
						<td><%=notice.getNoticeTitle()%></td>
					</tr>
					<tr>
						<th style="width:25%">작성자</th>
						<td><%=notice.getNoticeId()%></td>
					</tr>
					<tr>
						<th style="width:25%">공지내용</th>
						<td><%=notice.getNoticeContent()%></td>
					</tr>										
				</table>
		</div>
			<hr/>
			
		<div>
			<!-- comment -->
			<form action="">
				<table class="table table-bordered">
					<tr>
						<th>ID</th>
						<th>CONTENT</th>
						<th>PASSWORD</th>
						<th>CREATEDATE</th>
						<th>UPDATEDATE</th>
					</tr>
				<%
					for(Comment c : list){
				%>
					<tr>
						<td><%=c.getId()%></td>
						<td><%=c.getCommentContent()%></td>
						<td><input type="password" name="commentPw"></td>
						<td><%=c.getCreateDate()%></td>
						<td><%=c.getUpdateDate()%></td>
						<td><a href="<%=request.getContextPath()%>/comment/deleteCommentAction.jsp?commentNo=<%=c.getCommentNo()%>">삭제</a></td>
					</tr>
				<%
					}
				%>
				</table>
			</form>
		</div>
		<hr/>
			
		
		<div>
			<form method="post" action="<%=request.getContextPath()%>/comment/insertCommentAction.jsp?noticeNo=<%=notice.getNoticeNo()%>&id=<%=session.getAttribute("id")%>">
				<table class="table table-hover" style="margin:auto;">
					<tr>
						<th>댓글</th><td><textarea class="ridge" style="resize: none;" rows="2" cols="100" name="commentContent"></textarea></td>
					</tr>
					<tr>
						<th>비밀번호설정</th><td><input type="password" name="commentPw"></td>
					</tr>
				</table>
				<button type="submit">댓글등록</button>
				<button type="reset">댓글초기화</button>			
			</form>
		</div>
		</div>
		<br><br>
	
<%@include file="/template/footer.jsp"%> 