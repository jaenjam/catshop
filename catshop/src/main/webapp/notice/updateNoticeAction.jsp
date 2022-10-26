<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.Notice"%>
<%@ page import="service.NoticeService"%>

<%
	int row=0;
	int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
	String show = request.getParameter("show");
	
	System.out.println(noticeNo + " <-- noticeNo");
	System.out.println(show + " <-- show");
	
	Notice notice = new Notice();
	notice.setNoticeNo(noticeNo);
	notice.setNoticeShow(show);
	
	NoticeService noticeService = new NoticeService();
	row = noticeService.modifyNoticeAction(notice);
	
	if(row == 1){		// 수정 성공
		System.out.println("관리자 권한 수정 성공!");
		response.sendRedirect(request.getContextPath() + "/notice/adminNoticeOne.jsp?noticeNo="+noticeNo);
	} else {			// 수정 실패
		System.out.println("관리자 권한 수정 실패!");
		response.sendRedirect(request.getContextPath() + "/notice/adminNoticeOne.jsp?noticeNo="+noticeNo);
	}
%>