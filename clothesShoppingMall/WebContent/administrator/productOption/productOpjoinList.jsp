<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 상품 · 상품 옵션 전체 리스트 -->
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
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<h3>상품 · 상품 옵션 전체 리스트</h3>
<table border="1">
	<tr>
		<th>상품코드</th>
		<th>판매자</th>
		<th>상품명</th>
		<th>옷 성별</th>
		<th>옷 색상</th>
		<th>가격</th>
		<th>상세보기</th>
	</tr>
<%
	Pro_optionDao podao = new Pro_optionDao();
	Map<String, Object> map = podao.productOpAllList();
	// 상품 테이블과 상품 옵션 테이블을 조인했기 때문에 상품 리스트와 상품 옵션 리스트 두개를 하나의 map 객체에 담아서 리턴하였다.
	
	List<Product> prolist = (List<Product>)map.get("prolist");
	// 상품 리스트를 "prolist" 키값으로 받아오기
	
	List<Pro_option> proplist = (List<Pro_option>)map.get("proplist");
	// 상품 옵션 리스트를 "proplist" 키값으로 받아오기
	
	for(int i=0; i<prolist.size(); i++) {	// 상품 리스트와 상품 옵션 리스트는 사이즈가 같으므로 아무거나 써도 상관없다.
%>
	<tr>
		<td><%=prolist.get(i).getP_code() %></td>
		<td><%=prolist.get(i).getSeller_id() %></td>
		<td><%=prolist.get(i).getP_name() %></td>
		<td><%=proplist.get(i).getOp_gender() %></td>
		<td><%=proplist.get(i).getOp_color() %></td>
		<td><%=prolist.get(i).getP_price() %></td>
		<td>
		<a href="<%=request.getContextPath()%>/administrator/productOption/productOpjoinDetail.jsp?prop_code=<%=proplist.get(i).getPro_op_code()%>">
		상세 보기</a>															<!-- 상품과 상품 옵션 테이블 조인시 한개의 레코드만 가져오려면 상품 옵션을 넘겨야 한다. -->
		</td>
	</tr>
<%	}%>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>