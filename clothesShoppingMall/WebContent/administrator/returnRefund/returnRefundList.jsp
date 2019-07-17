<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ǰ ȯ�� ��ü ����Ʈ -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.Retrun_refundDao" %>
<%@ page import="kr.or.ksmart.dto.Retrun_refund" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<h3>��ǰ ȯ�� ��ü ����Ʈ</h3>
<table border="1">
	<tr>
		<th>��ǰ ȯ�� �ڵ�</th>
		<th>��ǰ �ڵ�</th>
		<th>��ǰ��</th> <!-- ��ǰ ���̺�� �����Ѵ�. -->
		<th>������ ���̵�</th>
		<th>ȯ�� �ݾ�</th>
		<th>�󼼺���</th>
	</tr>
<%
	Retrun_refundDao retfdao = new Retrun_refundDao();
	Map<String, Object> map = retfdao.retrefAllList();
	
	@SuppressWarnings("unchecked")
	List<String> prolist = (List<String>)map.get("pname");
	
	@SuppressWarnings("unchecked")
	List<Retrun_refund> reflist = (List<Retrun_refund>)map.get("reflist");
	
	for(int i=0; i<prolist.size(); i++) {
%>
	<tr>
		<td><%=reflist.get(i).getRetf_o_code() %></td>
		<td><%=reflist.get(i).getP_code() %></td>
		<td><%=prolist.get(i) %></td>
		<td><%=reflist.get(i).getBuyer_id() %></td>
		<td><%=reflist.get(i).getReturn_payment() %></td>
		<td>
<a href="<%=request.getContextPath()%>/administrator/returnRefund/returnRefundDetail.jsp?send_code=<%=reflist.get(i).getRetf_o_code()%>">
�󼼺���</a>
		</td>
	</tr>
<%	}%>	
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>