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
	List<BlackConsumer> bcReqlist = bcdao.request_blacon();
	for(int i=0; i<bclist.size(); i++) {
%>
	<tr>
		<td><%=bclist.get(i).getBuyer_id() %></td>
		<td><%=bclist.get(i).getRequest_seller_id() %></td>
		<td><%=bclist.get(i).isTreat_whether() %></td>
		<td>
<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerDetail.jsp?buy_id=<%=bclist.get(i).getBuyer_id()%>&re_sel_id=<%=bclist.get(i).getRequest_seller_id()%>">
�� ����</a>
		</td>
	</tr>
<%	}%>
</table>
<hr/>
<table border="1">
<%	if(bcReqlist.size() == 0) {%>
	<tr>
		<td>��û�� �������� ����Ʈ�� �����ϴ�.</td>
	</tr>
<%
	} else {
		for(int i=0; i<bcReqlist.size(); i++) {
%>
	<tr>
		<td>
			�Ǹ���<%=bcReqlist.get(i).getRequest_seller_id()%>�Բ���
			������<%=bcReqlist.get(i).getBuyer_id()%>�� �������� ���� ��û
<a href="<%=request.getContextPath()%>/administrator/blackConsumer/blackConsumerForm.jsp?buy_id=<%=bcReqlist.get(i).getBuyer_id()%>&re_sel_id=<%=bcReqlist.get(i).getRequest_seller_id()%>">
���뺸��</a>
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