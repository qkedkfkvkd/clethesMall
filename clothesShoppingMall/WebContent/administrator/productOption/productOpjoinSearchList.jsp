<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ǰ �� ��ǰ �ɼ� �˻� ��� ����Ʈ -->
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
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="prop" class="kr.or.ksmart.dto.Pro_option"/>
<jsp:setProperty name="prop" property="*"/>

<h3>��ǰ �� ��ǰ �ɼ� �˻� ��� ����Ʈ</h3>
<table border="1">
	<tr>
		<th>��ǰ�ڵ�</th>
		<th>�Ǹ���</th>
		<th>��ǰ��</th>
		<th>�� ����</th>
		<th>�� ����</th>
		<th>������</th>
		<th>����</th>
		<th>�󼼺���</th>
	</tr>
<%
	Pro_optionDao podao = new Pro_optionDao();
	Map<String, Object> map = podao.productOpSearchList(prop);
	// prop�� ����ִ� �˻����� �Ű������� �ְ� ��ǰ ���̺�� ��ǰ �ɼ� ���̺��� �����߱� ������
	// ��ǰ ����Ʈ�� ��ǰ �ɼ� ����Ʈ �ΰ��� �ϳ��� map ��ü�� ��Ƽ� �����Ͽ���.
	
	List<Product> prolist = (List<Product>)map.get("prolist");
	// ��ǰ ����Ʈ�� "prolist" Ű������ �޾ƿ���
	
	List<Pro_option> proplist = (List<Pro_option>)map.get("proplist");
	// ��ǰ �ɼ� ����Ʈ�� "proplist" Ű������ �޾ƿ���
	
	for(int i=0; i<prolist.size(); i++) {	// ��ǰ ����Ʈ�� ��ǰ �ɼ� ����Ʈ�� ����� �����Ƿ� �ƹ��ų� �ᵵ �������.
%>
	<tr>
		<td><%=prolist.get(i).getP_code() %></td>
		<td><%=prolist.get(i).getSeller_id() %></td>
		<td><%=prolist.get(i).getP_name() %></td>
		<td><%=proplist.get(i).getOp_gender() %></td>
		<td><%=proplist.get(i).getOp_color() %></td>
		<td><%=proplist.get(i).getOp_size() %></td>
		<td><%=prolist.get(i).getP_price() %></td>
		<td>
		<a href="<%=request.getContextPath()%>/administrator/productOption/productOpjoinDetail.jsp?prop_code=<%=proplist.get(i).getPro_op_code()%>">
		�� ����</a>															<!-- ��ǰ�� ��ǰ �ɼ� ���̺� ���ν� �Ѱ��� ���ڵ常 ���������� ��ǰ �ɼ��� �Ѱܾ� �Ѵ�. -->
		</td>
	</tr>
<%	}%>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>