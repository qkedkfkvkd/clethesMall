<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ǰ ȯ�� �� ��ȸ -->
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
<%@ page import="kr.or.ksmart.dto.Product" %>
<%@ page import="java.util.Map" %>

<table border="1">
	<tr>
		<th>��ǰ ȯ�� �ڵ�</th>
		<th>��ǰ �ڵ�</th>
		<th>��ǰ��</th> <!-- ��ǰ ���̺�� �����Ѵ�. -->
		<th>ī�װ�</th> <!-- ��ǰ ���̺�� �����Ѵ�. -->
		<th>�Ǹ��� ���̵�</th>
		<th>������ ���̵�</th>
		<th>�ֹ� ����</th>
		<th>���� ����</th>
		<th>ȯ�� �ݾ�</th>
	</tr>
<%
	String retf_o_code = request.getParameter("send_code");
	System.out.println(retf_o_code + " <- retf_o_code   returnRefundDetail.jsp");
	
	Retrun_refundDao redao = new Retrun_refundDao();
	Map<String, Object> map = redao.returnRefundDetail(retf_o_code);
	Product pro = (Product)map.get("pro");
	Retrun_refund ref = (Retrun_refund)map.get("ref");
%>
	<tr>
		<td><%=ref.getRetf_o_code() %></td>
		<td><%=ref.getP_code() %></td>
		<td><%=pro.getP_name() %></td>
		<td><%=pro.getP_cate() %></td>
		<td><%=ref.getSeller_id() %></td>
		<td><%=ref.getBuyer_id() %></td>
		<td><%=ref.getO_amount() %></td>
		<td><%=ref.getO_unit_price() %></td>
		<td><%=ref.getReturn_payment() %></td>
		<td>
			<a href="<%=request.getContextPath()%>/administrator/returnRefund/returnRefundList.jsp">����Ʈ��</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>