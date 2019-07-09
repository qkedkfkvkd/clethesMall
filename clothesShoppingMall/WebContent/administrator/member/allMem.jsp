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
	System.out.println(mlist.size() + " <- mlist.size()   allMem.jsp");
	System.out.println(mlist.get(0).getU_id() + " <- mlist.get(0).getU_id()   allMem.jsp");
	System.out.println(mlist.get(1).getU_id() + " <- mlist.get(1).getU_id()   allMem.jsp");
	System.out.println(mlist.get(2).getU_id() + " <- mlist.get(2).getU_id()   allMem.jsp");
	for(int i=0; i<mlist.size(); i++) {
%>
	<tr>
		<td><%=(mlist.get(i)).getU_id()%></td>
		<td><%=(mlist.get(i)).getU_name()%></td>
		<td><%=(mlist.get(i)).getU_level()%></td>
		<td><%=(mlist.get(i)).getU_gender()%></td>
		<td><%=(mlist.get(i)).isU_sanctions()%></td>
		<td><a href="<%=request.getContextPath()%>/administrator/member/gaeinMem.jsp?send_id=<%=(mlist.get(i)).getU_id()%>">�� ����</a></td>
	</tr>
<%	}%>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>