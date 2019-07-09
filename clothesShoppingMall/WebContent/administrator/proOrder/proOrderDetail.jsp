<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- �ֹ� ��Ȳ �� ��ȸ -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.Pro_orderDao" %>
<%@ page import="kr.or.ksmart.dto.Pro_order" %>

<%
	String o_code = request.getParameter("pror_code");
	System.out.println(o_code + "<- o_code   proOrderDatail.jsp");
	
	Pro_orderDao prdao = new Pro_orderDao();
	Pro_order pro = prdao.proOrderDetail(o_code);
	// �ֹ� ���̺��� �⺻Ű�� �ֹ� �ڵ带 �Ű������� �־ �ֹ� �� ��������
%>
<h3>��ǰ - ��ǰ �ɼ� �� ��ȸ</h3>
<table border="1">
	<tr>
		<th>�ֹ� �ڵ�</th>
		<td><%=pro.getO_code() %></td>
	</tr>
	<tr>
		<th>������ ���̵�</th>
		<td><%=pro.getBuyer_id() %></td>
	</tr>
	<tr>
		<th>��ǰ �ڵ�</th>
		<td><%=pro.getP_code() %></td>
	</tr>
	<tr>
		<th>�ֹ� ����</th>
		<td><%=pro.getO_amount() %></td>
	</tr>
	<tr>
		<th>���� ����</th>
		<td><%=pro.getO_unit_price() %></td>
	</tr>
	<tr>
		<th>���� �ݾ�</th>
		<td><%=pro.getO_payment() %></td>
	</tr>
	<tr>
		<th>�޴� ��� �ּ�</th>
		<td><%=pro.getO_get_addr() %></td>
	</tr>
	<tr>
		<th>�޴� ��� ��ȣ</th>
		<td><%=pro.getO_get_call() %></td>
	</tr>
	<tr>
		<th>������ ��� �̸�</th>
		<td><%=pro.getO_send_name() %></td>
	</tr>
	<tr>
		<th>������ ��� ��ȣ</th>
		<td><%=pro.getO_send_call() %></td>
	</tr>
	<tr>
		<th>�ֹ� ��¥</th>
		<td><%=pro.getO_date() %></td>
	</tr>
	<tr>
		<th>��� �޼���</th>
		<td><%=pro.getO_message() %></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<%=request.getContextPath()%>/administrator/proOrder/proOrderList.jsp">
			����Ʈ��</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>