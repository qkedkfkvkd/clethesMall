<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ǰ �� ��ǰ �ɼ� �� ��ȸ  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.Pro_optionDao" %>
<%@ page import="kr.or.ksmart.dto.Pro_option" %>
<%@ page import="kr.or.ksmart.dto.Product" %>
<%@ page import="java.util.Map" %>

<%
	String prop_code = request.getParameter("prop_code");
	System.out.println(prop_code + "<- prop_code   productOpjoinDetail.jsp");
	
	Pro_optionDao podao = new Pro_optionDao();
	Map<String, Object> map = podao.productOpDetail(prop_code);
	// ��ǰ ���̺�� ��ǰ �ɼ� ���̺��� �����߱� ������ ��ǰ ��ü�� ��ǰ �ɼ� ��ü �ΰ��� �ϳ��� map ��ü�� ��Ƽ� �����Ͽ���.
	
	Product pro = (Product)map.get("product");
	// "product" Ű������ ���� ��ǰ ��ü ��������
	
	Pro_option prop = (Pro_option)map.get("pro_option");
	// "pro_option" Ű������ ���� ��ǰ �ɼ� ��ü ��������
%>
<h3>��ǰ - ��ǰ �ɼ� �� ��ȸ</h3>
<table border="1">
	<tr>
		<th>��ǰ�ڵ�</th>
		<td><%=pro.getP_code() %></td>
	</tr>
	<tr>
		<th>��ǰ�ɼ� �ڵ�</th>
		<td><%=prop.getPro_op_code() %></td>
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
		<th>�� ����</th>
		<td><%=prop.getOp_gender() %></td>
	</tr>
	<tr>
		<th>����</th>
		<td><%=prop.getOp_color() %></td>
	</tr>
	<tr>
		<th>������</th>
		<td><%=prop.getOp_size() %></td>
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
			<a href="<%=request.getContextPath()%>/administrator/productOption/productOpjoinList.jsp">
			����Ʈ��</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>