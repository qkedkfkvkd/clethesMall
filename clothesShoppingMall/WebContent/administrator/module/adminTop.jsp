<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sId = (String)session.getAttribute("sId");
	String sName = (String)session.getAttribute("sName");
	String sLevel = (String)session.getAttribute("sLevel");
	
	System.out.println(sId+"<- sId   adminTop.jsp");
	System.out.println(sName+"<- sName   adminTop.jsp");
	System.out.println(sLevel+"<- sLevel   adminTop.jsp");
%>
	<!-- Begin Wrapper -->
	<div id="wrapper">
	
		<!-- Begin Header -->
		<div id="header">
			<a href="<%=request.getContextPath()%>/administrator/member/allMem.jsp">01 전체 회원 관리</a>
			
			<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerList.jsp">02 블랙컨슈머 관리</a>
			<a href="<%=request.getContextPath()%>/administrator/notGoodSeller/notGoodSellerList.jsp">03 부정판매자 관리</a>
			
			<a href="<%=request.getContextPath()%>/administrator/product/productList.jsp">04 상품 관리</a>
			<a href="<%=request.getContextPath()%>/administrator/product/productSearchForm.jsp">05 상품 검색</a>
			
			<a href="<%=request.getContextPath()%>/administrator/productOption/productOpjoinList.jsp">06 상품 옵션 관리</a>
			<a href="<%=request.getContextPath()%>/administrator/productOption/productOpjoinSearchForm.jsp">07 상품 옵션 검색</a>
			
			<a href="<%=request.getContextPath()%>/administrator/proOrder/proOrderList.jsp">08 주문 관리</a>
			
			<a href="<%=request.getContextPath()%>/administrator/returnRefund/returnRefundList.jsp">09 반품/환불 관리</a>
			<br/><br/>
			<%= sName %> 님 <%= sLevel %> 권한 로그인 중
			<a href="<%= request.getContextPath() %>/login/logout.jsp">로그아웃</a>
		</div>
		<!-- End Header -->
		
		
		
		
		
		