<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- �Ǹ��ڷκ��� ���� ��û�� �������� ���� ���� -->
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
<h3>�������� ���� ��û</h3>
<form action="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerPro.jsp" method="post">
	<table>
		<tr>
			<th colspan="3"><h5>�� ������ ����</h5></th>
		</tr>
		<tr>
			<th>��û�� �Ǹ��� ���̵�</th>
			<td>
				<input type="text" value="<%=bcn.getRequest_seller_id() %>" name="request_seller_id"
				readonly="readonly">
				<input type="text" value="�Բ���" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>���� ��û�� ������ ���̵�</th>
			<td>
				<input type="text" value="<%=bcn.getBuyer_id() %>" name="buyer_id"
				readonly="readonly">
				<input type="text" value="���� �� �����ӷ� ���� ��û" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td><input type="text" value="��û ����" readonly="readonly"></td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea rows="13" cols="100%" readonly="readonly">
				<%=bcn.getRequest_reason() %>
				</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">ó���ϱ�</button>
			</td>
		</tr>
	</table>
</form>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>