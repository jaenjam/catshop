<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.SignService"%>
<%@ page import = "repository.*"%>
<%@ page import = "java.sql.*"%>
<%@ page import = "vo.*"%>
<%
	SignDao signDao = new SignDao();

	request.setCharacterEncoding("utf-8");
	
	//ckid(아이디 중복검사를 위해 받은 아이디값)을 ckId에 불러와준다.
	String ckId = request.getParameter("ckid");
	
	//Customer에서 회원가입할 땐 c값에 Customer가 N으로 들어가게되고
	//Employee에서 회원가입할 땐 c값에 Employee가 N으로 들어가게된다!
	//idCheckAction.jsp를 두개만들고 싶지않아서 c값을 받아와 String N을 사용
	String N = request.getParameter("c");
	
	SignService service = new SignService();
	if(service.idCheck(ckId) == false){ //고객, 관리자 혹은 탈되한. 기존에 있던 아이디 값이라면
	//입력한 아이디의 값이 Customer, OutId, Employee에 있는 값이면 false
	//service -> false
	System.out.println("사용불가능");
		if(N.equals("Customer")){ // 그 중에서도 N에 들어있는 값이 Customer이면
			//addCustomer.jsp 실행
			response.sendRedirect(request.getContextPath()+"/addCustomer.jsp?errorMsg=ID not used");
		}else{
			//addEmployee.jsp 실행
			response.sendRedirect(request.getContextPath()+"/addEmployee.jsp?errorMsg=ID not used");
		}
	
	}else{ //고객, 관리자 혹은 탈되한. 기존에 있던 아이디 값이 아니라면

	//service -> true
	service.idCheck(ckId);
		if(N.equals("Customer")){ // 그 중에서도 N에 들어있는 값이 Customer이면
			System.out.println("사용가능");
			//addCustomer.jsp 실행
			response.sendRedirect(request.getContextPath()+"/addCustomer.jsp?ckId="+ckId);
		}else{
			//addEmployee.jsp 실행
			response.sendRedirect(request.getContextPath()+"/addEmployee.jsp?ckId="+ckId);
			}
		}
%>