<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.CommentService"%>
<%@ page import="repository.*"%>
<%@ page import="vo.Comment"%>
<%@ page import="java.util.*"%>

<%
	System.out.println("!!!!insetCommentAction!!!!");

	//인코딩
	request.setCharacterEncoding("utf-8");
	
	int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
	String commentContent = request.getParameter("commentContent");
	String commentPw = request.getParameter("commentPw");
	String id = request.getParameter("id");
	
	System.out.println(noticeNo + " <-- noticeNo");
	System.out.println(commentContent + " <-- commentContent");
	System.out.println(commentPw + " <-- commentPw");
	System.out.println(id + " <-- id");
	
	Comment comment = new Comment();
	CommentService commentService = new CommentService();
	
	comment.setNoticeNo(noticeNo);
	comment.setCommentContent(commentContent);
	comment.setCommentPw(commentPw);
	comment.setId(id);
	
	commentService.insertComment(comment);
	
	System.out.println("로그인성공");
	
	response.sendRedirect(request.getContextPath()+"/notice/NoticeOne.jsp?noticeNo="+noticeNo);

	
	
%>