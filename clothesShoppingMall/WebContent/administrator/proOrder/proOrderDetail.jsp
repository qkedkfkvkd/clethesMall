<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 주문 현황 상세 조회 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.Pro_orderDao" %>
<%@ page import="kr.or.ksmart.dto.Pro_order" %>

<%
	String o_code = request.getParameter("pror_code");
	System.out.println(o_code + "<- o_code   proOrderDatail.jsp");
	
	Pro_orderDao prdao = new Pro_orderDao();
	Pro_order pro = prdao.proOrderDetail(o_code);
	// 주문 테이블의 기본키인 주문 코드를 매개변수로 넣어서 주문 상세 가져오기
%>
<h3>상품 - 상품 옵션 상세 조회</h3>
<table border="1">
	<tr>
		<th>주문 코드</th>
		<td><%=pro.getO_code() %></td>
	</tr>
	<tr>
		<th>구매자 아이디</th>
		<td><%=pro.getBuyer_id() %></td>
	</tr>
	<tr>
		<th>상품 코드</th>
		<td><%=pro.getP_code() %></td>
	</tr>
	<tr>
		<th>주문 수량</th>
		<td><%=pro.getO_amount() %></td>
	</tr>
	<tr>
		<th>개당 가격</th>
		<td><%=pro.getO_unit_price() %></td>
	</tr>
	<tr>
		<th>결제 금액</th>
		<td><%=pro.getO_payment() %></td>
	</tr>
	<tr>
		<th>받는 사람 주소</th>
		<td><%=pro.getO_get_addr() %></td>
	</tr>
	<tr>
		<th>받는 사람 번호</th>
		<td><%=pro.getO_get_call() %></td>
	</tr>
	<tr>
		<th>보내는 사람 이름</th>
		<td><%=pro.getO_send_name() %></td>
	</tr>
	<tr>
		<th>보내는 사람 번호</th>
		<td><%=pro.getO_send_call() %></td>
	</tr>
	<tr>
		<th>주문 날짜</th>
		<td><%=pro.getO_date() %></td>
	</tr>
	<tr>
		<th>배송 메세지</th>
		<td><%=pro.getO_message() %></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<%=request.getContextPath()%>/administrator/proOrder/proOrderList.jsp">
			리스트로</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>