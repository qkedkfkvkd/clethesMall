<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<!-- 전체 회원리스트 보기 -->
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

<h3>전체 회원 리스트</h3>
<table>
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>권한</th>
		<th>성별</th>
		<th>제재 여부</th> <!-- 블랙컨슈머, 부정판매자 제재 -->
		<th>상세보기</th>
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
		<td><a href="<%=request.getContextPath()%>/administrator/member/gaeinMem.jsp?send_id=<%=(mlist.get(i)).getU_id()%>">상세 보기</a></td>
	</tr>
<%	}%>
</table>

<%@ include file="/administrator/module/adminBottom.jsp" %>
</body>
</html>