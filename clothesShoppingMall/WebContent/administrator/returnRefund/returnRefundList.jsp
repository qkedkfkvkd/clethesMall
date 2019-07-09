<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 반품 환불 전체 리스트 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.Retrun_refundDao" %>
<%@ page import="kr.or.ksmart.dto.Retrun_refund" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<h3>반품 환불 전체 리스트</h3>
<table border="1">
	<tr>
		<th>반품 환불 코드</th>
		<th>주문 코드</th>
		<th>상품 코드</th>
		<th>상품명</th> <!-- 상품 테이블과 조인한다. -->
		<th>구매자 아이디</th>
		<th>환불 금액</th>
		<th>상세보기</th>
	</tr>
<%
	Retrun_refundDao retfdao = new Retrun_refundDao();
	//Map<String, Object> map = retfdao.
%>	
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>