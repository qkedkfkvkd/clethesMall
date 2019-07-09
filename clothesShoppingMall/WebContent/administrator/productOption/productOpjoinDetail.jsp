<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 상품 · 상품 옵션 상세 조회  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.Pro_optionDao" %>
<%@ page import="kr.or.ksmart.dto.Pro_option" %>
<%@ page import="kr.or.ksmart.dto.Product" %>
<%@ page import="java.util.Map" %>

<%
	String prop_code = request.getParameter("prop_code");
	System.out.println(prop_code + "<- prop_code   productOpjoinDetail.jsp");
	
	Pro_optionDao podao = new Pro_optionDao();
	Map<String, Object> map = podao.productOpDetail(prop_code);
	Product pro = (Product)map.get("product");
	Pro_option prop = (Pro_option)map.get("pro_option");
%>
<h3>상품 - 상품 옵션 상세 조회</h3>
<table border="1">
	<tr>
		<th>상품코드</th>
		<td><%=pro.getP_code() %></td>
	</tr>
	<tr>
		<th>상품옵션 코드</th>
		<td><%=prop.getPro_op_code() %></td>
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
		<th>옷 성별</th>
		<td><%=prop.getOp_gender() %></td>
	</tr>
	<tr>
		<th>색상</th>
		<td><%=prop.getOp_color() %></td>
	</tr>
	<tr>
		<th>사이즈</th>
		<td><%=prop.getOp_size() %></td>
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
			<a href="<%=request.getContextPath()%>/administrator/productOption/productOpjoinList.jsp">
			리스트로</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>