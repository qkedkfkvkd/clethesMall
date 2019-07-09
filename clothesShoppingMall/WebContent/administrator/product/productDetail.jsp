<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 상품 상세 보기 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.ProductDao" %>
<%@ page import="kr.or.ksmart.dto.Product" %>

<%
	request.setCharacterEncoding("euc-kr");
	String p_code = request.getParameter("send_code");
	System.out.println(p_code + "<-- p_code   productDetail.jsp");
	
	ProductDao pdao = new ProductDao();
	Product pro = pdao.productDetail(p_code);
%>

<h3>상품 상세 조회</h3>
<table border="1">
	<tr>
		<th>상품코드</th>
		<td><%=pro.getP_code() %></td>
	</tr>
	<tr>
		<th>판매자 아이디</th>
		<td><%=pro.getSeller_id() %></td>
	</tr>
	<tr>
		<th>상품명</th>
		<td><%=pro.getP_name() %></td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td><%=pro.getP_cate() %></td>
	</tr>
	<tr>
		<th>가격</th>
		<td><%=pro.getP_price() %></td>
	</tr>
	<tr>
		<th>등록일자</th>
		<td><%=pro.getP_date() %></td>
	</tr>
	<tr>
		<th>상세 설명</th>
		<td><%=pro.getP_desc() %></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<%=request.getContextPath()%>/administrator/product/productList.jsp">
			리스트로</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>