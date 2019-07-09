<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ǰ �˻� ��� ����Ʈ -->
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
<%@ page import="java.util.List" %>

<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="pro" class="kr.or.ksmart.dto.Product"/>
<jsp:setProperty name="pro" property="*"/>
<!--
	���� ������ �Ѿ�� name���� ProductŬ������ setter��� ���ٸ� �ڵ����� ���� �Էµȴ�.
	request.getParameter("orderby");�� �ڵ����� �̷������.
-->
<h3>�˻� ��ǰ ����Ʈ</h3>
<table border="1">
	<tr>
		<th>��ǰ�ڵ�</th>
		<th>�Ǹ���</th>
		<th>��ǰ��</th>
		<th>ī�װ�</th>
		<th>����</th>
		<th>�����</th>
		<th>�󼼺���</th>
	</tr>

<%
	ProductDao pdao = new ProductDao();
	List<Product> plist = pdao.productSearchList(pro);
	// �˻��� ��ǰ ��ü ����Ʈ ��������
	
	for(int i=0; i<plist.size(); i++) {	// �˻��� ��ǰ ����Ʈ�� ������ŭ ȸ��
%>
	<tr>
		<td><%=plist.get(i).getP_code() %></td>
		<td><%=plist.get(i).getSeller_id() %></td>
		<td><%=plist.get(i).getP_name() %></td>
		<td><%=plist.get(i).getP_cate() %></td>
		<td><%=plist.get(i).getP_price() %></td>
		<td><%=plist.get(i).getP_date() %></td>
		<td>
			<a href="<%=request.getContextPath()%>/administrator/product/productDetail.jsp?send_code=<%=plist.get(i).getP_code()%>">
			�� ����</a>
		</td>
	</tr>
<%	}%>

</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>