<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ü ���� ��û�ǰų� ����� �������� ����Ʈ -->
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
<%@ page import="java.util.List" %>

<h3>�������� ��ü ����Ʈ</h3>
<table border="1">
	<tr>
		<th>������ ������</th>
		<th>��û �Ǹ���</th>
		<th>���� ����</th>
		<th>�󼼺���</th>
	</tr>

<%
	BlackConsumerDao bcdao = new BlackConsumerDao();
	List<BlackConsumer> bclist = bcdao.blackAllList();
	// ���� ���� ��(treat_whether : 1 (true))�ų� ���� ��û ��(treat_whether:0 (false))��
	// �������� ��ü ����Ʈ
	
	List<BlackConsumer> bcReqlist = bcdao.request_blacon();
	// �Ǹ��ڷκ��� ���� ��û�� (treat_whether:0 (false)) �������� ����Ʈ �ҷ�����
	
	for(int i=0; i<bclist.size(); i++) { // ��ü ����Ʈ ��ŭ ������.
%>
	<tr>
		<td><%=bclist.get(i).getBuyer_id() %></td>
		<td><%=bclist.get(i).getRequest_seller_id() %></td>
		<td><%=bclist.get(i).isTreat_whether() %></td>
		<td>
<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerDetail.jsp?buy_id=<%=bclist.get(i).getBuyer_id()%>&re_sel_id=<%=bclist.get(i).getRequest_seller_id()%>">
�� ����</a>										<!-- �������� �� ��ȸ�� ���� �⺻Ű�� �Ǵ� ������ ���̵�� �Ǹ��� ���̵� �ѱ��. -->
		</td>
	</tr>
<%	}%>
</table>
<hr/>
<table border="1">
<%	if(bcReqlist.size() == 0) {%> <!-- ���� ���ΰ� (treat_whether:1 (true))�� �������ӵ鸸 �ִٸ� -->
	<tr>
		<td>��û�� �������� ����Ʈ�� �����ϴ�.</td>
	</tr>
<%
	} else {
		for(int i=0; i<bcReqlist.size(); i++) { // treat_whether:0�� �������Ӱ� �ִٸ�
%>
	<tr>
		<td>
			�Ǹ���<%=bcReqlist.get(i).getRequest_seller_id()%>�Բ���
			������<%=bcReqlist.get(i).getBuyer_id()%>�� �������� ���� ��û
<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerForm.jsp?buy_id=<%=bcReqlist.get(i).getBuyer_id()%>&re_sel_id=<%=bcReqlist.get(i).getRequest_seller_id()%>">
���뺸��</a>										<!-- �������� �� ��ȸ�� ���� �⺻Ű�� �Ǵ� ������ ���̵�� �Ǹ��� ���̵� �ѱ��. -->
		</td>
	</tr>
<%
		}
	}
%>
</table>
<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>