<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- ��ü ȸ������Ʈ ���� -->
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
<%@ page import="java.util.List" %>

<h3>��ü ȸ�� ����Ʈ</h3>
<table>
	<tr>
		<th>���̵�</th>
		<th>�̸�</th>
		<th>����</th>
		<th>����</th>
		<th>���� ����</th> <!-- ��������, �����Ǹ��� ���� -->
		<th>�󼼺���</th>
	</tr>
	
<%
	MemberDao mdao = new MemberDao();
	List<Member> mlist = mdao.memberAllList();
	// ��ü ȸ�� ����Ʈ ��ȯ
	
	for(int i=0; i<mlist.size(); i++) {	// ��ü ȸ�� ��ŭ ȸ��
%>
	<tr>
		<td><%=(mlist.get(i)).getU_id()%></td>
		<td><%=(mlist.get(i)).getU_name()%></td>
		<td><%=(mlist.get(i)).getU_level()%></td>
		<td><%=(mlist.get(i)).getU_gender()%></td>
		<td><%=(mlist.get(i)).isU_sanctions()%></td>
		<td><a href="<%=request.getContextPath()%>/administrator/member/gaeinMem.jsp?send_id=<%=(mlist.get(i)).getU_id()%>">�� ����</a></td>
	</tr>									<!-- �� ��ȸ�� ���� ȸ�� ���̺��� �⺻Ű�� ȸ�� ���̵� �ѱ��. -->
<%	}%>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>