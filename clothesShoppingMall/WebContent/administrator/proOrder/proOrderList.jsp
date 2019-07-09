<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 주문 현황 전체 리스트 -->
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
<%@ page import="java.util.List" %>

<h3>주문 현황 전체 리스트</h3>
<table border="1">
	<tr>
		<th>주문코드</th>
		<th>구매자</th>
		<th>상품코드</th>
		<th>주문 수량</th>
		<th>개당 가격</th>
		<th>결제 금액</th>
		<th>상세보기</th>
	</tr>
<%
	Pro_orderDao prdao = new Pro_orderDao();
	List<Pro_order> prlist = prdao.proOrderAllList();
	// 전체 주문 현황을 리스트로 하여 가져오기
	
	for(int i=0; i<prlist.size(); i++) {	// 전체 주문 현황 갯수 만큼 회전
%>
	<tr>
		<td><%=prlist.get(i).getO_code() %></td>
		<td><%=prlist.get(i).getBuyer_id() %></td>
		<td><%=prlist.get(i).getP_code() %></td>
		<td><%=prlist.get(i).getO_amount() %></td>
		<td><%=prlist.get(i).getO_unit_price() %></td>
		<td><%=prlist.get(i).getO_payment() %></td>
		<td>
		<a href="<%=request.getContextPath()%>/administrator/proOrder/proOrderDetail.jsp?pror_code=<%=prlist.get(i).getO_code()%>">
		상세 보기</a>								<!-- 상세 조회를 위해 주문 테이블의 기본키인 주문 코드를 넘긴다. -->
		</td>
	</tr>
<%	}%>	
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>