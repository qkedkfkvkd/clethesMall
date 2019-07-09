<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 상품 · 상품 옵션 검색 화면 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<h3>상품 옵션 검색 화면</h3>
<form action="<%=request.getContextPath()%>/administrator/productOption/productOpjoinSearchList.jsp"
method="post">
	<table border="1">
		<tr>
			<th>옷 성별</th>
			<td><input type="text" name="op_gender"></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input type="text" name="op_color"></td>
		</tr>
		<tr>
			<th>사이즈 구간</th>
			<td>
				<input type="text" name="op_size_min"> ~
				<input type="text" name="op_size_max">
			</td>
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