<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.Notice"%>
<%@ page import="service.NoticeService"%>
<%
	request.setCharacterEncoding("utf-8");

	int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
	String noticeId = request.getParameter("noticeId");
	String noticeTitle = request.getParameter("noticeTitle");
	String noticeContent = request.getParameter("noticeContent");
	
	System.out.println(noticeNo + " <-- noticeNo");
	System.out.println(noticeId + " <-- noticeId");
	System.out.println(noticeTitle + " <-- noticeTitle");
	System.out.println(noticeContent + " <-- noticeContent");
	
	Notice notice = new Notice();
	NoticeService noticeService = new NoticeService();
	
	notice.setNoticeNo(noticeNo);
	notice.setNoticeId(noticeId);
	notice.setNoticeTitle(noticeTitle);
	notice.setNoticeContent(noticeContent);
	
	
	noticeService.adminUptdateNotice(notice);
	
	System.out.println("로그인성공");
	
	response.sendRedirect(request.getContextPath()+"/notice/adminNoticeList.jsp");
%>