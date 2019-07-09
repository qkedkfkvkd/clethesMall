<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 상품 검색 화면 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<h3>상품 검색 하기</h3>
<form action="<%=request.getContextPath()%>/administrator/product/productSearchList.jsp"
method="post">
	<table border="1">
		<tr>
			<th>정렬기준 선택</th>
			<td>
				<select name="orderby">
					<option value="">선택</option>
					<option value="p_price">가격순</option>
					<option value="p_date">등록일순</option>
					<option value="p_name">상품명순</option>
					<option value="p_cate">카테고리순</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>정렬 선택</th>
			<td>
				<select name="sort">
					<option value="">선택</option>
					<option value="asc">오름차순</option>
					<option value="desc">내림차순</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>상품명 검색</th>
			<td><input type="text" name="p_name"></td>
		</tr>
		<tr>
			<th>가격 구간</th>
			<td>
				<input type="text" name="p_price_min"> ~
				<input type="text" name="p_price_max">
			</td>
		</tr>
		<tr>
			<th>등록일 구간</th>
			<td>
				<input type="text" name="p_date_min"> ~
				<input type="text" name="p_date_max">
			</td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td><input type="text" name="p_cate"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">검색하기</button>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>