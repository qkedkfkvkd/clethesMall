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
		<th>�ֹ� �ڵ�</th>
		<th>��ǰ �ڵ�</th>
		<th>��ǰ��</th> <!-- ��ǰ ���̺�� �����Ѵ�. -->
		<th>������ ���̵�</th>
		<th>ȯ�� �ݾ�</th>
		<th>�󼼺���</th>
	</tr>
<%
	Retrun_refundDao retfdao = new Retrun_refundDao();
	//Map<String, Object> map = retfdao.
%>	
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>