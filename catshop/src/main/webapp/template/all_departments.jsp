<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


    <!-- Hero Section Begin -->
    <section class="hero hero-normal" style="min-width:1200px">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>카테고리</span>
                        </div>
                        <ul>
                        	
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=New">신상품</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=캣타워">캣타워</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=생활/리빙용품">생활/리빙용품</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=목욕/미용용품">목욕/미용용품</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=배변용품">배변용품</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=건식사료">건식사료</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=습식사료">습식사료</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=고양이간식">고양이간식&영양제</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=장난감">장난감</a></li>
                            <li><a href="<%=request.getContextPath()%>/customer/customerGoodsList.jsp?goodsMenu=스크레쳐">스크레쳐</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="#">
                                <div class="hero__search__categories">
                                    카테고리
                                    <span class="arrow_carrot-down"></span>
                                </div>
                                <input type="text" placeholder="검색해보세요">
                                <button type="submit" class="site-btn">SEARCH</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>02 - 1004 - 1004</h5>
                                <span>전화가능 : 09:00 ~ 06:00</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->