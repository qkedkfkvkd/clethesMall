<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 판매자로부터 제재 요청된 블랙컨슈머 내용 보기 -->
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
<h3>블랙컨슈머 제재 요청</h3>
<form action="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerPro.jsp" method="post">
	<table>
		<tr>
			<th colspan="3"><h5>블랙 컨슈머 관리</h5></th>
		</tr>
		<tr>
			<th>요청한 판매자 아이디</th>
			<td>
				<input type="text" value="<%=bcn.getRequest_seller_id() %>" name="request_seller_id"
				readonly="readonly">
				<input type="text" value="님께서" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>제재 요청된 구매자 아이디</th>
			<td>
				<input type="text" value="<%=bcn.getBuyer_id() %>" name="buyer_id"
				readonly="readonly">
				<input type="text" value="님을 블랙 컨슈머로 지정 요청" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td><input type="text" value="요청 사유" readonly="readonly"></td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea rows="13" cols="100%" readonly="readonly">
				<%=bcn.getRequest_reason() %>
				</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">처리하기</button>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>