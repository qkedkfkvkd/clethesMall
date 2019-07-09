<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 상품 검색 결과 리스트 -->
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

<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="pro" class="kr.or.ksmart.dto.Product"/>
<jsp:setProperty name="pro" property="*"/>
<!--
	이전 폼에서 넘어온 name값과 Product클래스의 setter명과 같다면 자동으로 값이 입력된다.
	request.getParameter("orderby");가 자동으로 이루어진다.
-->
<h3>검색 상품 리스트</h3>
<table border="1">
	<tr>
		<th>상품코드</th>
		<th>판매자</th>
		<th>상품명</th>
		<th>카테고리</th>
		<th>가격</th>
		<th>등록일</th>
		<th>상세보기</th>
	</tr>

<%
	ProductDao pdao = new ProductDao();
	List<Product> plist = pdao.productSearchList(pro);
	// 검색된 상품 전체 리스트 가져오기
	
	for(int i=0; i<plist.size(); i++) {	// 검색된 상품 리스트의 갯수만큼 회전
%>
	<tr>
		<td><%=plist.get(i).getP_code() %></td>
		<td><%=plist.get(i).getSeller_id() %></td>
		<td><%=plist.get(i).getP_name() %></td>
		<td><%=plist.get(i).getP_cate() %></td>
		<td><%=plist.get(i).getP_price() %></td>
		<td><%=plist.get(i).getP_date() %></td>
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