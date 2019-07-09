<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ǰ �˻� ȭ�� -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<h3>��ǰ �˻� �ϱ�</h3>
<form action="<%=request.getContextPath()%>/administrator/product/productSearchList.jsp"
method="post">
	<table border="1">
		<tr>
			<th>���ı��� ����</th>
			<td>
				<select name="orderby">
					<option value="">����</option>
					<option value="p_price">���ݼ�</option>
					<option value="p_date">����ϼ�</option>
					<option value="p_name">��ǰ���</option>
					<option value="p_cate">ī�װ���</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>���� ����</th>
			<td>
				<select name="sort">
					<option value="">����</option>
					<option value="asc">��������</option>
					<option value="desc">��������</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>��ǰ�� �˻�</th>
			<td><input type="text" name="p_name"></td>
		</tr>
		<tr>
			<th>���� ����</th>
			<td>
				<input type="text" name="p_price_min"> ~
				<input type="text" name="p_price_max">
			</td>
		</tr>
		<tr>
			<th>����� ����</th>
			<td>
				<input type="text" name="p_date_min"> ~
				<input type="text" name="p_date_max">
			</td>
		</tr>
		<tr>
			<th>ī�װ�</th>
			<td><input type="text" name="p_cate"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">�˻��ϱ�</button>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>