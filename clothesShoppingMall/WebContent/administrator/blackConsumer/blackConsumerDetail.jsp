<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ���� ��û�ǰų� ����� �������� �� ���� -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.BlackConsumerDao" %>
<%@ page import="kr.or.ksmart.dto.BlackConsumer" %>

<%
	request.setCharacterEncoding("euc-kr");
	String buyer_id = request.getParameter("buy_id");				// ���� ��û�� ������ ���̵�
	String request_seller_id = request.getParameter("re_sel_id");	// ���� ��û�� �Ǹ��� ���̵�
	
	System.out.println(buyer_id + "<-- buyer_id   blackConsumerDetail.jsp");
	System.out.println(request_seller_id + "<-- request_seller_id   blackConsumerDetail.jsp");
	
	BlackConsumerDao bcdao = new BlackConsumerDao();
	BlackConsumer bcn = bcdao.blaconDetail(buyer_id, request_seller_id);
	// �⺻Ű�� ������ ���̵�� �Ǹ��� ���̵�� ��ȸ�Ͽ� ������� ��ȯ �޴´�.
%>

<h3>�������� �� ��ȸ</h3>
<table border="1">
	<tr>
		<th>������ ���̵�</th>
		<td><%=bcn.getBuyer_id() %></td>
	</tr>
	<tr>
		<th>��û�� �Ǹ��� ���̵�</th>
		<td><%=bcn.getRequest_seller_id() %></td>
	</tr>
	<tr>
		<th>��û ����</th>
		<td><%=bcn.getRequest_reason() %></td>
	</tr>
	<tr>
		<th>ó�� ����</th>
		<td><%=bcn.getTreat_reason() %></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerList.jsp">
			����Ʈ��</a>
		</td>
	</tr>
</table>


<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>