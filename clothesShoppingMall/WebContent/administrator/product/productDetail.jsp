<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ǰ �� ���� -->
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

<h3>��ǰ �� ��ȸ</h3>
<table border="1">
	<tr>
		<th>��ǰ�ڵ�</th>
		<td><%=pro.getP_code() %></td>
	</tr>
	<tr>
		<th>�Ǹ��� ���̵�</th>
		<td><%=pro.getSeller_id() %></td>
	</tr>
	<tr>
		<th>��ǰ��</th>
		<td><%=pro.getP_name() %></td>
	</tr>
	<tr>
		<th>ī�װ�</th>
		<td><%=pro.getP_cate() %></td>
	</tr>
	<tr>
		<th>����</th>
		<td><%=pro.getP_price() %></td>
	</tr>
	<tr>
		<th>�������</th>
		<td><%=pro.getP_date() %></td>
	</tr>
	<tr>
		<th>�� ����</th>
		<td><%=pro.getP_desc() %></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<%=request.getContextPath()%>/administrator/product/productList.jsp">
			����Ʈ��</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>