<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.NoticeService"%>
<%@ page import="repository.*"%>
<%@ page import="vo.Notice"%>
<%@ page import="java.util.*"%>
<%
	System.out.println("!!!!updateAdminNoticeForm!!!!");

	if (!(session.getAttribute("user").equals("Employee")&&session.getAttribute("login"+session.getAttribute("user")) != null)) {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	

	
	int noticeNo  =Integer.parseInt(request.getParameter("noticeNo"));
	System.out.println(noticeNo + " <-- noticeNo");
	
	NoticeService noticeService = new NoticeService();
	Notice notice = null;
	notice = noticeService.getNoticeOne(noticeNo);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div><a href="<%=request.getContextPath()%>/notice/adminNoticeList.jsp">NOTICE LIST</a></div>
<div><a href="<%=request.getContextPath()%>/notice/adminNoticeOne.jsp?noticeNo=<%=notice.getNoticeNo()%>">이전페이지로</a></div>
	<div>
		<form method="post" action="<%=request.getContextPath()%>/notice/updateAdminNoticeAction.jsp?noticeNo=<%=notice.getNoticeNo()%>">
			<table border = "1">
				<tr>
					<th>공지번호</th>
					<td><input type="text" id="noticeNo" name="noticeNo" value="<%=notice.getNoticeNo()%>" readonly="readonly"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" id="noticeId" name="noticeId" value="<%=notice.getNoticeId()%>" readonly="readonly"></td>
				</tr>
				<tr>
					<th>공지제목</th>
					<td><input type="text" id="noticeTitle" name="noticeTitle" value="<%=notice.getNoticeTitle()%>"></td>
				</tr>
				<tr>
					<th>공지내용</th>
					<td><textarea cols="100" rows="2" id="noticeContent" name="noticeContent"><%=notice.getNoticeContent()%></textarea></td>
				</tr>											
			</table>
			<button type="submit">수정완료</button>
			<button type="reset">수정초기화</button>
		</form>
	</div>
</body>
</html>