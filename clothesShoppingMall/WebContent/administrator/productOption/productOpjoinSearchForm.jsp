<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ǰ �� ��ǰ �ɼ� �˻� ȭ�� -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<h3>��ǰ �ɼ� �˻� ȭ��</h3>
<form action="<%=request.getContextPath()%>/administrator/productOption/productOpjoinSearchList.jsp"
method="post">
	<table border="1">
		<tr>
			<th>�� ����</th>
			<td><input type="text" name="op_gender"></td>
		</tr>
		<tr>
			<th>����</th>
			<td><input type="text" name="op_color"></td>
		</tr>
		<tr>
			<th>������ ����</th>
			<td>
				<input type="text" name="op_size_min"> ~
				<input type="text" name="op_size_max">
			</td>
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