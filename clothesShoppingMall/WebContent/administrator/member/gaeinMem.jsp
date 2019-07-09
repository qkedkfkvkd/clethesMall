<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ���� ȸ�� ���� ��ȸ -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
</head>
<body>
<%@ include file="/administrator/module/adminTop.jsp" %>
<%@ include file="/administrator/module/adminLeft.jsp" %>

<%@ page import="kr.or.ksmart.dao.MemberDao" %>
<%@ page import="kr.or.ksmart.dto.Member" %>


<%
	String u_id = request.getParameter("send_id");
	System.out.println(u_id + "<-- u_id   gaeinMem.jsp");
	MemberDao mdao = new MemberDao();
	Member mem = mdao.gaeinMem(u_id);
	// ȸ�� ���̺��� �⺻Ű�� ���̵�� ȸ�� ������ ��ȯ
%>

<h3>���� ȸ�� �� ����</h3>
<table border="1">
	<tr>
		<th>���̵�</th>
		<td><%=mem.getU_id()%></td>
	</tr>
	<tr>
		<th>�̸�</th>
		<td><%=mem.getU_name()%></td>
	</tr>
	<tr>
		<th>����</th>
		<td><%=mem.getU_level()%></td>
	</tr>
	<tr>
		<th>����</th>
		<td><%=mem.getU_gender()%></td>
	</tr>
	<tr>
		<th>����ȣ</th>
		<td><%=mem.getU_phone()%></td>
	</tr>
	<tr>
		<th>�̸���</th>
		<td><%=mem.getU_email()%></td>
	</tr>
	<tr>
		<th>�ּ�</th>
		<td><%=mem.getU_address()%></td>
	</tr>
	<tr>
		<th>���� ����</th>
		<td><%=mem.getU_address()%></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="<%=request.getContextPath()%>/administrator/member/allMem.jsp">����Ʈ��</a>
		</td>
	</tr>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>


