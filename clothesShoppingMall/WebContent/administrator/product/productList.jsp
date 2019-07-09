<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 전체 상품 리스트 -->
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
<%@ page import="java.util.List" %>

<h3>전체 상품 리스트</h3>
<table border="1">
	<tr>
		<th>상품코드</th>
		<th>판매자</th>
		<th>상품명</th>
		<th>가격</th>
		<th>상세보기</th>
	</tr>

<%
	ProductDao pdao = new ProductDao();
	List<Product> plist = pdao.productAllList();
	for(int i=0; i<plist.size(); i++) {
%>
	<tr>
		<td><%=plist.get(i).getP_code() %></td>
		<td><%=plist.get(i).getSeller_id() %></td>
		<td><%=plist.get(i).getP_name() %></td>
		<td><%=plist.get(i).getP_price() %></td>
		<td>
			<a href="<%=request.getContextPath()%>/administrator/product/productDetail.jsp?send_code=<%=plist.get(i).getP_code()%>">
			상세 보기</a>
		</td>
	</tr>
<%	}%>

</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>