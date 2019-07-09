<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 제재 요청되거나 제재된 블랙컨슈머 상세 보기 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.BlackConsumerDao" %>
<%@ page import="kr.or.ksmart.dto.BlackConsumer" %>

<%
	request.setCharacterEncoding("euc-kr");
	String buyer_id = request.getParameter("buy_id");				// 제재 요청된 구매자 아이디
	String request_seller_id = request.getParameter("re_sel_id");	// 제재 요청한 판매자 아이디
	
	System.out.println(buyer_id + "<-- buyer_id   blackConsumerDetail.jsp");
	System.out.println(request_seller_id + "<-- request_seller_id   blackConsumerDetail.jsp");
	
	BlackConsumerDao bcdao = new BlackConsumerDao();
	BlackConsumer bcn = bcdao.blaconDetail(buyer_id, request_seller_id);
	// 기본키인 구매자 아이디와 판매자 아이디로 조회하여 결과값을 반환 받는다.
%>

<h3>블랙컨슈머 상세 조회</h3>
<table border="1">
	<tr>
		<th>구매자 아이디</th>
		<td><%=bcn.getBuyer_id() %></td>
	</tr>
	<tr>
		<th>요청한 판매자 아이디</th>
		<td><%=bcn.getRequest_seller_id() %></td>
	</tr>
	<tr>
		<th>요청 사유</th>
		<td><%=bcn.getRequest_reason() %></td>
	</tr>
	<tr>
		<th>처리 사유</th>
		<td><%=bcn.getTreat_reason() %></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerList.jsp">
			리스트로</a>
		</td>
	</tr>
</table>


<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>