<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="vo.Employee"%>
<%@page import="vo.Customer"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>프리미엄 고양이 쇼핑몰 냥집사몰</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
</head>

<body>


    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper" style="min-width:1200px">
        <div class="humberger__menu__logo">
            <a href="#"><img src="<%=request.getContextPath()%>/img/logo.png" alt=""></a>
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <li><a href="<%=request.getContextPath()%>/customer/cartListForm.jsp?customerId=<%=session.getAttribute("id")%>"><i class="fa fa-shopping-bag"></i></a></li>
            </ul>
        </div>
        <div class="humberger__menu__widget">
            <div class="header__top__right__auth">
            
            
            
            <%
            
            
        	Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
        	Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
            
            
            	//로그인 안됐을경우
            if(session.getAttribute("name") == null){
            %>
            
            <!-- login -->
                <a href="<%=request.getContextPath()%>/loginForm.jsp"><i class="fa fa-user"></i> 로그인</a>
                
                
            <%
            	}else{
            		
            	//로그인 됐을경우
            %>    
            	<a href="<%=request.getContextPath()%>/login.jsp"><i class="fa fa-user"></i> <%=session.getAttribute("name")%><i class="fa fa-heart"></i></a>
            <%
            	}
            %>
            </div>
            
            
            
        </div>
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li class="active"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=All">Shop</a></li>
                <li><a href="<%=request.getContextPath()%>/contact.jsp">Contact</a></li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="header__top__right__social">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-pinterest-p"></i></a>
        </div>
        <div class="humberger__menu__contact">
            <ul>
                <li><i class="fa fa-envelope"></i> janjam00@naver.com</li>
                <li>만오천원이상 주문시 무료배송</li>
            </ul>
        </div>
    </div>
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__left">
                            <ul>
                                <li><i class="fa fa-envelope"></i> janjam00@naver.com</li>
                                <li>만오천원이상 주문시 무료배송</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__right">
                            <div class="header__top__right__social">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-linkedin"></i></a>
                                <a href="#"><i class="fa fa-pinterest-p"></i></a>
                            </div>
                            <div class="header__top__right__social">
                             <a href="<%=request.getContextPath()%>/addCustomer.jsp"> 회원가입</a>
                            </div>
                            <div class="header__top__right__auth">
                            
                            
                            
                            
				            <%
				            	if(session.getAttribute("name") == null){
				            %>
				            
				            <!-- login -->
				                <a href="<%=request.getContextPath()%>/loginForm.jsp"><i class="fa fa-user"></i> 로그인</a>
				                
				                
				            <%
				            	}else{
				            %>    
				            	<a href="<%=request.getContextPath()%>/login.jsp"><i class="fa fa-user"></i> <%=session.getAttribute("name")%></a>
				            <%
				            	}
				            %>
				            </div>
                            
                            
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="header__logo">
                        <a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-5">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=All">Shop</a></li>
                            <li><a href="<%=request.getContextPath()%>/contact.jsp">Contact</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/customer/cartListForm.jsp?customerId=<%=session.getAttribute("id")%>"><i class="fa fa-shopping-bag"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->