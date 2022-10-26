<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*"%>
<%@ page import="repository.*"%>
<%@ page import="java.util.*"%>
<%@ page import="vo.*"%>

<%
	System.out.println("!!!!adminNoticeOne!!!!");
	if (!(session.getAttribute("user").equals("Employee")&&session.getAttribute("login"+session.getAttribute("user")) != null)) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><a href="<%=request.getContextPath()%>/notice/adminNoticeList.jsp">홈으로</a></div>
	<div>
		<div>
				<table border = "1">
					<tr>
						<th>공지번호</th>
						<td><%=notice.getNoticeNo()%></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><%=notice.getNoticeId()%></td>
					</tr>
					<tr>
						<th>공지제목</th>
						<td><%=notice.getNoticeTitle()%></td>
					</tr>
					<tr>
						<th>공지내용</th>
						<td><%=notice.getNoticeContent()%></td>
					</tr>
					<tr>
						<th>공지보여주기/숨김</th>
						<td>
						<form action="<%=request.getContextPath()%>/notice/updateNoticeAction.jsp" method="post">
							<input type="hidden" name="noticeNo" value="<%=notice.getNoticeNo()%>">
							<select name="show">
								<%
								if (notice.getNoticeShow().equals("N")) { //active값이 N이면
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
						<button type="submit">권한변경</button>
					</form>
					</td>
					</tr>	
					<tr>
						<th>공지마지막수정날짜</th>
						<td><%=notice.getUpdateDate()%></td>
					</tr>
					<tr>
						<th>공지등록날짜</th>
						<td><%=notice.getCreateDate()%></td>
					</tr>				
					<tr>
						<th>수정</th>
						<th><a href="<%=request.getContextPath()%>/notice/updateAdminNoticeForm.jsp?noticeNo=<%=notice.getNoticeNo()%>">수정</a></th>
					</tr>										
				</table>
		</div>
			<hr/>
		<hr/>
	</div>
</body>
</html>